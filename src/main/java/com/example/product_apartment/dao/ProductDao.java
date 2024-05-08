package com.example.product_apartment.dao;

import com.example.product_apartment.model.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    private JdbcTemplate template;

    @Autowired
    public ProductDao(JdbcTemplate template) {
        this.template = template;
    }

    public List<ProductEntity> test() {
        String query = "SELECT value FROM product_discount WHERE product_discount.discount = 'сезонная скидка'";

        return template.query(query, (resultSet, rowNum) -> {
            ProductEntity product = new ProductEntity();
            product.setValue(resultSet.getInt("value"));
            return product;
        });
    }
}
