package in.mukesh.product_service_11052024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
//    private int id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

}
