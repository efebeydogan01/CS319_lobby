package lobby.pandemica.db;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data class for credentials in logging in
 */
public class TempDetails {
    private Integer bilkentId;
    private String password;
}
