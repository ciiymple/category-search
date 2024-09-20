package com.musinsa.category.search.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.function.Supplier;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchDto {
    private SearchPayload min;
    private SearchPayload max;

    public SearchDto(Supplier<SearchPayload> minSupplier, Supplier<SearchPayload> maxSupplier, String price) {
        switch (price.toLowerCase()) {
            case "min":
                this.min = minSupplier.get();
                break;
            case "max":
                this.max = maxSupplier.get();
                break;
            default:
                this.min = minSupplier.get();
                this.max = maxSupplier.get();
                break;
        }
    }
}
