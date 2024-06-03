package ra.jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.jpa.dto.request.CategoryCreateRequest;
import ra.jpa.entity.Category;
import ra.jpa.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api.rikkei.vn/v1/categories")
@RequiredArgsConstructor
public class ApiCategoryController {
    private final ICategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>>  getCategory(){
        List<Category> list = categoryService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Category> doAdd(@ModelAttribute CategoryCreateRequest request){
        return new ResponseEntity<>(categoryService.saveFormData(request),HttpStatus.CREATED);
    }

    // lay theo id
    //edit
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        try {
            Category category = categoryService.findById(id);
            return new ResponseEntity<>(category,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // update toàn phần
    @PutMapping("/{id}")
    public ResponseEntity<Category> doUpdate(@PathVariable Integer id,@ModelAttribute CategoryCreateRequest request){
        try {
            return new ResponseEntity<>(categoryService.updateFormData(request,id),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update 1 phần
    @PatchMapping("/{id}")
    public ResponseEntity<Category> doUpdatePatch(@PathVariable Integer id,@ModelAttribute CategoryCreateRequest request){
        try {
            return new ResponseEntity<>(categoryService.updateFormDataPath(request,id),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //xoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Integer id){
        try {
            categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
