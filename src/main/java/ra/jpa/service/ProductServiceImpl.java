package ra.jpa.service;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.jpa.dto.request.ProductCreateRequest;
import ra.jpa.entity.Category;
import ra.jpa.entity.Product;
import ra.jpa.repository.CategoryRepository;
import ra.jpa.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements IProductService{
    @Value("${uploadPath}")
    private String uploadPath;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Product> findAll(Pageable pageable) {
        // PageAble
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    @Override
    public void save(ProductCreateRequest request) {
        // xử lí upload
        // chuyển đổi DTO -> entity
        String uploadFolder = servletContext.getRealPath("/uploads");
        File folderUpload = new File(uploadFolder);
        if (!folderUpload.exists()){
            folderUpload.mkdirs();
        }
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .description(request.getDescription())
                .createdAt(new Date())
                .isHidden(false)
                .build();
        if (request.getFile().getSize() !=0){
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadPath+File.separator+fileName));
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadFolder+File.separator+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImageUrl(fileName);
        }
        //luwuu lại
        productRepository.save(product);
    }

    @Override
    public void save(ProductCreateRequest request, Integer id) {
        // xử lí upload
        // chuyển đổi DTO -> entity
        String uploadFolder = servletContext.getRealPath("/uploads");
        File folderUpload = new File(uploadFolder);
        if (!folderUpload.exists()){
            folderUpload.mkdirs();
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setDescription(request.getDescription());
        if (request.getFile().getSize() !=0){
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadPath+File.separator+fileName));
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadFolder+File.separator+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImageUrl(fileName);
        }
        //luwuu lại
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveJson(Product product) {
        product.setCreatedAt(new Date());
        product.setIsHidden(false);
        return productRepository.save(product);
    }

    @Override
    public Product saveFromData(ProductCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("Khong tim thay categoryId"));
        String uploadFolder = servletContext.getRealPath("/uploads");
        File folderUpload = new File(uploadFolder);
        if (!folderUpload.exists()){
            folderUpload.mkdirs();
        }
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .description(request.getDescription())
                .createdAt(new Date())
                .isHidden(false)
                .category(category)
                .build();
        if (request.getFile().getSize() !=0){
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadPath+File.separator+fileName));
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadFolder+File.separator+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImageUrl(fileName);
        }
        //luwuu lại

        return productRepository.save(product);
    }

    @Override
    public Product updateFromData(ProductCreateRequest request, Integer id) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("Khong tim thay categoryId"));

        // xử lí upload
        // chuyển đổi DTO -> entity
        String uploadFolder = servletContext.getRealPath("/uploads");
        File folderUpload = new File(uploadFolder);
        if (!folderUpload.exists()){
            folderUpload.mkdirs();
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        if (request.getFile().getSize() !=0){
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadPath+File.separator+fileName));
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadFolder+File.separator+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImageUrl(fileName);
        }
        //luwuu lại
        return productRepository.save(product);
    }

    @Override
    public Product updateFromDataPath(ProductCreateRequest request, Integer id) {



        // xử lí upload
        // chuyển đổi DTO -> entity
        String uploadFolder = servletContext.getRealPath("/uploads");
        File folderUpload = new File(uploadFolder);
        if (!folderUpload.exists()){
            folderUpload.mkdirs();
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        if (request.getName()!= null && !request.getName().isBlank()){
            product.setName(request.getName());
        }
        if (request.getPrice() != null && request.getPrice()>0){
            product.setPrice(request.getPrice());
        }
        if (request.getStock()!= null){
            product.setStock(request.getStock());
        }
        if (request.getDescription()!= null && !request.getDescription().isBlank()){
            product.setDescription(request.getDescription());
        }
        if (request.getCategoryId()!= null){
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("Khong tim thay categoryId"));
            product.setCategory(category);
        }


         if (request.getFile()!= null && request.getFile().getSize() !=0){
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadPath+File.separator+fileName));
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadFolder+File.separator+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImageUrl(fileName);
        }
        return productRepository.save(product);
    }
}
