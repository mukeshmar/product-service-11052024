package in.mukesh.product_service_11052024;

import in.mukesh.product_service_11052024.models.Category;
import in.mukesh.product_service_11052024.models.Product;
import in.mukesh.product_service_11052024.repositories.CategoryRepository;
import in.mukesh.product_service_11052024.repositories.ProductRepository;
import in.mukesh.product_service_11052024.repositories.projections.ProductProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductService11052024ApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    // JPA Query Methods Test

    @Test
    void testJpaDeclaredJoinQuery(){
        List<Product> productFromDb = productRepository.findAllByCategory_Title("Phone");
        for(Product product : productFromDb){
            System.out.println(product.getTitle());
        }
    }

    // HQL Query Test

    @Test
    void testHQL(){
        List<Product> productFromDb = productRepository.findProductWithCategoryName("Phone");
        for(Product product : productFromDb){
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testFindProductTitleByCategory(){
        List<String> productTitleFromDB = productRepository.findProductTitleMethod("Phone");

        for(String productTitle: productTitleFromDB){
            System.out.println(productTitle);
        }
    }

    @Test
    void testFindTitlePriceFromProductTable(){
        List<ProductProjection> responseFromDB = productRepository.findTitlePriceFromProductTable("Phone");

        for (ProductProjection productProjection : responseFromDB){
            System.out.println(productProjection.getTitle());
            System.out.println(productProjection.getPrice());
        }
    }


    // Native SQL Queries Test

    @Test
    void testFindAllByID(){
        Product responseFromDB = productRepository.findAllByID(2L);
        if(responseFromDB != null){
            System.out.println(responseFromDB.getTitle());
            System.out.println(responseFromDB.getPrice());
        }
    }

    @Test
    @Transactional
    void testFetchType(){
        Optional<Category> category = categoryRepository.findById(1L);
        if(category.isPresent()){
            System.out.println(category.get().getTitle());
            List<Product> productList = category.get().getProducts();
            for(Product product : productList){
                System.out.println(product.getTitle());
            }
        }
    }

    @Test
    @Transactional
    void testFetchMode(){
        List<Category> categories = categoryRepository.findAllByTitleEndingWith("e");
        for(Category category : categories){
            System.out.println(category.getTitle());
            List<Product> productList = category.getProducts();
            for (Product product : productList){
                System.out.println(product.getTitle());
            }
        }
    }
    
}
