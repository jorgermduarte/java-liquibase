package pt.jorgeduarte.liquibase.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LISTINGS_SEQ")
    @SequenceGenerator(name = "LISTINGS_SEQ", sequenceName = "LISTINGS_SEQ", allocationSize = 20)
    private Long id;

    private String title;

    private String description;

    private Double price;

    private LocalDateTime created;

    private LocalDateTime updated;

    private Boolean enabled;
}
