package com.musinsa.category.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.brand.BrandController;
import com.musinsa.category.search.brand.BrandDto;
import com.musinsa.category.search.brand.BrandService;
import com.musinsa.category.search.category.Category;
import com.musinsa.category.search.common.SearchService;
import com.musinsa.category.search.product.ProductDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @MockBean
    private SearchService searchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    public void 브랜드_조회() throws Exception {
        BrandDto brandDto = BrandDto.create(new Brand(1L, "A"));
        when(brandService.get(1L)).thenReturn(brandDto); //Option
        mockMvc.perform(MockMvcRequestBuilders.get("/brands/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(brandDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(brandDto.getName()));
    }

    @Test
    @Disabled
    public void 브랜드_생성() throws Exception {
        BrandDto newBrand = BrandDto.create(new Brand(null, "new Brand"));
        BrandDto createdBrand = BrandDto.create(new Brand(10L, "new Brand"));

        when(brandService.create(any(BrandDto.class))).thenReturn(createdBrand);
        mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBrand)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(createdBrand.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(createdBrand.getName()));
    }

    @Test
    @Disabled
    public void 브랜드_상품_생성() throws Exception {

        Brand brand = new Brand(4L, "D");
        Category category = new Category(1L, "상의");
        BrandDto brandDto = BrandDto.create(brand);
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto(73L, "나이키 상의 A",
                3000d, category.getId(), category.getName(), brand.getId(), brand.getName()));
        productDtoList.add(new ProductDto(74L, "나이키 상의 B",
                2000d, category.getId(), category.getName(), brand.getId(), brand.getName()));
        productDtoList.add(new ProductDto(75L, "나이키 상의 C",
                1000d, category.getId(), category.getName(), brand.getId(), brand.getName()));

        when(brandService.create(eq(brandDto.getId()), anyList())).thenReturn(productDtoList);

        mockMvc.perform(MockMvcRequestBuilders.post("/brands/{brandId}/products", brandDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDtoList)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(productDtoList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(productDtoList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].price").value(productDtoList.get(0).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].brand.id").value(brand.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value(productDtoList.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value(productDtoList.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].price").value(productDtoList.get(1).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].brand.id").value(brand.getId()));

    }
}
