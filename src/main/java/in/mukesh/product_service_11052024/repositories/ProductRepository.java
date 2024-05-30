package in.mukesh.product_service_11052024.repositories;

import in.mukesh.product_service_11052024.models.Product;
import in.mukesh.product_service_11052024.repositories.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // JPA Queries Methods

    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Long productId);
    void delete(Product product);
    List<Product> findAllByCategory_Title(String title);

    // HQL Queries - Hibernate Query language

    @Query("select p from Product p where p.category.title = :categoryName")
    List<Product> findProductWithCategoryName(String categoryName);

    @Query("select p.title as title from Product p where p.category.title = :categoryName ")
    List<String> findProductTitleMethod(String categoryName);

    @Query("Select p.title as title, p.price as price from Product p where p.category.title = :categoryName")
    List<ProductProjection> findTitlePriceFromProductTable(String categoryName);


    // Native SQL Queries
    @Query(value = "select * from product p where p.id = :productId", nativeQuery = true)
    Product findAllByID(long productId);



}
