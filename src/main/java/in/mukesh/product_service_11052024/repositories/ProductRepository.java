package in.mukesh.product_service_11052024.repositories;

import in.mukesh.product_service_11052024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Long productId);
    void delete(Product product);
}
