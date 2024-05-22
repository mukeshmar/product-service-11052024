package in.mukesh.product_service_11052024.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
