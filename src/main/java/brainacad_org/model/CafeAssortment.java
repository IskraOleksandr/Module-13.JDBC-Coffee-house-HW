package brainacad_org.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeAssortment {

    private Long id;
    private String title_eng;
    private String title_rus;
    private String assortment_type;
    private float price;
}
