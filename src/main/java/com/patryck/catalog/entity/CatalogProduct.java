package com.patryck.catalog.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity @Table(name = "catalog_products") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CatalogProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String brand;
    @Column(nullable = false) private String category;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal price;
    @ElementCollection @CollectionTable(name = "product_specs") private List<String> specifications;
    @ElementCollection @CollectionTable(name = "product_images") private List<String> imageUrls;
    @Builder.Default private Double averageRating = 0.0;
    @Builder.Default private Integer reviewCount = 0;
    @Builder.Default private Boolean active = true;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
