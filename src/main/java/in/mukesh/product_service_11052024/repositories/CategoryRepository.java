package in.mukesh.product_service_11052024.repositories;

import in.mukesh.product_service_11052024.models.Category;
import in.mukesh.product_service_11052024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);
    Category findByTitle(String title);
    List<Category> findAllByTitleEndingWith(String ending);
}
