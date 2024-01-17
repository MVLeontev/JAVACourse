package ru.vtb.course.lesson5.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.services.AccountService;
import ru.vtb.course.lesson5.services.ProductService;

@RestController
@RequestMapping("corporate-settlement-instance")
public class CorporateSettlementInstanceController {
    private ProductService productService;
    private AccountService accountService;

    @Autowired
    public CorporateSettlementInstanceController(ProductService productService, AccountService accountService) {
        this.productService = productService;
        this.accountService = accountService;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.makeProduct(productRequest));
    }
}
