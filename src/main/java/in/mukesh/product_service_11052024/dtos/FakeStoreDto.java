package in.mukesh.product_service_11052024.dtos;

import in.mukesh.product_service_11052024.models.Category;
import in.mukesh.product_service_11052024.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);
        Category cateObj = new Category();
        cateObj.setTitle(category);
        product.setCategory(cateObj);
        return product;
    }
}
