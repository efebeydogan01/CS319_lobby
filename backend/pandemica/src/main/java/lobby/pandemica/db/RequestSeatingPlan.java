package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Entity class of requestForm sent by users, includes the sender and text
 */
public class RequestSeatingPlan {
    private String courseName;
    private Integer sectionNo;
}
