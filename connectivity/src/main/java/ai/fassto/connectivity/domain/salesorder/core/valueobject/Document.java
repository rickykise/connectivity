package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.DocumentSource;
import lombok.Builder;

@Builder
public record Document(
        DocumentSource source, // 출처
        String slipNo,  // DT01OO230215000001
        String fileType,  // FORM
        String originalFileName,  // shipment_ManiFest_document(16490978)_2023_02_14
        String fileName,  // 513976609392_FORM
        String filePath,  // /2023/02/14/
        String fileExtension,  // pdf
        String fileVolume  // 2000
) {
}
