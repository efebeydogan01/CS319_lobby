package lobby.pandemica.db;

import lombok.*;

import javax.persistence.Entity;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempDetails {
    private Integer bilkentId;
    private String password;
}
