package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    public enum RISK {
        NEGATIVE,
        RISKY,
        POSITIVE
    }

    private RISK status;
}
