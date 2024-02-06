package com.maomkt.product.repository.dao;

import com.maomkt.product.repository.entity.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Profile("!test")
@EnableScan
public interface ProductDao extends CrudRepository<Product, String> {

    List<Product> findAll();
}
