package in.mukesh.product_service_11052024.services;

import in.mukesh.product_service_11052024.dtos.FakeStoreDto;
import in.mukesh.product_service_11052024.exceptions.ProductNotFoundException;
import in.mukesh.product_service_11052024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException {

        // Check in Cache
        Product productInCache = (Product) redisTemplate.opsForHash()
                .get("PRODUCTS", "PRODUCT_" + productId);

        if (productInCache != null){
            System.out.println("Cache hit for product " + productId);
            return productInCache;
        }

        String url = "https://fakestoreapi.com/products/" + productId;
        FakeStoreDto fakeStoreDto = restTemplate.getForObject(url, FakeStoreDto.class);

        if (fakeStoreDto == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found try a product with id less than 21"
            );
        }

        Product response = fakeStoreDto.toProduct();
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + productId, response);
        return response;
    }

    @Override
    public List<Product> getAllProducts() {

        String url = "https://fakestoreapi.com/products";

        FakeStoreDto[] fakeStoreDtosList = restTemplate.getForObject(url, FakeStoreDto[].class);

        List<Product> productsList = new ArrayList<>();
        for (FakeStoreDto fakeStoreDto : fakeStoreDtosList) {
            productsList.add(fakeStoreDto.toProduct());
        }
        return productsList;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortParam) {
        return null;
    }

    @Override
    public Product addNewProduct(String title, double price, String description, String image, String category) {
        FakeStoreDto fakeStoreDTO = new FakeStoreDto();
        fakeStoreDTO.setTitle(title);
        fakeStoreDTO.setPrice(price);
        fakeStoreDTO.setDescription(description);
        fakeStoreDTO.setImage(image);
        fakeStoreDTO.setCategory(category);

        String url = "https://fakestoreapi.com/products";
        FakeStoreDto response = restTemplate.postForObject(url, fakeStoreDTO, FakeStoreDto.class);

        return response.toProduct();
    }

    @Override
    public Product deleteProduct(long productId) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + productId;
        FakeStoreDto response = restTemplate.exchange(url, HttpMethod.DELETE, null, FakeStoreDto.class).getBody();

        if (response == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found try to delete a product with id less than 21"
            );
        }

        return response.toProduct();
    }

    @Override
    public Product updateProduct(long productId, String title, double price, String description, String image, String category) throws ProductNotFoundException {
        FakeStoreDto requestDto = new FakeStoreDto();
        requestDto.setTitle(title);
        requestDto.setPrice(price);
        requestDto.setDescription(description);
        requestDto.setImage(image);
        requestDto.setCategory(category);

//         Known Issue: HTTP PATCH is not supported by RestTemplate
//         So below code will NOT work
//         Will throw an exception:
//         Invalid HTTP method: PATCH\n\tat org.springframework.web.client.
//         create request entity to send in patch request body to fakestore

//         String url = "https://fakestoreapi.com/products/" + productId;
//         HttpEntity<FakeStoreDto> request = new HttpEntity<>(requestDto);
//         ResponseEntity<FakeStoreDto> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, request, FakeStoreDto.class);
//         FakeStoreDto response = responseEntity.getBody();
//        if (response == null) {
//            throw new ProductNotFoundException(
//                    "Product with id " + productId + " not found try to delete a product with id less than 21"
//                    );
//        }

        FakeStoreDto response = requestDto;
        response.setId(productId);
        return response.toProduct();

    }

    public Product replaceProduct(long productId, String title, double price, String description, String image, String category) throws ProductNotFoundException {
        FakeStoreDto requestDto = new FakeStoreDto();
        requestDto.setTitle(title);
        requestDto.setPrice(price);
        requestDto.setDescription(description);
        requestDto.setImage(image);
        requestDto.setCategory(category);

        String url = "https://fakestoreapi.com/products/" + productId;
        HttpEntity<FakeStoreDto> request = new HttpEntity<>(requestDto);
        FakeStoreDto response = restTemplate.exchange(url, HttpMethod.PUT, request, FakeStoreDto.class).getBody();

        if(response == null){
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found try to delete a product with id less than 21"
            );
        }
        return response.toProduct();
    }

}
