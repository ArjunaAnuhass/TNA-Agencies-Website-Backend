package AACode.com.TNA.Agencies.controller;

import AACode.com.TNA.Agencies.dto.AdvertisementDTO;
import AACode.com.TNA.Agencies.model.Advertisement;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.request.CreateAdvertisementRequest;
import AACode.com.TNA.Agencies.response.MessageResponse;
import AACode.com.TNA.Agencies.service.AdvertisementService;
import AACode.com.TNA.Agencies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(path = "/{id}/deleteAdd")
    public ResponseEntity<MessageResponse> deleteAdvertisement(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        advertisementService.deleteAdvertisement(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Advertisement deleted Successfully...");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/AllAdds")
    public ResponseEntity<List<Advertisement>> getAllAdvertisement(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Advertisement> advertisements = advertisementService.getAllAdvertisements();

        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Advertisement>> searchAdvertisement(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Advertisement> advertisements = advertisementService.searchAdvertisement(keyword);

        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/add-to-favorite")
    public ResponseEntity<AdvertisementDTO> addToFavorite(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        AdvertisementDTO advertisementDTO = advertisementService.addToFavorite(id, user);

        return new ResponseEntity<>(advertisementDTO, HttpStatus.OK);
    }
}
