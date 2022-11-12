package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.model.Track;
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
public class TrackRepositoryTests {
    @Autowired
    private TrackRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        Track expectedTrack = new Track();
        expectedTrack.setTitle("Some music");
        expectedTrack.setAlbumId(1);
        expectedTrack.setRunTime(321);

        // Act
        Track actualTrack = repository.save(expectedTrack);
        expectedTrack.setTrackId(actualTrack.getTrackId());

        // Assert
        assertEquals(expectedTrack, actualTrack);

        // UPDATE
        // Arrange
        expectedTrack.setTitle("Some More Music");

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
        Track track1 = new Track();
        track1.setTitle("Some Music");
        track1.setAlbumId(1);
        track1.setRunTime(321);

        Track track2 = new Track();
        track2.setTitle("Some More Music");
        track2.setAlbumId(1);
        track2.setRunTime(326);

        // Act
        track1 = repository.save(track1);
        track2 = repository.save(track2);
        List<Track> tracks = repository.findAll();

        // Assert
        Set<Track> expectedSet = new HashSet<>(Arrays.asList(track1, track2));
        Set<Track> actualSet = new HashSet<>(tracks);
        assertEquals(expectedSet, actualSet);
    }
}
