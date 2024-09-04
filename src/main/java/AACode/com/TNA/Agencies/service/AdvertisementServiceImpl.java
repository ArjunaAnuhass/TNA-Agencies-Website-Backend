package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.Address;
import AACode.com.TNA.Agencies.model.Advertisement;
import AACode.com.TNA.Agencies.model.Category;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.repo.AddressRepository;
import AACode.com.TNA.Agencies.repo.AdvertisementRepository;
import AACode.com.TNA.Agencies.repo.UserRepository;
import AACode.com.TNA.Agencies.request.CreateAdvertisementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{

    private final UserService userService;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(UserService userService, AddressRepository addressRepository, UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userService = userService;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Advertisement createAdvertisement(CreateAdvertisementRequest createAdvertisementRequest, Category category, User user) {

        Address address = addressRepository.save(createAdvertisementRequest.getAddress());

        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(createAdvertisementRequest.getTitle());
        advertisement.setLandSize(createAdvertisementRequest.getLandSize());
        advertisement.setHouseSize(createAdvertisementRequest.getHouseSize());
        advertisement.setBaths(createAdvertisementRequest.getBaths());
        advertisement.setBeds(createAdvertisementRequest.getBeds());
        advertisement.setImages(createAdvertisementRequest.getImages());
        advertisement.setDescription(createAdvertisementRequest.getDescription());
        advertisement.setPrice(createAdvertisementRequest.getPrice());
        advertisement.setPublishedDate(LocalDateTime.now());

        advertisement.setContactInformation(createAdvertisementRequest.getContactInformation());
        advertisement.setAddress(address);
        advertisement.setDistrictCategory(category);
        advertisement.setCustomer(user);

        return advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisement(Long AddsId) throws Exception {

    }

    @Override
    public List<Advertisement> getAdvertisements(String districtCategory) {
        return null;
    }

    @Override
    public List<Advertisement> searchAdvertisement(String keyword) {
        return null;
    }

    @Override
    public Advertisement updateAvailabilityStatus(Long AddsId) throws Exception {
        return null;
    }
}
