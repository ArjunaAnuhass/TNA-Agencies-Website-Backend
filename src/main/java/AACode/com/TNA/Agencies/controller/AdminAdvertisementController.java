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

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/advertisement")
public class AdminAdvertisementController {

    private final AdvertisementService advertisementService;
    private final UserService userService;

    @Autowired
    public AdminAdvertisementController(AdvertisementService advertisementService, UserService userService) {
        this.advertisementService = advertisementService;
        this.userService = userService;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Advertisement> updateAdvertisement(@RequestBody CreateAdvertisementRequest request,
                                                             @RequestHeader("Authorization") String jwt,
                                                             @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Advertisement advertisement = advertisementService.updateAdvertisement(id, request);

        return new ResponseEntity<>(advertisement, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}/status")
    public ResponseEntity<Advertisement> updateAvailability(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Advertisement advertisement = advertisementService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(advertisement, HttpStatus.OK);
    }

    @GetMapping(path = "/userAdvertisement")
    public ResponseEntity<List<Advertisement>> findAdvertisementByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Advertisement> advertisement = advertisementService.getAdvertisementByUserId(user.getId());

        return new ResponseEntity<>(advertisement, HttpStatus.OK);
    }
}
