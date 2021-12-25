package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSeat {
    private String courseName;
    private int sectionNo;
    private int rowNo;
    private int columnNo;
}
