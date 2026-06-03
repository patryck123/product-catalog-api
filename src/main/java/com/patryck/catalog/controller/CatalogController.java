package com.patryck.catalog.controller;
import com.patryck.catalog.entity.CatalogProduct;
import com.patryck.catalog.repository.CatalogProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
@RestController @RequestMapping("/api/catalog") @RequiredArgsConstructor
@Tag(name = "Catálogo de Produtos", description = "Catálogo completo de produtos com avaliações")
public class CatalogController {
    private final CatalogProductRepository repo;
    @PostMapping public ResponseEntity<CatalogProduct> create(@RequestBody CatalogProduct p) { return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(p)); }
    @GetMapping public ResponseEntity<List<CatalogProduct>> findAll() { return ResponseEntity.ok(repo.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<CatalogProduct> findById(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @GetMapping("/search") public ResponseEntity<List<CatalogProduct>> search(@RequestParam String q) { return ResponseEntity.ok(repo.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(q, q)); }
    @GetMapping("/category/{cat}") public ResponseEntity<List<CatalogProduct>> byCategory(@PathVariable String cat) { return ResponseEntity.ok(repo.findByCategory(cat)); }
    @GetMapping("/brand/{brand}") public ResponseEntity<List<CatalogProduct>> byBrand(@PathVariable String brand) { return ResponseEntity.ok(repo.findByBrand(brand)); }
    @GetMapping("/price") public ResponseEntity<List<CatalogProduct>> byPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) { return ResponseEntity.ok(repo.findByPriceRange(min, max)); }
    @GetMapping("/top-rated") public ResponseEntity<List<CatalogProduct>> topRated(@RequestParam(defaultValue = "4.0") Double min) { return ResponseEntity.ok(repo.findByAverageRatingGreaterThanEqual(min)); }
    @PatchMapping("/{id}/review") public ResponseEntity<CatalogProduct> addReview(@PathVariable Long id, @RequestParam Double rating) {
        return repo.findById(id).map(p -> {
            double newAvg = ((p.getAverageRating() * p.getReviewCount()) + rating) / (p.getReviewCount() + 1);
            p.setAverageRating(Math.round(newAvg * 10.0) / 10.0); p.setReviewCount(p.getReviewCount() + 1);
            return ResponseEntity.ok(repo.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }
}
