package in.mukesh.product_service_11052024.controllers;


import in.mukesh.product_service_11052024.dtos.ErrorDto;
import in.mukesh.product_service_11052024.dtos.ProductRequestDto;
import in.mukesh.product_service_11052024.dtos.ProductResponseDto;
import in.mukesh.product_service_11052024.exceptions.ProductNotFoundException;
import in.mukesh.product_service_11052024.models.Product;
import in.mukesh.product_service_11052024.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(@Qualifier("SelfProductService") ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseDto> getSingleProduct(@PathVariable("id") long productId) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(toProductResponseDto(product));
    }

//    @GetMapping({"/products", "/products/"})
//    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
//        List<Product> productsList = productService.getAllProducts();
//        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
//        for (Product product : productsList) {
//            productResponseDtoList.add(toProductResponseDto(product));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
//    }

    // Another way to get all projects with pagination, filtering, sorting
    // Query Parameter -> http://localhost:8080/products?pageNumber=0&pageSize=5&sortParam=title

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
            @PathParam("pageNumber") int pageNumber,
            @PathParam("pageSize") int pageSize,
            @PathParam("sortParam") String sortParam
    ) {
        Page<Product> page = productService.getAllProducts(pageNumber, pageSize, sortParam);
        List<Product> productListCurrentPage = page.getContent();
        List<ProductResponseDto> productResponseList = new ArrayList<>();
        for (Product product : productListCurrentPage){
            productResponseList.add(toProductResponseDto(product));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productResponseList);
    }

    @PostMapping({"/products", "/products/"})
    public ResponseEntity<ProductResponseDto> addNewProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.addNewProduct(
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );
        return ResponseEntity.status(HttpStatus.OK).body(toProductResponseDto(product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") long productId) throws ProductNotFoundException {
        Product product = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(toProductResponseDto(product));
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") long productId, @RequestBody ProductRequestDto productRequestDto) throws ProductNotFoundException {
        Product product = productService.updateProduct(
                productId,
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );

        return ResponseEntity.status(HttpStatus.OK).body(toProductResponseDto(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> replaceProduct(@PathVariable("id") long productId, @RequestBody ProductRequestDto productRequestDto) throws ProductNotFoundException {
        Product product = productService.replaceProduct(
                productId,
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );
        return ResponseEntity.status(HttpStatus.OK).body(toProductResponseDto(product));
    }

    private ProductResponseDto toProductResponseDto(Product product) {
        String categoryTitle = product.getCategory().getTitle();
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

}
