package lobby.pandemica.db;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedCovidInformation
{
    private CovidInformation covidInformation;
    private int vaccinationCount;
}