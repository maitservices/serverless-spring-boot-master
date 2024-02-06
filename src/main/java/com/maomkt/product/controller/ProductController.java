package com.maomkt.product.controller;


import com.maomkt.product.model.ProductForm;
import com.maomkt.product.model.Response;
import com.maomkt.product.repository.entity.Product;
import com.maomkt.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
@Slf4j
public class ProductController {

    @Autowired
    private ProductService quoteService;

    @RequestMapping(path = "/product",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getQuotes() {
        List<Product> result = quoteService.listAllQuotes();
        return result;
    }

    @RequestMapping(path = "/product/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getQuote(@PathVariable("id") String id) {
        Product result = quoteService.getQuoteById(id);
        return result;
    }

    @RequestMapping(path = "/product",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createQuote(@RequestBody ProductForm quoteForm) {
        Response response = new Response();
        try {
            Product quote = new Product(quoteForm);
            quoteService.createQuote(quote);
            response.setStatus("OK");
            response.setMessage("Quote created");
        } catch (RuntimeException ex) {
            log.error("Error during quote creation!");
            log.error("Exception Message {}, Cause {}", ex.getLocalizedMessage(), ex.getCause().toString());
            response.setStatus("FAILED");
            response.setMessage("Unable to create product");
        }

        return response;
    }

    @RequestMapping(path = "/product/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateQuote(@PathVariable("id") String id, @RequestBody ProductForm quoteForm) {
        Response response = new Response();

        try {
            Product quote = new Product(quoteForm);
            quote.setId(id);
            quoteService.updateQuote(id, quote);
            response.setStatus("OK");
            response.setMessage("product updated");
        } catch (RuntimeException ex) {
            log.error("Error during product update!");
            log.error("Exception Message {}, Cause {}", ex.getLocalizedMessage(), ex.getCause().toString());
            response.setStatus("FAILED");
            response.setMessage("Unable to update product");
        }

        return response;
    }

    @RequestMapping(path = "/product/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteQuote(@PathVariable("id") String id) {
        Response response = new Response();
        try {
            quoteService.deleteQuote(id);
            response.setStatus("OK");
            response.setMessage("product Deleted");
        } catch (RuntimeException ex) {
            log.error("Error during product delete!");
            log.error("Exception Message {}, Cause {}", ex.getLocalizedMessage(), ex.getCause().toString());
            response.setStatus("FAILED");
            response.setMessage("Unable to delete product");
        }
        return response;
    }


}
