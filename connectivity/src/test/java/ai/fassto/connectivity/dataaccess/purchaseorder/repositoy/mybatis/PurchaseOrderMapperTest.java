package ai.fassto.connectivity.dataaccess.purchaseorder.repositoy.mybatis;

import ai.fassto.connectivity.dataaccess.common.configuration.PrimaryDbConfig;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ImportAutoConfiguration(PrimaryDbConfig.class)
class PurchaseOrderMapperTest {
/*

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    private static final String WH_CD = "DT01";
    private static final String CST_CD = "01270";

    private static final String SUP_CD = "99999";

    private static final String TODAY = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    private static final String SLIP_NO = WH_CD + CST_CD + TODAY;

    @Test
    @DisplayName("itemList size 만큼 입고요청정보 2건 등록")
    public void insertInOrdTestData() {
        InOrdBulkInsertDTO dto = InOrdBulkInsertDTO.builder()
            .ordDt(TODAY)
            .whCd(WH_CD)
            .slipNo(SLIP_NO)
            .cstCd(CST_CD)
            .supCd(SUP_CD)
            .releaseDt(TODAY)
            .releaseGbn(ReleaseType.PLUS_DAY_0.getCode())
            .preArv("N")
            .itemList(this.makeItemEntityList())
            .build();

        this.purchaseOrderMapper.insertInOrd(dto);
    }

    @Test
    @DisplayName("[입고요청조회] itemList size 만큼 존재하면 성공")
    public void getOutOrd_test() {
        // given
        this.insertInOrdTestData();

        // when
        List<InOrdEntity> inOrdEntityListFromDB = this.purchaseOrderMapper.getInOrdList(WH_CD, SLIP_NO);

        // then
        assertEquals(2, inOrdEntityListFromDB.size());
    }

    @Test
    @DisplayName("[입고작업상태변경] workStatus 로 입고주문상태 업데이트 되면 성공")
    public void updateWorkStatus_test() {
        // given
        this.insertInOrdTestData();

        PurchaseOrderWorkStatus purchaseOrderWorkStatus = PurchaseOrderWorkStatus.RECEIVED;
        InOrdEntity inOrdEntity = InOrdEntity.builder()
            .whCd(WH_CD)
            .slipNo(SLIP_NO)
            .wrkStat(purchaseOrderWorkStatus.getCode())
            .build();
        this.purchaseOrderMapper.updateWorkStatus(inOrdEntity);

        // when
        List<InOrdEntity> inOrdEntityListFromDB = this.purchaseOrderMapper.getInOrdList(WH_CD, SLIP_NO);

        // then
        for (InOrdEntity inOrdEntityFromDB : inOrdEntityListFromDB) {
            assertEquals(inOrdEntityFromDB.getWrkStat(), purchaseOrderWorkStatus.getCode());
            assertEquals("SOLO", inOrdEntityFromDB.getUpdId());
        }
    }

    @Test
    @DisplayName("[센터도착] 센터도착정보, 출고구분상태값 업데이트 되면 성공")
    public void cenArvTimeSave_test() {
        // given
        this.insertInOrdTestData();

        InOrdEntity inOrdEntityParam = InOrdEntity.builder()
            .whCd(WH_CD)
            .slipNo(SLIP_NO)
            .releaseGbn(ReleaseType.PLUS_DAY_1.getCode())
            .cenArvGbn(CenterArrivalType.NORMAL.getCode())
            .cenArvDt(TODAY)
            .cenArvTime("1111")
            .build();

        // when
        this.purchaseOrderMapper.cenArvTimeSave(inOrdEntityParam);
        List<InOrdEntity> inOrdEntityListFromDB = this.purchaseOrderMapper.getInOrdList(WH_CD, SLIP_NO);

        // then
        for (InOrdEntity inOrdEntityFromDB : inOrdEntityListFromDB) {
            assertEquals(inOrdEntityParam.getSlipNo(), inOrdEntityFromDB.getSlipNo());
            assertEquals(inOrdEntityParam.getReleaseGbn(), inOrdEntityFromDB.getReleaseGbn());
            assertEquals(inOrdEntityParam.getCenArvGbn(), inOrdEntityFromDB.getCenArvGbn());
            assertEquals(inOrdEntityParam.getCenArvDt(), inOrdEntityFromDB.getCenArvDt(), "주어진 센터도착일시와 같아야한다");
            assertEquals(inOrdEntityParam.getCenArvTime(), inOrdEntityFromDB.getCenArvTime(), "주어진 센터도착시간과 같아야한다");
            assertEquals("SOLO", inOrdEntityFromDB.getUpdId());
        }
    }

    @Test
    @DisplayName("[입고검수] TB_IN_WRK_INFO_TEMP 생성 - InWrkInfoTempEntity 로 1건 생성되고, parameter 와 DB 조회값이 같으면 성공. SEQ는 1로 고정")
    public void wrkInfoTempSave_test() {
        // given
        InWrkInfoEntity inWrkInfoTempEntityParam = InWrkInfoEntity.builder()
            .ordDt(TODAY)
            .whCd(WH_CD)
            .slipNo(SLIP_NO)
            .cstCd(CST_CD)
            .inWay(InWay.PARCEL.getCode())
            .pltCnt(1)
            .subBoxCnt(2)
            .godBarcdAttCnt(3)
            .weight5(5)
            .weight10(10)
            .weight15(15)
            .weight20(20)
            .weight20Over(25)
            .mixInYn("Y")
            .mixInCnt(3)
            .inRtnPay(1000)
            .releaseGbn(ReleaseType.PLUS_DAY_1.getCode())
            .build();

        // when
        this.purchaseOrderMapper.wrkInfoTempSave(inWrkInfoTempEntityParam);
        InWrkInfoEntity inWrkInfoTempEntityFromDB = this.purchaseOrderMapper.getWrkInfoTemp(inWrkInfoTempEntityParam);

        // then
        assertNotNull(inWrkInfoTempEntityFromDB);
        assertEquals(inWrkInfoTempEntityParam.getOrdDt(), inWrkInfoTempEntityFromDB.getOrdDt());
        assertEquals(inWrkInfoTempEntityParam.getWhCd(), inWrkInfoTempEntityFromDB.getWhCd());
        assertEquals(inWrkInfoTempEntityParam.getSlipNo(), inWrkInfoTempEntityFromDB.getSlipNo());
        assertEquals(1, inWrkInfoTempEntityFromDB.getSeq(), "SEQ 는 1로 저장되어있어야한다");
        assertEquals(inWrkInfoTempEntityParam.getCstCd(), inWrkInfoTempEntityFromDB.getCstCd());
        assertEquals(inWrkInfoTempEntityParam.getInWay(), inWrkInfoTempEntityFromDB.getInWay());
        assertEquals(inWrkInfoTempEntityParam.getPltCnt(), inWrkInfoTempEntityFromDB.getPltCnt());
        assertEquals(inWrkInfoTempEntityParam.getSubBoxCnt(), inWrkInfoTempEntityFromDB.getSubBoxCnt());
        assertEquals(inWrkInfoTempEntityParam.getGodBarcdAttCnt(), inWrkInfoTempEntityFromDB.getGodBarcdAttCnt());
        assertEquals(inWrkInfoTempEntityParam.getWeight5(), inWrkInfoTempEntityFromDB.getWeight5());
        assertEquals(inWrkInfoTempEntityParam.getWeight10(), inWrkInfoTempEntityFromDB.getWeight10());
        assertEquals(inWrkInfoTempEntityParam.getWeight15(), inWrkInfoTempEntityFromDB.getWeight15());
        assertEquals(inWrkInfoTempEntityParam.getWeight20(), inWrkInfoTempEntityFromDB.getWeight20());
        assertEquals(inWrkInfoTempEntityParam.getWeight20Over(), inWrkInfoTempEntityFromDB.getWeight20Over());
        assertEquals(inWrkInfoTempEntityParam.getMixInYn(), inWrkInfoTempEntityFromDB.getMixInYn());
        assertEquals(inWrkInfoTempEntityParam.getMixInCnt(), inWrkInfoTempEntityFromDB.getMixInCnt());
        assertEquals(inWrkInfoTempEntityParam.getInRtnPay(), inWrkInfoTempEntityFromDB.getInRtnPay());
        assertEquals(inWrkInfoTempEntityParam.getReleaseGbn(), inWrkInfoTempEntityFromDB.getReleaseGbn());
        assertEquals("SOLO", inWrkInfoTempEntityFromDB.getRegId());
    }

    @Test
    @DisplayName("[입고검수] - TB_GOD_LOT 생성 : size가 2인 List<GodLotEntity> 으로 godLot 2건 생성되고, parameter 와 DB 조회값이 같으면 성공")
    public void godLotInsert_test() {
        // given
        List<ItemEntity> itemEntityParamList = this.makeItemEntityList();
        List<GodLotEntity> godLotEntityParamList = itemEntityParamList.stream().map(
            itemEntity -> GodLotEntity.builder()
                .whCd(WH_CD)
                .godCd(itemEntity.getGodCd())
                .lotNo(itemEntity.getLotNo())
                .makeDt(itemEntity.getMakeDt())
                .distTermDt(itemEntity.getDistTermDt())
                .build()
        ).collect(Collectors.toList());

        // when
        this.purchaseOrderMapper.godLotInsert(godLotEntityParamList);

        for (int i = 0; i < godLotEntityParamList.size(); i++) {
            GodLotEntity godLotEntityParam = godLotEntityParamList.get(i);
            GodLotEntity godLotEntityFromDB = this.purchaseOrderMapper.getGodLot(godLotEntityParam);

            // then
            assertNotNull(godLotEntityFromDB);
            assertEquals(godLotEntityParam.getWhCd(), godLotEntityFromDB.getWhCd());
            assertEquals(godLotEntityParam.getLotNo(), godLotEntityFromDB.getLotNo());
            assertEquals(godLotEntityParam.getGodCd(), godLotEntityFromDB.getGodCd());
            assertEquals(godLotEntityParam.getMakeDt(), godLotEntityFromDB.getMakeDt());
            assertEquals(godLotEntityParam.getDistTermDt(), godLotEntityFromDB.getDistTermDt());
            assertEquals("SOLO", godLotEntityFromDB.getRegGear());
        }
    }

    @Test
    @DisplayName("[입고검수] - TB_IN_CHECK 생성 : itemEntityList size 만큼 생성되고, parameter 와 DB 조회값이 같으면 성공")
    public void inCheckInsert_test() {
        // given
        String slipNo = WH_CD + "ICH" + TODAY;
        List<ItemEntity> itemEntityList = this.makeItemEntityList();

        InCheckBulkInsertDTO inCheckBulkInsertDTO = InCheckBulkInsertDTO
            .builder()
            .whCd(WH_CD)
            .slipNo(slipNo)
            .cstCd(CST_CD)
            .supCd(SUP_CD)
            .itemList(itemEntityList)
            .inDiv(InDiv.PURCHASE_ORDER.getCode())
            .inWay(InWay.PARCEL.getCode())
            .inOrdSlipNo(SLIP_NO)
            .inTp("1")
            .pltCnt(1)
            .weight5(5)
            .weight10(10)
            .weight15(15)
            .weight20(20)
            .weight20Over(25)
            .subBoxCnt(2)
            .mixInYn("N")
            .mixInCnt(0)
            .godBarcdAttCnt(30)
            .inWrkPer(10)
            .inWrkTime(50)
            .payTime(50)
            .inRtnPay(1000)
            .build();

        int inOrdSeq = this.purchaseOrderMapper.getInCheckSeq(inCheckBulkInsertDTO.getWhCd(), inCheckBulkInsertDTO.getInOrdSlipNo(),
            inCheckBulkInsertDTO.getCstCd());
        inCheckBulkInsertDTO.setInOrdSeq(inOrdSeq);

        // when
        this.purchaseOrderMapper.insertBulkInCheck(inCheckBulkInsertDTO);

        InCheckEntity inCheckEntityParam = InCheckEntity.builder()
            .whCd(WH_CD)
            .cstCd(CST_CD)
            .inOrdSlipNo(SLIP_NO)
            .build();
        List<InCheckEntity> inCheckEntityListFromDB = this.purchaseOrderMapper.getInCheckList(inCheckEntityParam);

        // then
        assertEquals(inCheckEntityListFromDB.size(), itemEntityList.size());
        for (int i = 0; i < inCheckEntityListFromDB.size(); i++) {
            InCheckEntity inCheckEntityFromDB = inCheckEntityListFromDB.get(i);

            assertEquals(inCheckBulkInsertDTO.getWhCd(), inCheckEntityFromDB.getWhCd());
            assertEquals(inCheckBulkInsertDTO.getSlipNo(), inCheckEntityFromDB.getSlipNo());
            assertEquals(inCheckBulkInsertDTO.getCstCd(), inCheckEntityFromDB.getCstCd());
            assertEquals(inCheckBulkInsertDTO.getSupCd(), inCheckEntityFromDB.getSupCd());
            assertEquals("IN000", inCheckEntityFromDB.getLocNo(), "locNo 는 IN000 으로 등록되어야 한다");
            assertEquals(inCheckBulkInsertDTO.getInOrdSlipNo(), inCheckEntityFromDB.getInOrdSlipNo());
            assertEquals(inCheckBulkInsertDTO.getItemList().get(i).getGodCd(), inCheckEntityFromDB.getGodCd());
            assertEquals(inCheckBulkInsertDTO.getItemList().get(i).getLotNo(), inCheckEntityFromDB.getLotNo());
            assertEquals(inCheckBulkInsertDTO.getItemList().get(i).getOrdSeq(), inCheckEntityFromDB.getOrdSeq());
            assertEquals(inCheckBulkInsertDTO.getInDiv(), inCheckEntityFromDB.getInDiv());
            assertEquals(inCheckBulkInsertDTO.getInWay(), inCheckEntityFromDB.getInWay());
            assertEquals(inOrdSeq, inCheckEntityFromDB.getInOrdSeq(), "SEQ는 max+1 값을 등록하므로 첫 등록인 이번 테스트의 경우 SEQ 가 1이어야 한다");
            assertEquals(inCheckBulkInsertDTO.getInTp(), inCheckEntityFromDB.getInTp());
            assertEquals(inCheckBulkInsertDTO.getWeight5(), inCheckEntityFromDB.getWeight5());
            assertEquals(inCheckBulkInsertDTO.getWeight10(), inCheckEntityFromDB.getWeight10());
            assertEquals(inCheckBulkInsertDTO.getWeight15(), inCheckEntityFromDB.getWeight15());
            assertEquals(inCheckBulkInsertDTO.getWeight20(), inCheckEntityFromDB.getWeight20());
            assertEquals(inCheckBulkInsertDTO.getWeight20Over(), inCheckEntityFromDB.getWeight20Over());
            assertEquals(inCheckBulkInsertDTO.getSubBoxCnt(), inCheckEntityFromDB.getSubBoxCnt());
            assertEquals(inCheckBulkInsertDTO.getPltCnt(), inCheckEntityFromDB.getPltCnt());
            assertEquals(inCheckBulkInsertDTO.getGodBarcdAttCnt(), inCheckEntityFromDB.getGodBarcdAttCnt());
            assertEquals(inCheckBulkInsertDTO.getMixInYn(), inCheckEntityFromDB.getMixInYn());
            assertEquals(inCheckBulkInsertDTO.getMixInCnt(), inCheckEntityFromDB.getMixInCnt());
            assertEquals(inCheckBulkInsertDTO.getInWrkPer(), inCheckEntityFromDB.getInWrkPer());
            assertEquals(inCheckBulkInsertDTO.getInWrkTime(), inCheckEntityFromDB.getInWrkTime());
            assertEquals(inCheckBulkInsertDTO.getPayTime(), inCheckEntityFromDB.getPayTime());
            assertEquals(inCheckBulkInsertDTO.getInRtnPay(), inCheckEntityFromDB.getInRtnPay());
            assertEquals("SOLO", inCheckEntityFromDB.getRegGear());
        }
    }

    @Test
    @DisplayName("[입고완료] - TB_IN 생성 : ItemEntity size 만큼 생성되고, parameter 와 DB 조회값이 같으면 성공")
    public void inInsert_test() {
        // given
        List<ItemEntity> itemEntityList = this.makeItemEntityList();
        String slipNo = WH_CD + "I" + TODAY;

        InBulkInsertDTO inBulkInsertDTO = InBulkInsertDTO.builder()
            .whCd(WH_CD)
            .slipNo(slipNo)
            .cstCd(CST_CD)
            .supCd(SUP_CD)
            .inDiv(InDiv.PURCHASE_ORDER.getCode())
            .inWay(InWay.PARCEL.getCode())
            .carKind("03")
            .inOrdSlipNo(SLIP_NO)
            .inOrdSeq(1)
            .regGear("SOLO")
            .itemList(itemEntityList)
            .build();

        // when
        this.purchaseOrderMapper.inInsert(inBulkInsertDTO);
        List<InEntity> inEntityListFromDB = this.purchaseOrderMapper.getInList(WH_CD, SLIP_NO);

        // then
        assertEquals(inEntityListFromDB.size(), itemEntityList.size());
        for (int i = 0; i < inEntityListFromDB.size(); i++) {
            InEntity inEntityFromDB = inEntityListFromDB.get(i);
            assertEquals(TODAY, inEntityFromDB.getInDt(), "inDt 는 오늘이어야 한다");
            assertEquals(inBulkInsertDTO.getWhCd(), inEntityFromDB.getWhCd());
            assertEquals(slipNo, inEntityFromDB.getSlipNo());
            assertEquals(inBulkInsertDTO.getCstCd(), inEntityFromDB.getCstCd());
            assertEquals(inBulkInsertDTO.getSupCd(), inEntityFromDB.getSupCd());
            assertEquals("SOLO-LOC", inEntityFromDB.getLocNo(), "locNo는 SOLO-LOC 이어야 한다(미정임)");
            assertEquals("NONE", inEntityFromDB.getLuNo(), "luNo 는 NONE 이어야 한다");
            assertEquals(inBulkInsertDTO.getItemList().get(i).getLotNo(), inEntityFromDB.getLotNo());
            assertEquals(inBulkInsertDTO.getItemList().get(i).getGodCd(), inEntityFromDB.getGodCd());
            assertEquals(inBulkInsertDTO.getItemList().get(i).getQty(), inEntityFromDB.getInQty());
            assertEquals(inBulkInsertDTO.getInDiv(), inEntityFromDB.getInDiv());
            assertEquals(inBulkInsertDTO.getItemList().get(i).getInPr(), inEntityFromDB.getInPr());
            assertEquals(inBulkInsertDTO.getInWay(), inEntityFromDB.getInWay());
            assertEquals(inBulkInsertDTO.getCarKind(), inEntityFromDB.getCarKind());
            assertEquals(inBulkInsertDTO.getPltCnt(), 0, "pltCnt는 0이어야 한다");
            assertEquals(inBulkInsertDTO.getInOrdSlipNo(), inEntityFromDB.getInOrdSlipNo());
            assertEquals(inBulkInsertDTO.getInOrdSeq(), inEntityFromDB.getInOrdSeq());
            assertEquals(inBulkInsertDTO.getRegGear(), inEntityFromDB.getRegGear());
            assertEquals(inBulkInsertDTO.getItemList().get(i).getOrdSeq(), inEntityFromDB.getOrdSeq());
        }
    }

    @Test
    @DisplayName("[입고완료] - TB_IN_WRK_INFO 생성 : 1건 생성되고, parameter 와 DB 조회값이 같으면 성공")
    public void insInWrkInfo_test() {
        // given
        this.insertInOrdTestData();

        InWrkInfoEntity inWrkInfoTempEntityParam = InWrkInfoEntity.builder()
            .ordDt(TODAY)
            .whCd(WH_CD)
            .slipNo(SLIP_NO)
            .seq(1)
            .cstCd(CST_CD)
            .inWay(InWay.PARCEL.getCode())
            .pltCnt(1)
            .subBoxCnt(2)
            .godBarcdAttCnt(3)
            .weight5(5)
            .weight10(10)
            .weight15(15)
            .weight20(20)
            .weight20Over(25)
            .mixInYn("Y")
            .mixInCnt(3)
            .inRtnPay(1000)
            .releaseGbn(ReleaseType.PLUS_DAY_1.getCode())
            .rateGbn("R12")
            .build();

        // when
        this.purchaseOrderMapper.insInWrkInfo(inWrkInfoTempEntityParam);
        InWrkInfoEntity inWrkInfoEntityFromDB = this.purchaseOrderMapper.getInWrkInfo(inWrkInfoTempEntityParam);

        // then
        assertNotNull(inWrkInfoEntityFromDB);
        assertEquals(TODAY, inWrkInfoEntityFromDB.getOrdDt(), "TB_IN_ORD 의 ORD_DT 와 같아야 한다");
        assertEquals(inWrkInfoTempEntityParam.getWhCd(), inWrkInfoEntityFromDB.getWhCd());
        assertEquals(inWrkInfoTempEntityParam.getSlipNo(), inWrkInfoEntityFromDB.getSlipNo());
        assertEquals(inWrkInfoTempEntityParam.getSeq(), inWrkInfoEntityFromDB.getSeq());
        assertEquals(inWrkInfoTempEntityParam.getCstCd(), inWrkInfoEntityFromDB.getCstCd());
        assertEquals(TODAY, inWrkInfoEntityFromDB.getInArvDt(), "InArvDt 는 오늘이어야 한다");
        assertEquals(inWrkInfoTempEntityParam.getInWrkPer(), inWrkInfoEntityFromDB.getInWrkPer());
        assertEquals(inWrkInfoTempEntityParam.getInWrkTime(), inWrkInfoEntityFromDB.getInWrkTime());
        assertEquals(inWrkInfoTempEntityParam.getPayTime(), inWrkInfoEntityFromDB.getPayTime());
        assertEquals(inWrkInfoTempEntityParam.getPltCnt(), inWrkInfoEntityFromDB.getPltCnt());
        assertEquals(inWrkInfoTempEntityParam.getSubBoxCnt(), inWrkInfoEntityFromDB.getSubBoxCnt());
        assertEquals(inWrkInfoTempEntityParam.getGodBarcdAttCnt(), inWrkInfoEntityFromDB.getGodBarcdAttCnt());
        assertEquals(inWrkInfoTempEntityParam.getWeight5(), inWrkInfoEntityFromDB.getWeight5());
        assertEquals(inWrkInfoTempEntityParam.getWeight10(), inWrkInfoEntityFromDB.getWeight10());
        assertEquals(inWrkInfoTempEntityParam.getWeight15(), inWrkInfoEntityFromDB.getWeight15());
        assertEquals(inWrkInfoTempEntityParam.getWeight20(), inWrkInfoEntityFromDB.getWeight20());
        assertEquals(inWrkInfoTempEntityParam.getWeight20Over(), inWrkInfoEntityFromDB.getWeight20Over());
        assertEquals(inWrkInfoTempEntityParam.getMixInYn(), inWrkInfoEntityFromDB.getMixInYn());
        assertEquals(inWrkInfoTempEntityParam.getMixInCnt(), inWrkInfoEntityFromDB.getMixInCnt());
        assertEquals(inWrkInfoTempEntityParam.getInRtnPay(), inWrkInfoEntityFromDB.getInRtnPay());
        assertEquals(inWrkInfoTempEntityParam.getRateGbn(), inWrkInfoEntityFromDB.getRateGbn(), "지정한 RateGbn 으로 저장되어 있어야 한다");
    }

    @Test
    @DisplayName("[입고요청취소] - 히스토리 테이블에 넣고 입고요청건 삭제되면 성공")
    public void inOrdCnclHistInsert_inOrdCancel_test() {
        // given
        this.insertInOrdTestData(); // 테스트 입고요청 데이터 생성
        InOrdEntity inOrdEntity = InOrdEntity.builder()
            .whCd(WH_CD)
            .slipNo(SLIP_NO)
            .build();

        // when
        this.purchaseOrderMapper.inOrdCnclHistInsert(inOrdEntity);  // 히스토리 테이블 생성
        this.purchaseOrderMapper.inOrdCancel(inOrdEntity);  // 삭제 요청

        // then
        List<InOrdEntity> inOrdEntityFromDB = this.purchaseOrderMapper.getInOrdList(WH_CD, SLIP_NO);
        assertEquals(0, inOrdEntityFromDB.size());
    }


    private List<ItemEntity> makeItemEntityList() {
        return List.of(
            ItemEntity.builder()
                .godCd("01270HA00003")
                .distTermDt("20250101")
                .makeDt("20210101")
                .qty(100)
                .ordSeq(1)
                .inPr(10000)
                .lotNo("A0001")
                .locNo("SOLO-LOC")
                .build(),
            ItemEntity.builder()
                .godCd("01270HA00001")
                .distTermDt("20260101")
                .makeDt("20220101")
                .qty(50)
                .ordSeq(2)
                .inPr(20000)
                .lotNo("B0001")
                .locNo("SOLO-LOC")
                .build()
        );
    }
*/

}