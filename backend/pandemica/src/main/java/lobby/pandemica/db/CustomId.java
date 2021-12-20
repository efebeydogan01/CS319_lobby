package lobby.pandemica.db;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomId {
    private UUID id;
}
