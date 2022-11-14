package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.ArtistRecommendation;
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
public class ArtistRecommendationRepositoryTests {
    @Autowired
    private ArtistRecommendationRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        ArtistRecommendation expectedArtist = new ArtistRecommendation();
        expectedArtist.setArtistId(1);
        expectedArtist.setUserId(1);
        expectedArtist.setLiked(true);

        // Act
        ArtistRecommendation actualArtist = repository.save(expectedArtist);
        expectedArtist.setArtistId(actualArtist.getArtistId());

        // Assert
        assertEquals(expectedArtist, actualArtist);

        // UPDATE
        // Arrange
        expectedArtist.setLiked(false);

        // Act
        actualArtist = repository.save(expectedArtist);

        // Assert
        assertEquals(expectedArtist, actualArtist);

        // DELETE
        // Act
        repository.delete(expectedArtist);

        // Assert
        assertFalse(repository.findById(expectedArtist.getArtistId()).isPresent());
    }

    @Test
    public void ShouldFindAllRecords() {
        // Arrange
        ArtistRecommendation artist1 = new ArtistRecommendation();
        artist1.setArtistId(1);
        artist1.setUserId(1);
        artist1.setLiked(true);

        ArtistRecommendation artist2 = new ArtistRecommendation();
        artist2.setArtistId(2);
        artist2.setUserId(2);
        artist2.setLiked(false);

        // Act
        artist1 = repository.save(artist1);
        artist2 = repository.save(artist2);
        List<ArtistRecommendation> artists = repository.findAll();

        // Assert
        Set<ArtistRecommendation> expectedSet = new HashSet<>(Arrays.asList(artist1, artist2));
        Set<ArtistRecommendation> actualSet = new HashSet<>(artists);
        assertEquals(expectedSet, actualSet);
    }
}
