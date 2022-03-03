package com.noseryoung.uek223.domain.category;

import com.noseryoung.uek223.domain.exceptions.NoCategoryFoundException;
import com.noseryoung.uek223.domain.utils.LevenshteinDistance;
import com.noseryoung.uek223.domain.utils.LevenshteinResult;
import com.noseryoung.uek223.domain.utils.MultiStopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category update(Category category, UUID id) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findByName(String name) {
        MultiStopwatch multiStopwatch = new MultiStopwatch();

        List<Category> categories = categoryRepository.findAll();
        List<LevenshteinResult> levenshteinDistances = new ArrayList<>();

        multiStopwatch.start();
        for (Category category : categories) {
            int levenshteinDistance = LevenshteinDistance.calculate(name.toUpperCase(),
                    category.getName().toUpperCase());
            multiStopwatch.newTime();
            levenshteinDistances.add(new LevenshteinResult(category, levenshteinDistance));
        }


        log.info("Average Time per Calculation: " + multiStopwatch.getAverageTime());

        levenshteinDistances.sort(Comparator.comparing(LevenshteinResult::getDistance));

        List<Category> validCategories = new ArrayList<>();

        levenshteinDistances.forEach(entry -> {
            float difference = (float) entry.getDistance() / Math.max(name.length(),
                    ((Category) entry.getSource()).getName().length());

            if (difference < 0.30f) {
                validCategories.add((Category) entry.getSource());
            }
        });

        if (validCategories.isEmpty()) {
            throw new NoCategoryFoundException("No Category found with the given name");
        } else {
            return validCategories;
        }
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll(int page, int length) {
        Pageable pageable = PageRequest.of(page, length, Sort.by("name").ascending());
        return categoryRepository.findAll(pageable).getContent();
    }
}
