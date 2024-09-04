package AACode.com.TNA.Agencies.controller;

import AACode.com.TNA.Agencies.model.Advertisement;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.request.CreateAdvertisementRequest;
import AACode.com.TNA.Agencies.service.AdvertisementService;
import AACode.com.TNA.Agencies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final UserService userService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService, UserService userService) {
        this.advertisementService = advertisementService;
        this.userService = userService;
    }

    @PostMapping(path = "/createAdd")
    public ResponseEntity<Advertisement> createAdvertisement(@RequestBody CreateAdvertisementRequest request,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Advertisement advertisement = advertisementService.createAdvertisement(request, request.getDistrictCategory(), user);

        return new ResponseEntity<>(advertisement, HttpStatus.CREATED);
    }
}
