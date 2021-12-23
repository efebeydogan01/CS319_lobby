package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSeat {
    private String courseName;
    private Integer sectionNo;
    private Integer rowNo;
    private Integer columnNo;
}
