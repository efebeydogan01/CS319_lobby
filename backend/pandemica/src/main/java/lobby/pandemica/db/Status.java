package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class holding an enum type COVID risk status
 */
public class Status {

    public enum RISK {
        NEGATIVE,
        RISKY,
        POSITIVE
    }
    private RISK status;
}
