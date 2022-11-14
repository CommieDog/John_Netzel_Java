package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.LabelRecommendation;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LabelRecommendationRepositoryTests {
    @Autowired
    private LabelRecommendationRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        LabelRecommendation expectedLabel = new LabelRecommendation();
        expectedLabel.setLabelId(1);
        expectedLabel.setUserId(1);
        expectedLabel.setLiked(true);

        // Act
        LabelRecommendation actualLabel = repository.save(expectedLabel);
        expectedLabel.setLabelId(actualLabel.getLabelId());

        // Assert
        assertEquals(expectedLabel, actualLabel);

        // UPDATE
        // Arrange
        expectedLabel.setLiked(false);

        // Act
        actualLabel = repository.save(expectedLabel);

        // Assert
        assertEquals(expectedLabel, actualLabel);

        // DELETE
        // Act
        repository.delete(expectedLabel);

        // Assert
        assertFalse(repository.findById(expectedLabel.getLabelId()).isPresent());
    }

    @Test
    public void ShouldFindAllRecords() {
        // Arrange
        LabelRecommendation label1 = new LabelRecommendation();
        label1.setLabelId(1);
        label1.setUserId(1);
        label1.setLiked(true);

        LabelRecommendation label2 = new LabelRecommendation();
        label2.setLabelId(2);
        label2.setUserId(2);
        label2.setLiked(false);

        // Act
        label1 = repository.save(label1);
        label2 = repository.save(label2);
        List<LabelRecommendation> labels = repository.findAll();

        // Assert
        Set<LabelRecommendation> expectedSet = new HashSet<>(Arrays.asList(label1, label2));
        Set<LabelRecommendation> actualSet = new HashSet<>(labels);
        assertEquals(expectedSet, actualSet);
    }
}
