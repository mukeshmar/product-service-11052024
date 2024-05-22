package in.mukesh.product_service_11052024.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String title;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
