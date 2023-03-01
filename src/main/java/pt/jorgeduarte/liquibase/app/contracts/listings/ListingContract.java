package pt.jorgeduarte.liquibase.app.contracts.listings;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingContract {

    private Long id;

    @NonNull
    @JsonProperty("title")
    private String title;

    @NonNull
    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created")
    private LocalDateTime created = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Nullable
    @JsonProperty("updated")
    private LocalDateTime updated;

    private Boolean enabled = true;
}
