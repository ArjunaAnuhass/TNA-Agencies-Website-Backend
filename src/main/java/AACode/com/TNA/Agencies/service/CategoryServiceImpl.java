package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.Category;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(UserService userService, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Optional<User> user = userService.findUserById(userId);

        Category category = new Category();
        category.setName(name);
        category.setUser(user.get());
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        return null;
    }
}
