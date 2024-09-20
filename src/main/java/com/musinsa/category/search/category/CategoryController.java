package com.musinsa.category.search.category;

import com.musinsa.category.search.common.SearchDto;
import com.musinsa.category.search.common.SearchService;
import com.musinsa.category.search.common.ApiException;
import com.musinsa.category.search.common.ApiExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final SearchService searchService;

    public CategoryController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public ResponseEntity<SearchDto> getCategories(@RequestParam(required = false,
            defaultValue = "all") String price) {

        if (!("all".equalsIgnoreCase(price) || "min".equalsIgnoreCase(price)
                || "max".equalsIgnoreCase(price))) {
            throw new ApiException(ApiExceptionCode.INVALID_DATA,
                    "Invalid value for 'price' parameter");
        }

        return ResponseEntity.status(HttpStatus.OK).body(searchService.getCategories(price));
    }


    @GetMapping("/{name}/products")
    public SearchDto getCategoriesByName(@PathVariable String name,
                                         @RequestParam(required = false, defaultValue = "all") String price) {

        if (!("all".equalsIgnoreCase(price) || "min".equalsIgnoreCase(price)
                || "max".equalsIgnoreCase(price))) {
            throw new ApiException(ApiExceptionCode.INVALID_DATA,
                    "Invalid value for 'price' parameter");
        }

        return searchService.getCategoriesByName(name, price);
    }
}
