package ai.fassto.connectivity.domain.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Size {
    private String width; //          '너비',
    private String length; //         '길이',
    private String height; //         '높이',
    private String bulk; //           '상품체적',
    private String weight; //         '상품무게',
    private String sideSum; //       '상품규격 (3면의합)',
    private String volume; //         '상품용량'
}
