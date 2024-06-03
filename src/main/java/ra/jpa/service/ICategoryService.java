package ra.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.jpa.dto.request.CategoryCreateRequest;
import ra.jpa.entity.Category;

import java.util.List;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);
    Category findById(Integer id);
    void save(CategoryCreateRequest categoryCreateRequest);
    void save(CategoryCreateRequest categoryCreateRequest,Integer id);
    void deleteById(Integer id);

    List<Category> findAll();

    Category saveFormData(CategoryCreateRequest request);
    Category updateFormData(CategoryCreateRequest request,Integer id);
    Category updateFormDataPath(CategoryCreateRequest request,Integer id);



}
