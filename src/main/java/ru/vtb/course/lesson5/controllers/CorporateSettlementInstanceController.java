package ru.vtb.course.lesson5.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.course.lesson5.dto.ProductRequest;
import ru.vtb.course.lesson5.services.ProductServiceable;

@RestController
@RequestMapping("corporate-settlement-instance")
public class CorporateSettlementInstanceController {
    private final ProductServiceable productService;

    public CorporateSettlementInstanceController(ProductServiceable productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        return ResponseEntity.ok(productService.makeProduct(productRequest));
    }
}
