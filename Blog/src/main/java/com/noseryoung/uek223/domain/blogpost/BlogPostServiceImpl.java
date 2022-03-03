package com.noseryoung.uek223.domain.blogpost;

import com.noseryoung.uek223.domain.appuser.User;
import com.noseryoung.uek223.domain.appuser.UserRepository;
import com.noseryoung.uek223.domain.blogpost.dto.UpdateBlogPostDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidObjectException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;
import com.noseryoung.uek223.domain.exceptions.NoBlogPostFoundException;
import com.noseryoung.uek223.domain.role.RoleRepository;
import com.noseryoung.uek223.domain.utils.LevenshteinDistance;
import com.noseryoung.uek223.domain.utils.LevenshteinResult;
import com.noseryoung.uek223.domain.utils.MultiStopwatch;
import com.noseryoung.uek223.domain.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BlogPostMapper blogPostMapper;
    private final NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    @SneakyThrows
    @Transactional
    public BlogPost createBlogPost(BlogPost blogPost) {
        //Set author of blogpost to current user and update the role to AUTHOR if necessary
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User author = userRepository.findByUsername(auth.getName());
        if (author.getRoles().contains(roleRepository.findByName("USER"))){
            author.getRoles().add(roleRepository.findByName("AUTHOR"));
            author.getRoles().remove(roleRepository.findByName("USER"));
            log.log(Level.INFO, "Updated role from user " + author.getUsername() + " to AUTHOR");
        }
        blogPost.setAuthor(author);
        log.log(Level.INFO, "Attempting to create blogpost");
        return blogPostRepository.saveAndFlush(blogPost);
    }

    @Override
    @Transactional
    public BlogPost updateBlogPost(UpdateBlogPostDTO blogPost, UUID id)
            throws NoAccessException, NoBlogPostFoundException, InvalidObjectException {
        if (blogPostRepository.existsById(id)){
            if (hasAccess(id)){
                // Map updateBlogPost back to normal blogpost and try to copy unchangeable data from the old to the new
                // blogpost
                BlogPost newBlogPost = blogPostMapper.updateBlogPostDTOToBlog(blogPost);
                BlogPost oldBlogPost = findById(id);
                try {
                    nullAwareBeanUtilsBean.copyProperties(oldBlogPost, newBlogPost);
                } catch (Exception e){
                    // Could be thrown if something goes wrong when copying the blogpost properties
                    log.log(Level.ERROR, "Something went wrong with copying blogposts: " +
                            Arrays.toString(e.getStackTrace()));
                    throw new InvalidObjectException("An unexpected error occurred. Please verify that the provided " +
                            "blogpost is valid!");
                }
                return blogPostRepository.saveAndFlush(oldBlogPost);
            } else {
                log.log(Level.WARN, "Refused access for user:" + id);
                throw new NoAccessException();
            }
        } else {
            // Since the BlogPosts do not hold sensitive information we can give this information to the user
            throw new NoBlogPostFoundException();
        }
    }

    @Override
    public BlogPost findById(UUID id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    @Override
    public List<BlogPost> findByTitle(String searchedTitle) {

        MultiStopwatch multiStopwatch = new MultiStopwatch();

        List<BlogPost> blogPosts = blogPostRepository.findAll();
        List<LevenshteinResult> levenshteinDistances = new ArrayList<>();

        multiStopwatch.start();

        // calculate levenshtein distance between every blog post and the searched string
        for (BlogPost blogPost : blogPosts) {
            int levenshteinDistance = LevenshteinDistance.calculate(searchedTitle.toUpperCase(),
                    blogPost.getTitle().toUpperCase());
            multiStopwatch.newTime();
            levenshteinDistances.add(new LevenshteinResult(blogPost, levenshteinDistance));
        }

        // keep track of performance since it can use a lot of resources really fast
        log.info("Average Time per Calculation: " + multiStopwatch.getAverageTime());

        levenshteinDistances.sort(Comparator.comparing(LevenshteinResult::getDistance));

        List<BlogPost> validBlogPosts = new ArrayList<>();

        levenshteinDistances.forEach(entry -> {
            // calculate the difference in percent,
            // based of the calculated distance and the length of either the blog title or the title searched for.
            float difference = (float) entry.getDistance() / Math.max(searchedTitle.length(),
                    ((BlogPost) entry.getSource()).getTitle().length());
            // Retrieve all blogposts whose titles are at least 30% similar to the search string
            if (difference < 0.30f) {
                validBlogPosts.add((BlogPost) entry.getSource());
            }
        });

        if (validBlogPosts.isEmpty()) {
            throw new NoBlogPostFoundException("No BlogPost found with the given title");
        } else {
            return validBlogPosts;
        }
    }

    @Override
    @Transactional
    public void deleteBlogPost(UUID id) throws NoAccessException, NoBlogPostFoundException {
        if (blogPostRepository.existsById(id)) {
            if (hasAccess(id)) {
                log.log(Level.INFO, "Attempting to delete blogpost");
                blogPostRepository.deleteById(id);
            } else {
                log.log(Level.WARN, "Refused access for user:" + id);
                throw new NoAccessException();
            }
        } else {
            throw new NoBlogPostFoundException();
        }
    }

    @Override
    public List<BlogPost> findAll(int page, int length) {
        Pageable pageable = PageRequest.of(page, length, Sort.by("createdAt").ascending());
        return blogPostRepository.findAll(pageable).getContent();
    }

    private boolean hasAccess(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findById(id).getAuthor().getId().equals(userRepository.findByUsername(auth.getName()).getId()) ||
                auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
