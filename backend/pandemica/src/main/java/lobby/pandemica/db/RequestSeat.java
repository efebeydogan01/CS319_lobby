package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class of requestSeat, involving necessary information to define a seat
 */
public class RequestSeat {
    private String courseName;
    private int sectionNo;
    private int rowNo;
    private int columnNo;
}
