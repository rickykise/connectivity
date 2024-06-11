package ai.fassto.connectivity.domain.workorder.application.dto.erp.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class Component {
    private String sequence;
    private String code;
    private Integer orderQty;
    private Integer consumedQty;
    private Integer remainingQty;
    private Integer cancelledQty;

}
