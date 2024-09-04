package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.Advertisement;
import AACode.com.TNA.Agencies.model.Category;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.request.CreateAdvertisementRequest;

import java.util.List;

public interface AdvertisementService {

    public Advertisement createAdvertisement(CreateAdvertisementRequest createAdvertisementRequest, Category category, User user);

    void deleteAdvertisement(Long AddsId) throws Exception;

    public List<Advertisement> getAdvertisements(String districtCategory);

    public List<Advertisement> searchAdvertisement(String keyword);

    public Advertisement updateAvailabilityStatus(Long AddsId) throws Exception;
}
