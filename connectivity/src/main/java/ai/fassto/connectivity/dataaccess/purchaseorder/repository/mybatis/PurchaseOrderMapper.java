package ai.fassto.connectivity.dataaccess.purchaseorder.repository.mybatis;

import ai.fassto.connectivity.dataaccess.purchaseorder.dto.*;
import ai.fassto.connectivity.dataaccess.purchaseorder.entity.*;

import java.util.List;

import ai.fassto.connectivity.dataaccess.purchaseorder.dto.ClientConfirmYnBulkUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseOrderMapper {

    /************
     * 상태 변경
     *************/
    int updateWorkStatus(InOrdEntity inOrdEntity);


    /************
     * 센터 도착
     *************/
    int cenArvTimeUpdate(InOrdEntity inOrdEntity);


    /************
     * 입고 검수
     *************/
    int getInCheckSeq(String whCd, String inOrdSlipNo, String cstCd);



    /************
     * 입고 완료
     *************/

    int insertBulkInCheck(InCheckBulkInsertDTO inCheckBulkInsertDTO);
    int insertBulkInWhenSales(InBulkInsertDTO inBulkInsertDTO);
    int insertInWrkInfoWhenSales(InWrkInfoEntity inWrkInfoEntity);

    /************
     * 반품 완료
     *************/
    int insertBulkInWhenReturn(InBulkInsertDTO inBulkInsertDTO);
    int insertInWrkInfoWhenReturn(InWrkInfoEntity inWrkInfoEntity);


    /************
     * 조회
     *************/
    InOrdEntity findOneInOrd(InOrdDTO inOrdDTO);

    int insertBulkDamagedItemClientConfirm(ClientConfirmBulkInsertDTO clientConfirmBulkInsertDTO);

    int updateBulkClientConfirmYn(ClientConfirmYnBulkUpdateDTO clientConfirmYnBulkUpdateDTO);

    List<ClientConfirmEntity> findDamagedItemListBySlipNo(String slipNo);
}
