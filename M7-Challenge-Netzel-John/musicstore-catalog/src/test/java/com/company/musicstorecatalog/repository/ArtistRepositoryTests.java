package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.model.Album;
import com.company.musicstorecatalog.model.Artist;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistRepositoryTests {
    @Autowired
    private ArtistRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        Artist expectedArtist = new Artist();
        expectedArtist.setName("Jonny Boy");
        expectedArtist.setInstagram("JonnyBoy");
        expectedArtist.setTwitter("@jonnyboy");

        // Act
        Artist actualArtist = repository.save(expectedArtist);
        expectedArtist.setArtistId(actualArtist.getArtistId());

        // Assert
        assertEquals(expectedArtist, actualArtist);

        // UPDATE
        // Arrange
        expectedArtist.setName("Jhonny-Boi");

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
        Artist artist1 = new Artist();
        artist1.setName("Jonny Boy");
        artist1.setInstagram("JonnyBoy");
        artist1.setTwitter("@jonnyboy");

        Artist artist2 = new Artist();
        artist2.setName("Jhonny-Boy");
        artist2.setInstagram("JhonnyBoi");
        artist2.setTwitter("@jhonnyboi");

        // Act
        artist1 = repository.save(artist1);
        artist2 = repository.save(artist2);
        List<Artist> artists = repository.findAll();

        // Assert
        Set<Artist> expectedSet = new HashSet<>(Arrays.asList(artist1, artist2));
        Set<Artist> actualSet = new HashSet<>(artists);
        assertEquals(expectedSet, actualSet);
    }
}
