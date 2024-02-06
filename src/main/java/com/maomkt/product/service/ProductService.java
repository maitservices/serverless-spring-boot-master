package com.maomkt.product.service;


import com.maomkt.product.repository.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllQuotes();

    Product getQuoteById(String id);

    void createQuote(Product quoteForm);

    void updateQuote(String id, Product quote);

    void deleteQuote(String id);
}
