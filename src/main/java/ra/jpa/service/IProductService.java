package ra.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.jpa.dto.request.ProductCreateRequest;
import ra.jpa.entity.Product;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll();
    Product findById(Integer id);
    void save(ProductCreateRequest request);   // webapp
    void save(ProductCreateRequest request,Integer id);   // webapp
    void deleteById(Integer id);

    Product saveJson(Product product);
    Product saveFromData(ProductCreateRequest request);
    Product updateFromData(ProductCreateRequest request,Integer id);
    Product updateFromDataPath(ProductCreateRequest request,Integer id);


}
