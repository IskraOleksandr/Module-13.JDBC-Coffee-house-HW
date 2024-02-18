package brainacad_org.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffWorkSchedule {

    private Long id;
    private long staff_id;
    private String staff_name;
    private String day_of_week;
    private Time start_time;
    private Time end_time;
}
