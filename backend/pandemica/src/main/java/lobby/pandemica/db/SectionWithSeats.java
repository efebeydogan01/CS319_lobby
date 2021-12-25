package lobby.pandemica.db;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionWithSeats {
    private Section section;
    private List<Seat> seats;
}