package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.dto.AdvertisementDTO;
import AACode.com.TNA.Agencies.model.Advertisement;
import AACode.com.TNA.Agencies.model.Category;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.request.CreateAdvertisementRequest;

import java.util.List;

public interface AdvertisementService {

    public Advertisement createAdvertisement(CreateAdvertisementRequest createAdvertisementRequest, Category category, User user);

    public Advertisement updateAdvertisement(Long advertisementId, CreateAdvertisementRequest updateAdvertisement) throws Exception;

    void deleteAdvertisement(Long AddsId) throws Exception;

    public List<Advertisement> getAllAdvertisements();

    public List<Advertisement> searchAdvertisement(String keyword);

    public Advertisement updateAvailabilityStatus(Long AddsId) throws Exception;

    public Advertisement findAdvertisementById(Long id) throws Exception;

    public List<Advertisement> getAdvertisementByUserId(Long userId) throws Exception;

    public AdvertisementDTO addToFavorite(Long advertisementId, User user) throws Exception;
}
