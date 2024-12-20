/*
 * @ (#) CategoryImpl.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import fit.ktth.dao.CategoryDAO;
import fit.ktth.entity.Category;
import fit.ktth.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category find(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryDAO.save(category);
    }
}
