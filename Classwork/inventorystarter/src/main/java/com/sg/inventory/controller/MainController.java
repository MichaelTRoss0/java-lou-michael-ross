package com.sg.inventory.controller;

import com.sg.inventory.entities.Product;
import com.sg.inventory.entities.Store;
import com.sg.inventory.entities.Supplier;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kylerudy
 */
@Controller
public class MainController {
    
    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("stores", null);
        return "index";
    }
    
    @PostMapping("/addStore")
    public String addStore(Store store) {

        return "redirect:/";
    }
    
    @GetMapping("/viewInventory")
    public String viewInventory(Integer id, Model model) {
        
//        model.addAttribute("store", null);
//        model.addAttribute("products", null);
        
        return "inventory";
    }
    
    @PostMapping("/addProduct")
    public String addProduct(Product product, HttpServletRequest request) {
        int storeId = Integer.parseInt(request.getParameter("storeId"));

        return "redirect:/viewInventory?id=" + storeId;
    }
    
    @GetMapping("/deleteProduct")
    public String deleteProduct(Integer id, Integer storeId) {

        return "redirect:/viewInventory?id=" + storeId;
    }
    
    @GetMapping("/product")
    public String displayProduct(Integer id, Model model) {

        
//        model.addAttribute("product", null);
//        model.addAttribute("suppliers", null);
        
        return "product";
    }
    
    @PostMapping("/addSupplier")
    public String addSupplier(Supplier supplier, HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));

        
        return "redirect:/product?id=" + productId;
    }
    
    @PostMapping("/addExistingSupplier")
    public String addExistingSupplier(Integer productId, Integer supplierId) {

        
        return "redirect:/product?id=" + productId;
    }
    
    @GetMapping("/removeSupplier")
    public String removeSupplier(Integer productId, Integer supplierId) {

        
        return "redirect:/product?id=" + productId;
    }
    
    @GetMapping("/supplier")
    public String displaySupplier(Integer id, Model model) {
        
//        model.addAttribute("supplier", null);
        
        return "supplier";
    }
}
