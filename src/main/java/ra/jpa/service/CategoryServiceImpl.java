package ra.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.jpa.dto.request.CategoryCreateRequest;
import ra.jpa.entity.Category;
import ra.jpa.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("khong tim thay id"));
    }

    @Override
    public void save(CategoryCreateRequest categoryCreateRequest) {
        Category category = Category.builder()
                .categoryName(categoryCreateRequest.getCategoryName())
                .description(categoryCreateRequest.getDescription())
                .status(true)
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void save(CategoryCreateRequest categoryCreateRequest, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("khong tim thay id"));
        category.setCategoryName(categoryCreateRequest.getCategoryName());
        category.setDescription(categoryCreateRequest.getDescription());
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveFormData(CategoryCreateRequest request) {
        Category category = Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .status(true)
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category updateFormData(CategoryCreateRequest request, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("khong tim thay id"));
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);

    }

    @Override
    public Category updateFormDataPath(CategoryCreateRequest request, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("khong tim thay id"));
        if (request.getCategoryName()!= null && !request.getCategoryName().isBlank()){
            category.setCategoryName(request.getCategoryName());
        }
       if (request.getDescription()!= null && !request.getDescription().isBlank()){
           category.setDescription(request.getDescription());
       }
        return categoryRepository.save(category);
    }
}
