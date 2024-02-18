package brainacad_org.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    private Long id;
    private long client_id;
    private long assortment_id;
    private Date order_date;
    private Time order_time;
}
