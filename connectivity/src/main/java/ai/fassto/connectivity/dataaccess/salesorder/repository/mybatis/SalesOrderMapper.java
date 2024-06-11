package ai.fassto.connectivity.dataaccess.salesorder.repository.mybatis;

import ai.fassto.connectivity.dataaccess.salesorder.dto.*;
import ai.fassto.connectivity.dataaccess.salesorder.entity.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesOrderMapper {

    /************
     * 작업 상태 변경
     *************/
    int updateOutOrdStatus(OutOrdEntity outOrdEntity);

    /************
     * 출고 지시 확정
     *************/

    int updateBulkOutInventoryAssignLotNo(OutInventoryAssignLotNoBulkUpdateDTO outInventoryAssignLotNoBulkUpdateDTO);
    int deleteOutInventoryAssign(OutInventoryAssignDeleteDTO outInventoryAssignDeleteDTO);
    int insertBulkOutInventoryAssign(OutInventoryAssignBulkInsertDTO outInventoryAssignBulkInsertDTO);
    boolean isExistOutPickMap(OutPickMapEntity outPickMapEntity);
    int insertOutPickMap(OutPickMapEntity outPickMapEntity);
    int updateOutPickMap(OutPickMapEntity outPickMapEntity);
    int insertBulkOutOrdSet(OutOrdSetBulkInsertDTO outOrdSetBulkInsertDTO);
    int getPickSeq(OutPickOrdPickSeqDTO outPickOrdPickSeqDTO);
    int insertBulkOutPickOrd(OutPickOrdBulkInsertDTO outPickOrdBulkInsertDTO);

    /************
     * 피킹
     *************/
    int updateBulkOutPickOrdPickQty(OutPickOrdPickQtyBulkUpdateDTO pickOrdPickQtyBulkUpdateDTO);

    /************
     * 패킹
     *************/

    int updateOutPickOrdWorkStatus(OutPickOrdEntity outPickOrdEntity);

    int insertBulkOutPack(OutPackBulkInsertDTO packBulkInsertDTO);

    int insertBulkOut(OutBulkInsertDTO outBulkInsertDTO);
    int insertBulkOutInvoice(OutInvoiceBulkInsertDTO outInvoiceInsertDTO);

    int updateOutOrdParcelCdInvoiceNo(OutOrdParcelCdInvoiceNoDTO outOrdParcelCdInvoiceNoDTO);

    int updateBulkOutPickOrdPackQty(OutPickOrdPackQtyBulkUpdateDTO outPickOrdPackQtyBulkUpdateDTO);

    int updateOutOrdOrdDiv(OutOrdOrdDivDTO outOrdOrdDivDTO);

    /************
     * 출고
     *************/

    int updateBulkOutPackParcelCdInvoiceNo(OutPackParcelCdInvoiceNoDTO outPackParcelCdInvoiceNoDTO);

    /************
     * 출고 지시 확정 취소
     *************/

    boolean isExistOutOrdSet(OutOrdSetExistDTO outOrdSetExistDTO);

    boolean isExistOutPickOrd(OutPickOrdExistDTO outPickOrdExistDTO);

    boolean isExistOutPack(OutPackExistDTO outPackExistDTO);

    boolean isExistOutInvoice(OutInvoiceExistDTO outPackInvoiceDTO);

    boolean isExistOut(OutExistDTO outExistDTO);

    boolean isExistOutPackWhSub(OutPackWhSubExistDTO outPackWhSubExistDTO);

    int updateOutPickMapAllocateYn(OutPickMapStatusDTO outPickMapStatusDTO);

    int deleteOutOrdSet(OutOrdSetDeleteDTO outOrdSetDeleteDTO);

    int deleteOutPickOrd(OutPickOrdDeleteDTO outPickOrdDeleteDTO);

    int deleteOutPack(OutPackDeleteDTO outPackEntity);

    int deleteOutInvoice(OutInvoiceExistDTO outInvoiceEntity);

    int deleteOut(OutDeleteDTO outEntity);

    int deleteOutPackWhSub(OutPackWhSubDeleteDTO outPackWhSubDeleteDTO);

    int updateOutOrdParcelCdInvoiceNoToNull(OutOrdParcelCdInvoiceNoToNullDTO outOrdParcelCdInvoiceNoToNullDTO);

    int insertBulkOutPackWhSub(OutPackWhSubBulkInsertDTO outPackWhSubBulkInsertDTO);
    /************
     * 조회
     *************/

    OutOrdEntity findOneOutOrd(OutOrdDTO outOrdDTO);
    OutPickOrdEntity findOneOutPickOrd(OutPickOrdDTO outPickOrdDTO);
    List<OutOrdSeperatedGodQtyEntity> findSeperatedGodQtyList(OutOrdSeperatedGodQtyDTO outOrdSeperatedGodQtyDTO);
    List<OutInventoryAssignEntity> findOutInventoryAssignList(String outOrdSlipNo);
    List<WhSubsidiaryEntity> findWhSubsidiaryList(WhSubsidiaryDTO whSubsidiaryDTO);

}
