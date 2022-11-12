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
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlbumRepositoryTests {
    @Autowired
    private AlbumRepository repository;
    @Autowired
    private ArtistRepository artistRepository;

    @Before
    public void setUp() {
        // Album requires artist
        Artist artist = new Artist();
        artist.setName("Jonny Boy");
        artist.setInstagram("JonnyBoy");
        artist.setTwitter("@jonnyboy");

        artistRepository.save(artist);
    }

    @After
    public void cleanUp() {
        repository.deleteAll();
        artistRepository.deleteAll();
    }

    @Test
    public void ShouldCreateAndReadAndUpdateAndDeleteRecordById() {
        // CREATE and READ
        // Arrange
        Album expectedAlbum = new Album();
        expectedAlbum.setTitle("Album 1");
        expectedAlbum.setArtistId(1);
        expectedAlbum.setReleaseDate(LocalDate.of(2022, 1, 1));
        expectedAlbum.setLabelId(1);
        expectedAlbum.setListPrice(new BigDecimal("9.99"));

        // Act
        Album actualAlbum = repository.save(expectedAlbum);
        expectedAlbum.setAlbumId(actualAlbum.getAlbumId());

        // Assert
        assertEquals(expectedAlbum, actualAlbum);

        // UPDATE
        // Arrange
        expectedAlbum.setTitle("Anything Besides Album 1");

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
        Album album1 = new Album();
        album1.setTitle("Album 1");
        album1.setArtistId(1);
        album1.setReleaseDate(LocalDate.of(2022, 2, 2));
        album1.setLabelId(1);
        album1.setListPrice(new BigDecimal("9.99"));

        Album album2 = new Album();
        album2.setTitle("Album 2");
        album2.setArtistId(1);
        album2.setReleaseDate(LocalDate.of(2022, 3, 3));
        album2.setLabelId(1);
        album2.setListPrice(new BigDecimal("14.99"));

        // Act
        album1 = repository.save(album1);
        album2 = repository.save(album2);
        List<Album> albums = repository.findAll();

        // Assert
        Set<Album> expectedSet = new HashSet<>(Arrays.asList(album1, album2));
        Set<Album> actualSet = new HashSet<>(albums);
        assertEquals(expectedSet, actualSet);
    }
}
