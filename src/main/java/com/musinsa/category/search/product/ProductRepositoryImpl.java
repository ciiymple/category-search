package com.musinsa.category.search.product;

import com.musinsa.category.search.common.SearchService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ProductDto> findProductsWithUniqueMinAndMaxPrice(SearchService.SearchCondition searchCondition) {

        String jpql = "SELECT CATEGORY_ID, CATEGORY_NAME, BRAND_ID, BRAND_NAME,\n" +
                "PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, MIN_RANK, MAX_RANK\n" +
                "FROM (" +
                "SELECT C.id AS CATEGORY_ID, C.name AS CATEGORY_NAME, B.id AS BRAND_ID,\n" +
                "        B.name AS BRAND_NAME, P.id AS PRODUCT_ID, P.name AS PRODUCT_NAME, P.price AS PRODUCT_PRICE,\n" +
                "        RANK() OVER (PARTITION BY P.category.id ORDER BY (P.price, P.id)) AS MIN_RANK,\n" +
                "        RANK() OVER (PARTITION BY P.category.id ORDER BY (P.price, P.id) DESC) AS MAX_RANK\n" +
                " FROM Product P INNER JOIN P.category C " +
                " INNER JOIN P.brand B\n" + searchCondition.getCondition().toString() +
                ") RESULT WHERE RESULT.MIN_RANK = 1 OR RESULT.MAX_RANK=1 ORDER BY RESULT.CATEGORY_ID";

        TypedQuery<ProductDto> query = entityManager.createQuery(jpql, ProductDto.class);

        for (int i = 0; i < searchCondition.getQueryParams().size(); i++) {
            query.setParameter(i + 1, searchCondition.getQueryParams().get(i));
        }

        return query.getResultList();
    }

    @Override
    public List<ProductDto> findProductsWithMinAndMaxPrice(SearchService.SearchCondition searchCondition) {

        String jpql = "SELECT CATEGORY_ID, CATEGORY_NAME, BRAND_ID, BRAND_NAME,\n" +
                "PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, MIN_RANK, MAX_RANK\n" +
                "FROM (" +
                "SELECT C.id AS CATEGORY_ID, C.name AS CATEGORY_NAME, B.id AS BRAND_ID,\n" +
                "        B.name AS BRAND_NAME, P.id AS PRODUCT_ID, P.name AS PRODUCT_NAME, P.price AS PRODUCT_PRICE,\n" +
                "        RANK() OVER (PARTITION BY P.category.id ORDER BY P.price) AS MIN_RANK,\n" +
                "        RANK() OVER (PARTITION BY P.category.id ORDER BY P.price DESC) AS MAX_RANK\n" +
                " FROM Product P INNER JOIN P.category C " +
                " INNER JOIN P.brand B\n" + searchCondition.getCondition().toString() +
                ") RESULT WHERE RESULT.MIN_RANK = 1 OR RESULT.MAX_RANK=1 ORDER BY RESULT.CATEGORY_ID";

        TypedQuery<ProductDto> query = entityManager.createQuery(jpql, ProductDto.class);

        for (int i = 0; i < searchCondition.getQueryParams().size(); i++) {
            query.setParameter(i + 1, searchCondition.getQueryParams().get(i));
        }

        return query.getResultList();
    }
}
