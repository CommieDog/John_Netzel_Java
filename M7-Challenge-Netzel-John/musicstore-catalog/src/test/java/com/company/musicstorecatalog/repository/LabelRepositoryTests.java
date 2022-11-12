package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.model.Artist;
import com.company.musicstorecatalog.model.Label;
import org.junit.After;
import org.junit.Before;
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
public class LabelRepositoryTests {
    @Autowired
    private LabelRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        Label expectedLabel = new Label();
        expectedLabel.setName("Paul's Records");
        expectedLabel.setWebsite("www.paulsrecords.com");

        // Act
        Label actualLabel = repository.save(expectedLabel);
        expectedLabel.setLabelId(actualLabel.getLabelId());

        // Assert
        assertEquals(expectedLabel, actualLabel);

        // UPDATE
        // Arrange
        expectedLabel.setName("Big Daddy Music");

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
        Label label1 = new Label();
        label1.setName("Paul's Records");
        label1.setWebsite("www.paulsrecords.com");

        Label label2 = new Label();
        label2.setName("Big Daddy Music");
        label2.setWebsite("www.bigdaddymusic.com");

        // Act
        label1 = repository.save(label1);
        label2 = repository.save(label2);
        List<Label> labels = repository.findAll();

        // Assert
        Set<Label> expectedSet = new HashSet<>(Arrays.asList(label1, label2));
        Set<Label> actualSet = new HashSet<>(labels);
        assertEquals(expectedSet, actualSet);
    }
}
