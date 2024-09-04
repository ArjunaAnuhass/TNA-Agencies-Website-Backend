package AACode.com.TNA.Agencies.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User customer;

    private String title;
    private Long landSize;
    private Long houseSize;
    private int baths;
    private int beds;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private String description;
    private Long price;
    private boolean availability;
    private LocalDateTime publishedDate;

    @Embedded
    private ContactInformation contactInformation;

    @OneToOne
    private Address address;

    @ManyToOne
    private Category districtCategory;

}
