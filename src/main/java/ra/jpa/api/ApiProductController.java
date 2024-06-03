package ra.jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.jpa.dto.request.ProductCreateRequest;
import ra.jpa.entity.Product;
import ra.jpa.service.IProductService;

import java.util.List;

@RestController   // controller cho dichj vu web new

// cac buoc dat ten api nhu sau
// domain: https//www.rikkei.vn
@RequestMapping("/api.rikkei.vn/v1/products")   // https//localhost8080/api.rikkei.vn/v1/products
@RequiredArgsConstructor
public class ApiProductController {
    private final IProductService productService;
    // lay ve danh sach san pham
    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> list = productService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(list,HttpStatus.OK);
            // tham số thứ nhất là phâ body gửi về
            // tham số thư 2 là 1 HttpStatus
        }
    }
    // them moi product
//    @PostMapping
//    public ResponseEntity<Product> doAddJson(@RequestBody Product product){
//        return new ResponseEntity<>(productService.saveJson(product), HttpStatus.CREATED);
//    }

    // them moi product
    @PostMapping
    public ResponseEntity<Product> doAddFromData(@ModelAttribute ProductCreateRequest product){
        return new ResponseEntity<>(productService.saveFromData(product) , HttpStatus.CREATED);
    }

    // lay theo id
    //edit
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        try {
            Product p = productService.findById(id);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update toàn phần
    @PutMapping("/{id}")
    public  ResponseEntity<Product> doUpdate(@PathVariable Integer id,@ModelAttribute ProductCreateRequest request){
        try {
            return new ResponseEntity<>(productService.updateFromData(request,id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update 1 phần
    @PatchMapping("/{id}")
    public ResponseEntity<Product> doUpdatePath(@PathVariable Integer id,@ModelAttribute ProductCreateRequest request){
        try {
            return new ResponseEntity<>(productService.updateFromDataPath(request,id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Integer id){// wildcard
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
