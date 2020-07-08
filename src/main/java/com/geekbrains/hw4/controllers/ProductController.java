package com.geekbrains.hw4.controllers;

import com.geekbrains.hw4.model.Product;
import com.geekbrains.hw4.model.Search;
import com.geekbrains.hw4.repositories.ProductSpecifications;
import com.geekbrains.hw4.services.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    ProductServer productServer;
    private Integer page;

    @Autowired
    public void setProductServer(ProductServer productServer) {
        this.productServer = productServer;
        page = 1;
    }


    @GetMapping
    public String showAll(Model model,
                          @RequestParam(name = "p", defaultValue = "1") Integer page,
                          @RequestParam(name = "max_price", required = false) Double maxPrice,
                          @RequestParam(name = "min_price", required = false) Double minPrice){
        Specification<Product> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ProductSpecifications.scoreGEThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecifications.scoreLEThan(maxPrice));
        }

        List<Product> products = productServer.getAll(specification,page).getContent();
        model.addAttribute("products",products);
        return "all_products";
    }

    @GetMapping("/next_page")
    public String nextPage(){
        page++;
        return "redirect:/products?p="+page;
    }

    @GetMapping("/previous_page")
    public String previousPage(){
        page--;
        if(page<1){
            page=1;
        }
        return "redirect:/products?p="+page;
    }

    @PostMapping
    public String maxMin(@ModelAttribute Search search){
        page = 1;
        if(search.getMaxPrice() != null && search.getMinPrice() != null){
            return "redirect:/products?p="+page+"&max_price="+search.getMaxPrice()+"&min_price="+search.getMinPrice();
        } else if(search.getMaxPrice() != null){
            return "redirect:/products?p="+page+"&max_price="+search.getMaxPrice();
        } else if(search.getMinPrice() != null){
            return "redirect:/products?p="+page+"&min_price="+search.getMinPrice();
        }
        return "redirect:/products?p="+page;
    }

    @GetMapping("/add")
    public String showAddForm(){
        return "add_product_form";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product newProduct){
        productServer.saveOrUpdate(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduct(@PathVariable Long id, Model model){
        model.addAttribute("product",productServer.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product editProduct){
        productServer.saveOrUpdate(editProduct);
        return "redirect:/products";
    }
}
