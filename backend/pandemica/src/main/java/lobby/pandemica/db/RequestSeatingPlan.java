package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSeatingPlan {
    private String courseName;
    private Integer sectionNo;
}
