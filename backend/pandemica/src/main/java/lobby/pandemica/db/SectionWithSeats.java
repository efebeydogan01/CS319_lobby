package lobby.pandemica.db;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class for encapsulating a section with its seats
 */
public class SectionWithSeats {
    private Section section;
    private List<Seat> seats;
}