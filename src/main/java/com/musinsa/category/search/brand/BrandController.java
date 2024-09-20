package com.musinsa.category.search.brand;

import com.musinsa.category.search.common.ApiException;
import com.musinsa.category.search.common.ApiExceptionCode;
import com.musinsa.category.search.common.SearchDto;
import com.musinsa.category.search.common.SearchService;
import com.musinsa.category.search.product.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    private final SearchService searchService;

    public BrandController(BrandService brandService, SearchService searchService) {
        this.brandService = brandService;
        this.searchService = searchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.get(id));
    }

    @GetMapping("/{name}/products")
    public ResponseEntity<SearchDto> getBrandsByName(@PathVariable String name,
                                                     @RequestParam(required = false,
                                                             defaultValue = "all") String price) {

        if (!("all".equalsIgnoreCase(price) || "min".equalsIgnoreCase(price)
                || "max".equalsIgnoreCase(price))) {
            throw new ApiException(ApiExceptionCode.INVALID_DATA,
                    "Invalid value for 'price' parameter");
        }

        return ResponseEntity.status(HttpStatus.OK).body(searchService.getBrandsByName(name, price));
    }

    @PostMapping
    public ResponseEntity<BrandDto> create(@RequestBody BrandDto brandDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(brandDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> update(@PathVariable Long id, @RequestBody BrandDto brandDto) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(id, brandDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandDto> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.delete(id));
    }

    @PostMapping("/{brandId}/products")
    public ResponseEntity<List<ProductDto>> create(@PathVariable Long brandId,
                                                   @RequestBody List<ProductDto> productDtoList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(brandId, productDtoList));
    }
}
