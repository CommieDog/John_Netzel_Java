package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.AlbumRecommendation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlbumRepositoryTests {
    @Autowired
    private AlbumRecommendationRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        AlbumRecommendation expectedAlbum = new AlbumRecommendation();
        expectedAlbum.setAlbumId(1);
        expectedAlbum.setUserId(1);
        expectedAlbum.setLiked(true);

        // Act
        AlbumRecommendation actualAlbum = repository.save(expectedAlbum);
        expectedAlbum.setAlbumId(actualAlbum.getAlbumId());

        // Assert
        assertEquals(expectedAlbum, actualAlbum);

        // UPDATE
        // Arrange
        expectedAlbum.setLiked(false);

        // Act
        actualAlbum = repository.save(expectedAlbum);

        // Assert
        assertEquals(expectedAlbum, actualAlbum);

        // DELETE
        // Act
        repository.delete(expectedAlbum);

        // Assert
        assertFalse(repository.findById(expectedAlbum.getAlbumId()).isPresent());
    }

    @Test
    public void ShouldFindAllRecords() {
        // Arrange
        AlbumRecommendation album1 = new AlbumRecommendation();
        album1.setAlbumId(1);
        album1.setUserId(1);
        album1.setLiked(true);

        AlbumRecommendation album2 = new AlbumRecommendation();
        album2.setAlbumId(2);
        album2.setUserId(2);
        album2.setLiked(false);

        // Act
        album1 = repository.save(album1);
        album2 = repository.save(album2);
        List<AlbumRecommendation> albums = repository.findAll();

        // Assert
        Set<AlbumRecommendation> expectedSet = new HashSet<>(Arrays.asList(album1, album2));
        Set<AlbumRecommendation> actualSet = new HashSet<>(albums);
        assertEquals(expectedSet, actualSet);
    }
}
