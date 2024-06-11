package ai.fassto.connectivity.dataaccess.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConnectivityPropertyDTO {
    private String mainType;
    private String subType;
    private String value;
}
