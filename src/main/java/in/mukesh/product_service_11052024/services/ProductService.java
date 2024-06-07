package in.mukesh.product_service_11052024.services;

import in.mukesh.product_service_11052024.dtos.FakeStoreDto;
import in.mukesh.product_service_11052024.dtos.ProductRequestDto;
import in.mukesh.product_service_11052024.dtos.ProductResponseDto;
import in.mukesh.product_service_11052024.exceptions.ProductNotFoundException;
import in.mukesh.product_service_11052024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(long productId) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortParam);

    public Product addNewProduct(
            String title,
            double price,
            String description,
            String image,
            String category
    );

    public Product deleteProduct(long productId) throws ProductNotFoundException;

    public Product updateProduct(long productId,
                                 String title,
                                 double price,
                                 String description,
                                 String image,
                                 String category
    ) throws ProductNotFoundException;

    public Product replaceProduct(long productId,
                                  String title,
                                  double price,
                                  String description,
                                  String image,
                                  String category
    ) throws ProductNotFoundException;
}
