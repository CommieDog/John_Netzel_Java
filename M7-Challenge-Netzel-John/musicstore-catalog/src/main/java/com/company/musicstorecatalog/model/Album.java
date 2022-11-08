package com.company.musicstorecatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer albumId;

    @NotNull
    private String title;

    @NotNull
    @Column(name = "artist_id")
    private Integer artistId;

    @NotNull
    @Column(name = "release_date")
    private Date releaseDate;

    @NotNull
    @Column(name = "label_id")
    private Integer labelId;

    @NotNull
    @Column(name = "list_price")
    private BigDecimal listPrice;
}
