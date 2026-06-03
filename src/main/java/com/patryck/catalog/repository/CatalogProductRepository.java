package com.patryck.catalog.repository;
import com.patryck.catalog.entity.CatalogProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;
public interface CatalogProductRepository extends JpaRepository<CatalogProduct, Long> {
    List<CatalogProduct> findByCategory(String category);
    List<CatalogProduct> findByBrand(String brand);
    List<CatalogProduct> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(String name, String brand);
    @Query("SELECT p FROM CatalogProduct p WHERE p.price BETWEEN :min AND :max AND p.active=true ORDER BY p.averageRating DESC")
    List<CatalogProduct> findByPriceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
    List<CatalogProduct> findByAverageRatingGreaterThanEqual(Double rating);
}
