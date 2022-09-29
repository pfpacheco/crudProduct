package com.envixo.challenge.repository.intf;

import com.envixo.challenge.entity.Product;

import java.util.List;

public interface IProductRepository {

    Product save(Product product);

    Product findById(Long id);

    Product findByCategory(String category);

    Product findByTag(String tag);

    List<Product> findAll();

    void delete(Product product);

    void delete(Long id);
}
