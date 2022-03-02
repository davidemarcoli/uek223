package com.noseryoung.uek223.domain.blogPost;

import com.noseryoung.uek223.domain.appUser.User;
import com.noseryoung.uek223.domain.appUser.UserRepository;
import com.noseryoung.uek223.domain.appUser.UserService;
import com.noseryoung.uek223.domain.blogPost.dto.UpdateBlogPostDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidObjectException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;
import com.noseryoung.uek223.domain.exceptions.NoBlogPostFoundException;
import com.noseryoung.uek223.domain.role.Role;
import com.noseryoung.uek223.domain.role.RoleRepository;
import com.noseryoung.uek223.domain.utils.LevenshteinDistance;
import com.noseryoung.uek223.domain.utils.LevenshteinResult;
import com.noseryoung.uek223.domain.utils.MultiStopwatch;
import com.noseryoung.uek223.domain.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;
    private final NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public BlogPost create(BlogPost blogPost) {
        return blogPostRepository.saveAndFlush(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(UpdateBlogPostDTO blogPost, UUID id) throws NoAccessException, NoBlogPostFoundException, InvalidObjectException {
        if (blogPostRepository.existsById(id)){
            if (hasAccess(id)){
                BlogPost newBlogPost = blogPostMapper.updateBlogPostDTOToBlog(blogPost);
                BlogPost oldBlogPost = findById(id);
                try {
                    nullAwareBeanUtilsBean.copyProperties(oldBlogPost, newBlogPost);
                } catch (Exception e){
                    throw new InvalidObjectException("An unexpected error occurred. Please verify that the provided blogpost is valid!");
                }
                return blogPostRepository.saveAndFlush(oldBlogPost);
            } else {
                throw new NoAccessException();
            }
        } else {
            throw new NoBlogPostFoundException();
        }
    }

    @Override
    public BlogPost findById(UUID id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    @Override
    public List<BlogPost> findByTitle(String title) {

        MultiStopwatch multiStopwatch = new MultiStopwatch();

        List<BlogPost> blogPosts = blogPostRepository.findAll();
        List<LevenshteinResult> levenshteinDistances = new ArrayList<>();

        multiStopwatch.start();
        for (BlogPost blogPost : blogPosts) {
            int levenshteinDistance = LevenshteinDistance.calculate(title.toUpperCase(), blogPost.getTitle().toUpperCase());
            multiStopwatch.newTime();
            levenshteinDistances.add(new LevenshteinResult(blogPost, levenshteinDistance));
        }


        System.out.println("Average Time per Calculation: " + multiStopwatch.getAverageTime());

        levenshteinDistances.sort(Comparator.comparing(LevenshteinResult::getDistance));

        List<BlogPost> validBlogPosts = new ArrayList<>();

        levenshteinDistances.forEach(entry -> {
            if (entry.getSource() instanceof BlogPost) {

                float difference = (float) entry.getDistance() / Math.max(title.length(), ((BlogPost) entry.getSource()).getTitle().length());

                if (difference < 0.30f) {
                    validBlogPosts.add((BlogPost) entry.getSource());
                }
            }
        });

        if (validBlogPosts.isEmpty()) {
            throw new NoBlogPostFoundException("No BlogPost found with the given title");
        } else {
            return validBlogPosts;
        }

    }

    @Override
    public void deleteBlogPost(UUID id) throws NoAccessException, NoBlogPostFoundException {
        if (blogPostRepository.existsById(id)) {
            if (hasAccess(id)) {
                blogPostRepository.deleteById(id);
            } else {
                throw new NoAccessException();
            }
        } else {
            throw new NoBlogPostFoundException();
        }
    }

    @Override
    public List<BlogPost> findAll(int page, int length) {
        //TODO: change sort by title to sort by publish date
        Pageable pageable = PageRequest.of(page, length, Sort.by("title").ascending());
        return blogPostRepository.findAll(pageable).getContent();
    }

    private boolean hasAccess(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findById(id).getAuthor().getId().equals(userRepository.findByUsername(auth.getName()).getId()) ||
                auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


}
