package AACode.com.TNA.Agencies.model;

import AACode.com.TNA.Agencies.dto.AdvertisementDTO;
import AACode.com.TNA.Agencies.model.Enums.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @ElementCollection
    private List<AdvertisementDTO> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

}
