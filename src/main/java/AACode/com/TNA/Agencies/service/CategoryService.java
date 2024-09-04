package AACode.com.TNA.Agencies.service;

import AACode.com.TNA.Agencies.model.Category;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
