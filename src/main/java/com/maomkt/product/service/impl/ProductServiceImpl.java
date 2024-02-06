package com.maomkt.product.service.impl;


import com.maomkt.product.repository.dao.ProductDao;
import com.maomkt.product.repository.entity.Product;
import com.maomkt.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public List<Product> listAllQuotes() {
        return productDao.findAll();
    }

    @Override
    public Product getQuoteById(String id) {

        return productDao.findById(id).get();
    }

    @Override
    public void createQuote(Product quote) {
        productDao.save(quote);
    }

    @Override
    public void updateQuote(String id, Product quote) {
        if (productDao.findById(id).get().getId() == quote.getId()) {
            productDao.save(quote);
        } else {
            log.error("Trying to update with invalid quote id {}", id);
        }
    }

    @Override
    public void deleteQuote(String id) {
        productDao.deleteById(id);
    }
}
