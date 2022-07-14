package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ResponseDtoMapper<CategoryResponseDto, Category> responseMapper;
    private final RequestDtoMapper<CategoryRequestDto, Category> requestMapper;

    public CategoryController(CategoryService categoryService,
                              ResponseDtoMapper<CategoryResponseDto, Category> responseMapper,
                              RequestDtoMapper<CategoryRequestDto, Category> requestMapper) {
        this.categoryService = categoryService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return responseMapper.mapToDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody CategoryRequestDto dto) {
        Category category = requestMapper.mapToModel(dto);
        categoryService.add(category);
        return responseMapper.mapToDto(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto dto) {
        Category category = requestMapper.mapToModel(dto);
        category.setId(id);
        return responseMapper.mapToDto(categoryService.add(category));
    }
}
