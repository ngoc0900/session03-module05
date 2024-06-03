package ra.jpa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ra.jpa.entity.Category;

import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductCreateRequest {
    private String name;
    private Double price;
    private Integer stock;
    private MultipartFile file;
    private String description;
    private Integer categoryId;
}
