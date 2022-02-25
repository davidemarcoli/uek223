package com.noseryoung.uek223.domain.blogPost;

import com.noseryoung.uek223.domain.exceptions.NoBlogPostFoundException;
import com.noseryoung.uek223.domain.utils.LevenshteinDistance;
import com.noseryoung.uek223.domain.utils.LevenshteinResult;
import com.noseryoung.uek223.domain.utils.MultiStopwatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Override
    public BlogPost create(BlogPost blogPost) {
        return blogPostRepository.saveAndFlush(blogPost);
    }

    @Override
    public BlogPost update(BlogPost blogPost, UUID id) {
        blogPost.setId(id);

        return blogPostRepository.saveAndFlush(blogPost);
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
    public void delete(UUID id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    public List<BlogPost> findAll(int page, int length) {
        Pageable pageable = PageRequest.of(page, length, Sort.by("title").ascending());
        return blogPostRepository.findAll(pageable).getContent();
    }

}
