package in.mukesh.product_service_11052024.controllers;


import in.mukesh.product_service_11052024.dtos.FakeStoreDto;
import in.mukesh.product_service_11052024.dtos.ProductRequestDto;
import in.mukesh.product_service_11052024.dtos.ProductResponseDto;
import in.mukesh.product_service_11052024.models.Product;
import in.mukesh.product_service_11052024.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("products/{id}")
    public ProductResponseDto getSingleProduct(@PathVariable("id") long productId) {
        Product product = productService.getSingleProduct(productId);
        return toProductResponseDto(product);
    }

    @GetMapping({"/products", "/products/"})
    public List<ProductResponseDto> getAllProducts() {
        List<Product> productsList = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : productsList) {
            productResponseDtoList.add(toProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    @PostMapping({"/products", "/products/"})
    public ProductResponseDto addNewProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.addNewProduct(
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );
        return toProductResponseDto(product);
    }

    @DeleteMapping("/products/{id}")
    public ProductResponseDto deleteProduct(@PathVariable("id") long productId) {
        Product product = productService.deleteProduct(productId);
        return toProductResponseDto(product);
    }

    private ProductResponseDto toProductResponseDto(Product product) {
        String categoryTitle = product.getCategory().getTitle();
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

    @PatchMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") long productId, @RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.updateProduct(
                productId,
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );

        return toProductResponseDto(product);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto replaceProduct(@PathVariable("id") long productId, @RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.replaceProduct(
                productId,
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory()
        );
        return toProductResponseDto(product);
    }
}
