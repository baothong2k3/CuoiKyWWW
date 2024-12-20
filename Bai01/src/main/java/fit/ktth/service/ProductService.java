/*
 * @ (#) ProductService.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.service;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import fit.ktth.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProduct();

    public List<Product> findProductByCategory(int category_id);

    public Product save(Product product);

    public void delete(int id);

    public Product findProduct(int id);

    public Product edit(int id, Product product);

    public List<Product> search(String keyword);
}
