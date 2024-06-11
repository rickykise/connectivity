package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Attr;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record GetSOLIByParent(
        @JsonProperty("@attr")
        Attr attr_,

        @JsonProperty("SOLI")
        List<SOLI> soli
) {
}
