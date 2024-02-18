package brainacad_org.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {

    private Long id;
    private String full_name;
    private Date date_of_birth;
    private String contact_phone;
    private String contact_address;
    private String position;
}
