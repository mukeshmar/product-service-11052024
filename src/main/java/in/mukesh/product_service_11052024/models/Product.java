package in.mukesh.product_service_11052024.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private Category category;
}
