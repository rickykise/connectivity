package ai.fassto.connectivity.domain.status.application.dto;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RefreshConnectivityPropertyCacheRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    private String key;
}
