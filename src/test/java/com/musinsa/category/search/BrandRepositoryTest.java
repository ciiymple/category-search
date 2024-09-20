package com.musinsa.category.search;

import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.brand.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    @Disabled
    public void 브랜드_저장() {
        Brand brand = new Brand("Test 브랜드");
        Brand savedBrand = brandRepository.save(brand);

        Optional<Brand> foundBrand = brandRepository.findById(savedBrand.getId());
        Assertions.assertTrue(foundBrand.isPresent());
        Brand f_Brand = foundBrand.get();
        Assertions.assertEquals(savedBrand.getId(), f_Brand.getId());
        Assertions.assertEquals(savedBrand.getName(), f_Brand.getName());
    }
}
