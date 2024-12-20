/*
 * @ (#) ProductController.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.controller;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import fit.ktth.entity.Category;
import fit.ktth.entity.Product;
import fit.ktth.service.CategoryService;
import fit.ktth.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listProduct(Model model, @RequestParam(required = false) Integer categoryId) {
        List<Category> categories = categoryService.findAll();
        List<Product> products;
        if (categoryId != null) {
            products = productService.findProductByCategory(categoryId);
        } else
            products = productService.findAllProduct();

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "list_product";
    }

    @GetMapping("/addProduct")
    public String showAddProduct(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model, @RequestParam("categoryId") int categoryId) {
        List<Category> categories = categoryService.findAll();
        Category category = categoryService.find(categoryId);
        product.setCategory(category);
        if (result.hasErrors()) {
            model.addAttribute("categories", categories);
            return "addProduct";
        }
        productService.save(product);
        return "redirect:/list";
    }

    @GetMapping("/view/{id}")
    public String showView(Model model, @PathVariable("id") int id) {
        Product product = productService.findProduct(id);
        model.addAttribute("product", product);
        return "view";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(Model model, @PathVariable("id") int id) {
        Product product = productService.findProduct(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute Product product, BindingResult result, @PathVariable("id") int id, Model model) {
        List<Category> categories = categoryService.findAll();
        if (result.hasErrors()) {
            model.addAttribute("categories", categories);
            return "edit";
        }
        productService.edit(id, product);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Product> products = productService.search(query);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "list_product";
    }
}
