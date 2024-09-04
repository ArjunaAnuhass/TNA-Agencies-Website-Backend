package AACode.com.TNA.Agencies.request;

import AACode.com.TNA.Agencies.model.Address;
import AACode.com.TNA.Agencies.model.Category;
import AACode.com.TNA.Agencies.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateAdvertisementRequest {

    private String title;
    private Long landSize;
    private Long houseSize;
    private int baths;
    private int beds;
    private List<String> images;
    private String description;
    private Long price;

    private ContactInformation contactInformation;
    private Address address;
    private Category districtCategory;
}
