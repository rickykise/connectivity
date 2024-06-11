package ai.fassto.connectivity.domain.common.valueobject;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeInCharge {
    private String name;
    private String position;
    private String phoneNo;
    private String email;
}
