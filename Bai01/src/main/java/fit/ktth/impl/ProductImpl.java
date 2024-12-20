/*
 * @ (#) ProductImpl.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import fit.ktth.dao.ProductDAO;
import fit.ktth.entity.Product;
import fit.ktth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAllProduct() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findProductByCategory(int category_id) {
        return productDAO.findProductByCategory_Id(category_id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void delete(int id) {
        productDAO.deleteById(id);
    }

    @Override
    public Product findProduct(int id) {
        return productDAO.findById(id);
    }

    @Transactional
    @Override
    public Product edit(int id, Product product) {
        Product existingProduct = productDAO.findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setCode(product.getCode());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setQuantity(product.getQuantity());
            return productDAO.save(existingProduct);
        }
        return null;
    }

    @Override
    public List<Product> search(String keyword) {
        List<Product> products;
        try {
            LocalDate registerDate = LocalDate.parse(keyword, DateTimeFormatter.ISO_DATE);
            products = productDAO.findProductByRegisterDate(registerDate);
        } catch (Exception e) {
            products = productDAO.search(keyword);
        }
        return products;
    }
}
