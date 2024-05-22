package in.mukesh.product_service_11052024.services;

import in.mukesh.product_service_11052024.dtos.FakeStoreDto;
import in.mukesh.product_service_11052024.dtos.ProductResponseDto;
import in.mukesh.product_service_11052024.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate = new RestTemplate();

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) {
        String url = "http://fakestoreapi.com/products/" + productId;
        FakeStoreDto fakeStoreDto = restTemplate.getForObject(url, FakeStoreDto.class);
        return fakeStoreDto.toProduct();
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
    public Product deleteProduct(long productId) {
        String url = "https://fakestoreapi.com/products/" + productId;
        FakeStoreDto response = restTemplate.exchange(url, HttpMethod.DELETE, null, FakeStoreDto.class).getBody();
        return response.toProduct();
    }

    @Override
    public Product updateProduct(long productId, String title, double price, String description, String image, String category) {
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

        FakeStoreDto response = requestDto;
        response.setId(productId);
        return response.toProduct();

    }

    public Product replaceProduct(long productId, String title, double price, String description, String image, String category) {
        FakeStoreDto requestDto = new FakeStoreDto();
        requestDto.setTitle(title);
        requestDto.setPrice(price);
        requestDto.setDescription(description);
        requestDto.setImage(image);
        requestDto.setCategory(category);

        String url = "https://fakestoreapi.com/products/" + productId;
        HttpEntity<FakeStoreDto> request = new HttpEntity<>(requestDto);
        FakeStoreDto response = restTemplate.exchange(url, HttpMethod.PUT, request, FakeStoreDto.class).getBody();
        return response.toProduct();
    }

}
