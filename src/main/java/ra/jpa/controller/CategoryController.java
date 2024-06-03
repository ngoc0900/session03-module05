package ra.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.jpa.dto.request.CategoryCreateRequest;
import ra.jpa.entity.Category;
import ra.jpa.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/list")
    private String list(@PageableDefault(page = 0,size = 4 )Pageable pageable, Model model){
        model.addAttribute("page", categoryService.findAll(pageable));
        model.addAttribute("category",new CategoryCreateRequest());
        return "category";
    }

    @PostMapping("/add")
    public  String doAdd (@ModelAttribute("category") CategoryCreateRequest request){
        categoryService.save(request);
        return "redirect:/category/list";
    }

    @GetMapping("/delete")
    private String delete(@RequestParam Integer id){
        categoryService.deleteById(id);
        return "redirect:/category/list";
    }
    @GetMapping("/edit")
    private String edit(@RequestParam Integer id, Model model){
        Category category = categoryService.findById(id);
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
        model.addAttribute("category",request);
        model.addAttribute("id",category.getId());
        return "category-edit";
    }
    @PostMapping("/update")
    public  String doUpdate (@ModelAttribute("category") CategoryCreateRequest request, @RequestParam Integer id ){
        categoryService.save(request,id);
        return "redirect:/category/list";
    }
}
