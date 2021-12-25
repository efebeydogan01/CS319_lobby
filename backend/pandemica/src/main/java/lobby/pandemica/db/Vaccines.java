package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccines
{
    public enum TYPE {
        BIONTECH,
        SINOVAC,
        TURKOVAC
    }
    public TYPE type;
}
