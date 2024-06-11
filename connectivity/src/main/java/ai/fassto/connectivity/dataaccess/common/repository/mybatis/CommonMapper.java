package ai.fassto.connectivity.dataaccess.common.repository.mybatis;

import ai.fassto.connectivity.dataaccess.common.dto.*;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    //전표번호 가져오기
    String getSlipNo(SlipNoDTO slipNoDTO);

    //로케이션 번호 가져오기
    String getLocNo(LocNoDTO locNoDTO);

    // 현재 일자, 시간 조회
    DateTimeDTO getDbDateAndTime();

    //특정일 기준 +-n일의 영업일 조회
    String getWorkdayCalc(WorkDayCalcDTO workDayCalcDTO);

    //센터명 조회
    String getWhName(String whCd);

    //상품종류수(SKU), 입고완료수량(EA) 조회
    InQtyInfoDTO getInQtyInfo(String inOrdSlipNo);

    //상품 목록 조회
    List<GodEntity> findItems(List<String> godCdList);
}
