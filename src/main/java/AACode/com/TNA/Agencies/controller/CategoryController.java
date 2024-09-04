package AACode.com.TNA.Agencies.controller;

import AACode.com.TNA.Agencies.model.Category;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.service.CategoryService;
import AACode.com.TNA.Agencies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostMapping(path = "/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Category createCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }
}
