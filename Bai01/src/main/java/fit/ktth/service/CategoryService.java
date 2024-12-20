/*
 * @ (#) CategoryService.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.service;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import fit.ktth.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();

    public Category find(int id);

    public Category saveCategory(Category category);
}
