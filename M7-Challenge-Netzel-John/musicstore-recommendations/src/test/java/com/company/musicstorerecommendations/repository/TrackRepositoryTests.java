package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.TrackRecommendation;
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
public class TrackRepositoryTests {
    @Autowired
    private TrackRecommendationRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        TrackRecommendation expectedTrack = new TrackRecommendation();
        expectedTrack.setTrackId(1);
        expectedTrack.setUserId(1);
        expectedTrack.setLiked(true);

        // Act
        TrackRecommendation actualTrack = repository.save(expectedTrack);
        expectedTrack.setTrackId(actualTrack.getTrackId());

        // Assert
        assertEquals(expectedTrack, actualTrack);

        // UPDATE
        // Arrange
        expectedTrack.setLiked(false);

        // Act
        actualTrack = repository.save(expectedTrack);

        // Assert
        assertEquals(expectedTrack, actualTrack);

        // DELETE
        // Act
        repository.delete(expectedTrack);

        // Assert
        assertFalse(repository.findById(expectedTrack.getTrackId()).isPresent());
    }

    @Test
    public void ShouldFindAllRecords() {
        // Arrange
        TrackRecommendation track1 = new TrackRecommendation();
        track1.setTrackId(1);
        track1.setUserId(1);
        track1.setLiked(true);

        TrackRecommendation track2 = new TrackRecommendation();
        track2.setTrackId(2);
        track2.setUserId(2);
        track2.setLiked(false);

        // Act
        track1 = repository.save(track1);
        track2 = repository.save(track2);
        List<TrackRecommendation> tracks = repository.findAll();

        // Assert
        Set<TrackRecommendation> expectedSet = new HashSet<>(Arrays.asList(track1, track2));
        Set<TrackRecommendation> actualSet = new HashSet<>(tracks);
        assertEquals(expectedSet, actualSet);
    }
}
