package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.dto.AdvertisementDTO;
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
import java.util.Optional;

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
    public Advertisement updateAdvertisement(Long advertisementId, CreateAdvertisementRequest updateAdvertisement) throws Exception {

        Advertisement advertisement = findAdvertisementById(advertisementId);

        if (advertisement.getTitle()!=null){
            advertisement.setTitle(updateAdvertisement.getTitle());
        }
        if (advertisement.getDescription()!=null){
            advertisement.setDescription(updateAdvertisement.getDescription());
        }
        if (advertisement.getHouseSize()!=null){
            advertisement.setHouseSize(updateAdvertisement.getHouseSize());
        }
        if (advertisement.getLandSize()!=null){
            advertisement.setLandSize(updateAdvertisement.getLandSize());
        }
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisement(Long AddsId) throws Exception {

        Advertisement advertisement = findAdvertisementById(AddsId);

        advertisementRepository.delete(advertisement);
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> searchAdvertisement(String keyword) {
        return advertisementRepository.findBySearchQuery(keyword);
    }

    @Override
    public Advertisement updateAvailabilityStatus(Long AddsId) throws Exception {

        Advertisement advertisement = findAdvertisementById(AddsId);
        advertisement.setAvailability(!advertisement.isAvailability());

        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement findAdvertisementById(Long id) throws Exception {

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(id);

        if (optionalAdvertisement.isEmpty()){
            throw new Exception("Advertisement Not Found!!!");
        }
        return optionalAdvertisement.get();
    }

    @Override
    public List<Advertisement> getAdvertisementByUserId(Long userId) throws Exception {

        List<Advertisement> advertisement = advertisementRepository.findByCustomerId(userId);

        if (advertisement==null){
            throw new Exception("Advertisement not found with User Id" + userId);
        }
        return advertisement;
    }

    @Override
    public AdvertisementDTO addToFavorite(Long advertisementId, User user) throws Exception {

        Advertisement advertisement = findAdvertisementById(advertisementId);

        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        advertisementDTO.setTitle(advertisement.getTitle());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setImages(advertisement.getImages());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setId(advertisementId);

        boolean isFavorite = false;
        List<AdvertisementDTO> favorites = user.getFavorites();

        for (AdvertisementDTO favorite : favorites){
            if (favorite.getId().equals(advertisementId)){
                isFavorite = true;
                break;
            }
        }

        if (isFavorite){
            favorites.removeIf(favorite -> favorite.getId().equals(advertisementId));
        }
        else {
            favorites.add(advertisementDTO);
        }

        userRepository.save(user);

        return advertisementDTO;
    }
}
