package enset.dimba.tp_3_spring_mvc.controllers;

import enset.dimba.tp_3_spring_mvc.entities.Product;
import enset.dimba.tp_3_spring_mvc.repo.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping({"/", "/products"})
    public String listProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            Model model) {

        Page<Product> productPage = productRepository.findByNameContainsIgnoreCase(keyword.trim(),
                PageRequest.of(page, size));

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pages", new int[productPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "products/list";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id,
                                @RequestParam(defaultValue = "") String keyword,
                                @RequestParam(defaultValue = "0") int page) {
        productRepository.deleteById(id);
        String encodedKeyword = UriUtils.encode(keyword, StandardCharsets.UTF_8);
        return "redirect:/products?page=" + page + "&keyword=" + encodedKeyword;
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable : " + id));
        model.addAttribute("product", product);
        return "products/form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@Validated @ModelAttribute Product product,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "products/form";
        }
        productRepository.save(product);
        return "redirect:/products";
    }
}