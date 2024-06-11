package ai.fassto.connectivity.domain.workorder.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkType {

    KITTING("키팅"),
    UNKITTING("언키팅"),
    STICKER("스티커"),
    TAG("태그"),
    IRONING("다림질");

    private final String description;

    public static WorkType findBy(String name) {
        try {
            return WorkType.valueOf(name);
        } catch (Exception e) {
            throw new NoSuchEnumElementException(WorkType.class.getSimpleName(), name);
        }
    }

}
