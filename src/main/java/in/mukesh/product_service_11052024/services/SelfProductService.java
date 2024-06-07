package in.mukesh.product_service_11052024.services;

import in.mukesh.product_service_11052024.exceptions.ProductNotFoundException;
import in.mukesh.product_service_11052024.models.Category;
import in.mukesh.product_service_11052024.models.Product;
import in.mukesh.product_service_11052024.repositories.CategoryRepository;
import in.mukesh.product_service_11052024.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("SelfProductService")
public class SelfProductService implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException {
        Product responseFromDB = productRepository.findByIdIs(productId);
        if(responseFromDB == null){
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }
        return responseFromDB;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> responseFromDB = productRepository.findAll();
        return responseFromDB;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortParam) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortParam).descending());
        return productRepository.findAll(pageable);
    }

    @Override
    public Product addNewProduct(String title, double price, String description, String image, String category) {
        Product newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setDescription(description);
        newProduct.setImage(image);

        Category categoryFromDb = categoryRepository.findByTitle(category);

        if (categoryFromDb == null) {
            categoryFromDb = new Category();
            categoryFromDb.setTitle(category);
//            categoryRepository.save(categoryFromDb); --> Don't need to call this DB call, below ProductRepository will take care for that
        }
        newProduct.setCategory(categoryFromDb);
        Product savedProduct = productRepository.save(newProduct);
        return savedProduct;
    }

    @Override
    public Product deleteProduct(long productId) throws ProductNotFoundException {
        Product responseFromDb = productRepository.findByIdIs(productId);

        if(responseFromDb == null){
            long noOfItemsInDB = productRepository.count();
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }

        productRepository.delete(responseFromDb);
        return responseFromDb;
    }

    @Override
    public Product updateProduct(long productId, String title, double price, String description, String image, String category) throws ProductNotFoundException {

        Product responseFromDb = productRepository.findByIdIs(productId);

        if(responseFromDb == null){
            long noOfItemsInDB = productRepository.count();
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }

        if(title != null){
            responseFromDb.setTitle(title);
        }
        if(price != -1){
            responseFromDb.setPrice(price);
        }
        if(description != null){
            responseFromDb.setDescription(description);
        }
        if(image != null){
            responseFromDb.setImage(image);
        }
        if(category != null){
            Category categoryFromDb = categoryRepository.findByTitle(category);
            if(categoryFromDb == null){
                categoryFromDb = new Category();
                categoryFromDb.setTitle(category);
            }
            responseFromDb.setCategory(categoryFromDb);
        }

        return productRepository.save(responseFromDb);
    }

    @Override
    public Product replaceProduct(long productId, String title, double price, String description, String image, String category) throws ProductNotFoundException {

        Product responseFromDb = productRepository.findByIdIs(productId);

        if(responseFromDb == null){
            long noOfItemsInDB = productRepository.count();
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }

        responseFromDb.setTitle(title);
        responseFromDb.setPrice(price);
        responseFromDb.setDescription(description);
        responseFromDb.setImage(image);

        Category categoryFromDb = categoryRepository.findByTitle(category);

        if(categoryFromDb == null){
            categoryFromDb = new Category();
            categoryFromDb.setTitle(category);
            categoryRepository.save(categoryFromDb);
        }

        return productRepository.save(responseFromDb);
    }
}
