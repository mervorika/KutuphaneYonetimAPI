package com.mervorika.KutuphaneYonetimAPI.api;

import com.mervorika.KutuphaneYonetimAPI.core.config.IModelMapperService;
import com.mervorika.KutuphaneYonetimAPI.core.result.Result;
import com.mervorika.KutuphaneYonetimAPI.core.result.ResultData;
import com.mervorika.KutuphaneYonetimAPI.core.utilies.ResultHelper;
import com.mervorika.KutuphaneYonetimAPI.dto.request.category.CategorySaveRequest;
import com.mervorika.KutuphaneYonetimAPI.dto.response.CursorResponse;
import com.mervorika.KutuphaneYonetimAPI.dto.response.category.CategoryResponse;
import com.mervorika.KutuphaneYonetimAPI.entities.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.ICategoryService;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IModelMapperService modelMapper;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id){
        Category category=this.categoryService.getById(id);
        CategoryResponse categoryResponse=this.modelMapper.forResponse().map(category,CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }
   @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save (@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory=this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        CategoryResponse categoryResponse= this.modelMapper.forResponse().map(saveCategory,CategoryResponse.class);
        return ResultHelper.created(categoryResponse);
    }

    @PutMapping("/{categoryId}/books/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Category assignBookToCategory(@PathVariable("categoryId") int categoryId,@PathVariable("bookId") int bookId){
        return this.categoryService.assignBookToCategory(categoryId,bookId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(@RequestParam(name = "page",required = false,defaultValue = "0") int page, @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize){
        Page<Category> categoryPage=this.categoryService.cursor(page,pageSize);
        Page<CategoryResponse> categoryResponsePage=categoryPage
                .map(category -> this.modelMapper.forResponse().map(category,CategoryResponse.class));
        return ResultHelper.cursor(categoryResponsePage);
    }

    /*@PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update (@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
       this.categoryService.getById(categoryUpdateRequest.getId());
        Category updateCategory=this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);
        CategoryResponse categoryResponse= this.modelMapper.forResponse().map(updateCategory,CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
       if(this.categoryService.delete(id)){
           return ResultHelper.success();
       }
        return ResultHelper.error2();
    }
}
