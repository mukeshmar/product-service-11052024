package in.mukesh.product_service_11052024.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String title;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Product> products;
}
