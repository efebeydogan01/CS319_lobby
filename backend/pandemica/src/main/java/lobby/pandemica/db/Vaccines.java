package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class for vaccines enum type, where its either BIONTECH, SINOVAC or TURKOVAC
 */
public class Vaccines
{
    public enum TYPE {
        BIONTECH,
        SINOVAC,
        TURKOVAC
    }
    public TYPE type;
}
