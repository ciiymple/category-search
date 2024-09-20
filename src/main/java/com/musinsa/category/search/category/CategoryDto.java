package com.musinsa.category.search.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private Long id;
    private String name;

    private CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryDto create(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static CategoryDto create(Long id, String name) {
        return new CategoryDto(id, name);
    }
}
