package ai.fassto.connectivity.domain.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class StreetAddress {
    public String address1;
    public String address2;
    public String address3;
    public String city;
    public String province;
    public String zipCode;
    public String country;


}
