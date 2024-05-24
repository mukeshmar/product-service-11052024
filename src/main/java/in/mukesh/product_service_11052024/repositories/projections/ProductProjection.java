package in.mukesh.product_service_11052024.repositories.projections;


import in.mukesh.product_service_11052024.models.Category;
import java.util.Date;

public interface ProductProjection {
    Long getId();
    String getTitle();
    String getDescription();
    Double getPrice();
    String getImage();
    Category getCategory();
    Date getCreatedAt();
    Date getUpdatedAt();
    Boolean getIsDeleted();
}
