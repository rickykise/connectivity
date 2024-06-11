package ai.fassto.connectivity.dataaccess.salesorder.mapper;

//@MybatisTest    //디폴트 롤백 설정됨 @Transactional 포함
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ImportAutoConfiguration(PrimaryDbConfig.class)
class SalesOrderMapperTest {
    /*
    @Autowired
    private SalesOrderMapper salesOrderMapper;
    //수연
    private static final String WH_CD = "DT01";
    private static final String OUT_ORD_SLIP_NO = "1DT01OO22012800004";
    private static final String CST_CD = "01270";
    private static final String SHOP_CD = "99999";
    private static final String WORK_DATE = "20221118";

    //동욱
    private static final String WH_CD2 = "DT01";
    private static final String OUT_ORD_SLIP_NO2 = "1DT01OO221116000003";
    private static final String CST_CD2 = "09911";
    private static final String SHOP_CD2 = "99999999";


    @BeforeEach
    @DisplayName("기본 데이터 세팅")
    public void setUp(){
        salesOrderMapper.insertOutOrd(OutOrdEntity.builder()
                .whCd(WH_CD)
                .cstCd(CST_CD)
                .slipNo(OUT_ORD_SLIP_NO)
                .shopCd(SHOP_CD)
                .godCd("01270HA\u000200001")
                .ordQty(1)
                .build()
        );
        salesOrderMapper.insertOutOrd(OutOrdEntity.builder()
                .whCd(WH_CD)
                .slipNo(OUT_ORD_SLIP_NO)
                .cstCd(CST_CD)
                .shopCd(SHOP_CD)
                .godCd("01270HA\u000200002")
                .ordQty(2)
                .build()
        );
    }

    @Test
    @DisplayName("출고요청정보 조회")
    public void getOutOrd_test() {
        // given
        OutOrdEntity outOrdEntityParam = OutOrdEntity.builder()
            .whCd(WH_CD)
            .slipNo(OUT_ORD_SLIP_NO)
            .cstCd(CST_CD)
            .build();

        // when
        List<OutOrdEntity> outOrdEntityFromDBList = this.salesOrderMapper.findOutOrd(outOrdEntityParam);

        // then
        outOrdEntityFromDBList.stream().forEach(v-> assertNotNull(v));
    }

    @Test
    @DisplayName("출고 작업 상태 변경")
    public void updateOutboundOrderWorkStatus_test() {
        // given
        String slipNo = "1DT01OO22012800004";
        String workStatus = SalesOrderWorkStatus.S2.getCode();
        OutOrdEntity outOrdEntityParam = OutOrdEntity.builder()
            .whCd(WH_CD)
            .slipNo(slipNo)
            .cstCd(CST_CD)
            .wrkStat(workStatus)
            .build();

        // when
        this.salesOrderMapper.updateOutboundOrderWorkStatus(outOrdEntityParam);
        List<OutOrdEntity> outOrdEntityFromDBList = this.salesOrderMapper.findOutOrd(outOrdEntityParam);

        // then
        outOrdEntityFromDBList.stream().forEach(v-> assertEquals(v.getWrkStat(), workStatus,"작업상태 출고작업중(2)으로 변경되면 성공"));
    }

    @Test
    @DisplayName("[출고 지시 확정] TB_OUT_PICK_MAP 생성 : parameter 와 DB 조회값이 같으면 성공")
    public void insertOutPickMap_test() {
        // given
        OutPickMapEntity outPickMapEntityParam = OutPickMapEntity.builder()
            .whCd(WH_CD)
            .outOrdSlipNo(OUT_ORD_SLIP_NO)
            .cstCd(CST_CD)
            .pickDt(WORK_DATE)
            .build();

        // when
        this.salesOrderMapper.insertOutPickMap(outPickMapEntityParam);
        OutPickMapEntity outPickMapEntityFromDB = this.salesOrderMapper.findOutPickMap(outPickMapEntityParam);

        // then
        assertNotNull(outPickMapEntityFromDB.getMapSlipNo());
        assertEquals(OUT_ORD_SLIP_NO, outPickMapEntityFromDB.getOutOrdSlipNo());
        assertEquals(WH_CD, outPickMapEntityFromDB.getWhCd());
        assertEquals(CST_CD, outPickMapEntityFromDB.getCstCd());
        assertEquals(WORK_DATE, outPickMapEntityFromDB.getPickDt(), "피킹일자는 request로 받은 작업일자와 동일해야한다");
        assertEquals("O", outPickMapEntityFromDB.getPickOrdType(), "오더피킹으로 고정");
    }


    @Test
    @DisplayName("[출고 지시 확정] TB_OUT_ORD_SET 생성 : itemEntityList size 만큼 생성되고, parameter 와 DB 조회값이 같으면 성공")
    public void insertOutOrdSet_test() {
        // given
        String slipNo = "1DT01OO221116000002";
        List<ItemEntity> itemEntityList = this.makeItemEntityList();

        OutOrdSetBulkInsertDTO outOrdSetBulkInsertDTO = OutOrdSetBulkInsertDTO.builder()
            .ordDt("20221115")
            .whCd(WH_CD)
            .slipNo(slipNo)
            .cstCd(CST_CD)
            .shopCd(SHOP_CD)
            .ordNo("ordTestNo")
            .ordSeq(1)
            .itemList(itemEntityList)
            .build();

        // when
        this.salesOrderMapper.insertBulkOutOrdSet(outOrdSetBulkInsertDTO);
        List<OutOrdSetEntity> outOrdSetEntityListFromDB = this.salesOrderMapper.findOutOrdSet(outOrdSetBulkInsertDTO.toOutOrdSetEntity());

        // then
        assertEquals(itemEntityList.size(), outOrdSetEntityListFromDB.size());
        for (int i = 0; i < outOrdSetEntityListFromDB.size(); i++) {
            OutOrdSetEntity outOrdSetEntityFromDB = outOrdSetEntityListFromDB.get(i);
            assertEquals(outOrdSetEntityFromDB.getOrdDt(), outOrdSetBulkInsertDTO.getOrdDt());
            assertEquals(outOrdSetEntityFromDB.getSlipNo(), outOrdSetBulkInsertDTO.getSlipNo());
            assertEquals(outOrdSetEntityFromDB.getCstCd(), outOrdSetBulkInsertDTO.getCstCd());
            assertEquals(outOrdSetEntityFromDB.getShopCd(), outOrdSetBulkInsertDTO.getShopCd());
            assertEquals(outOrdSetEntityFromDB.getOrdNo(), outOrdSetBulkInsertDTO.getOrdNo());
            assertEquals(outOrdSetEntityFromDB.getOrdSeq(), outOrdSetBulkInsertDTO.getOrdSeq());
            assertEquals(outOrdSetEntityFromDB.getGodCd(), outOrdSetBulkInsertDTO.getItemList().get(i).getGodCd());
            assertEquals(outOrdSetEntityFromDB.getDistTermDt(), outOrdSetBulkInsertDTO.getItemList().get(i).getDistTermDt());
            assertEquals(outOrdSetEntityFromDB.getOrdQty(), outOrdSetBulkInsertDTO.getItemList().get(i).getOrdQty());
            assertEquals(outOrdSetEntityFromDB.getSetQty(), outOrdSetBulkInsertDTO.getItemList().get(i).getSetQty());
        }
    }

    @Test
    @DisplayName("[출고 지시 확정] TB_OUT_PICK_ORD 생성 : itemEntityList size 만큼 생성되고, parameter 와 DB 조회값이 같으면 성공")
    public void insertOutPickOrd_test() {
        // given
        List<ItemEntity> itemEntityList = this.makeItemEntityList();

        OutPickOrdBulkInsertDTO outPickOrdBulkInsertDTO = OutPickOrdBulkInsertDTO.builder()
            .pickDt(WORK_DATE)
            .whCd(WH_CD)
            .outOrdSlipNo(OUT_ORD_SLIP_NO)
            .cstCd(CST_CD)
            .shopCd(SHOP_CD)
            .itemList(itemEntityList)
            .build();

        // when
        this.salesOrderMapper.insertOutPickOrd(outPickOrdBulkInsertDTO);
        List<OutPickOrdEntity> outPickOrdEntityListFromDB = this.salesOrderMapper.findOutPickOrdList(
            outPickOrdBulkInsertDTO.getWhCd(),
            outPickOrdBulkInsertDTO.getOutOrdSlipNo()
        );

        // then
        assertEquals(itemEntityList.size(), outPickOrdEntityListFromDB.size());
        for (OutPickOrdEntity outPickOrdEntityFromDB : outPickOrdEntityListFromDB) {
            assertEquals(WORK_DATE, outPickOrdEntityFromDB.getPickDt(), "피킹일자는 request로 받은 작업일자와 동일해야한다");
            assertEquals(1, outPickOrdEntityFromDB.getPickSeq(), "피킹차수는 채번없이 1로 고정한다");
        }
    }

    @Test
    @DisplayName("[피킹] item Request 대로 피킹수량 업데이트 되면 성공")
    public void updatePickingQty_test() {
        // given
        List<ItemEntity> itemEntityList = this.makeItemEntityList();

        // update 대상 데이터 생성
        OutPickOrdBulkInsertDTO outPickOrdBulkInsertDTO = OutPickOrdBulkInsertDTO.builder()
            .pickDt(WORK_DATE)
            .whCd(WH_CD)
            .outOrdSlipNo(OUT_ORD_SLIP_NO)
            .cstCd(CST_CD)
            .shopCd(SHOP_CD)
            .itemList(itemEntityList)
            .build();
        this.salesOrderMapper.insertOutPickOrd(outPickOrdBulkInsertDTO);

        // update param
        OutPickOrdBulkUpdateDTO outPickOrdBulkUpdateDTO = OutPickOrdBulkUpdateDTO.builder()
            .whCd(WH_CD)
            .outOrdSlipNo(OUT_ORD_SLIP_NO)
            .cstCd(CST_CD)
            .shopCd(SHOP_CD)
            .itemList(itemEntityList)
            .build();

        // when
        this.salesOrderMapper.updatePickingQty(outPickOrdBulkUpdateDTO);
        List<OutPickOrdEntity> outPickOrdEntityListFromDB = this.salesOrderMapper.findOutPickOrdList(
            outPickOrdBulkUpdateDTO.getWhCd(),
            outPickOrdBulkUpdateDTO.getOutOrdSlipNo()
        );

        // then
        assertEquals(itemEntityList.size(), outPickOrdEntityListFromDB.size());
        for (int i = 0; i < outPickOrdEntityListFromDB.size(); i++) {
            OutPickOrdEntity outPickOrdEntityFromDB = outPickOrdEntityListFromDB.get(i);

            assertEquals(WH_CD, outPickOrdEntityFromDB.getWhCd());
            assertEquals(itemEntityList.get(i).getPickQty(), outPickOrdEntityFromDB.getPickQty());
        }

    }

    private List<ItemEntity> makeItemEntityList() {
        return List.of(
            ItemEntity.builder()
                .ordSeq(1)
                .godCd("01270HA00007")
                .pickQty(7)
                .ordQty(7)
                .setQty(7)
                .distTermDt("20240110")
                .makeDt("20220101")
                .locNo("A012401")
                .lotNo("18083101")
                .luNo("NONE")
                .build(),
            ItemEntity.builder()
                .ordSeq(2)
                .godCd("01270HA00009")
                .pickQty(5)
                .ordQty(5)
                .setQty(5)
                .distTermDt("20250101")
                .makeDt("20220101")
                .locNo("A012404")
                .lotNo("28083321")
                .luNo("NONE")
                .build()
        );
    }

    *//** 검수패킹 **//*
    @Test
    @DisplayName("1.검수패킹 단계 기존 부자재 피킹, 패킹, 확정수량 변경 (부자재 추가)")
    public void updateSubMateOutPickOrd(){
        String outSlipNo = "1DT01OO221116000003";
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .cstCd(CST_CD2)
                .godCd("09911B1112255")
                .outOrdSlipNo(outSlipNo)
                .pickSeq(4)
                .packQty(4)
                .setQty(4)
                .build();
        salesOrderMapper.updateSubMateOutPickOrd(outPickOrdEntity);
    }

    @Test
    @DisplayName("2.검수패킹 단계 부자재 추가 피킹차수 조회 후 피킹정보 등록 (부자재 추가), [검증완료]")
    public void insertSubMateOutPickOrd(){
        //추가된 부자재 상품개수 만큼 등록하기(insertBulk)
        //초기 DB 데이터 설정 필요(tb_out_pick_ord, SP_OUT_ORD_SET_DAS_VER_STOCK 프로시저로 생성)
        //tb_out_pick_ord에 피킹 상품 등록 되어 있어야함
        String outOrdSlipNo = "1DT01OO221116000003";
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .pickSeq(null) //피킹테이블에 먼저 등록된 상품기준으로 PICK_SEQ값을 가져옴 (부자재가 아닌)
                .outOrdSlipNo(outOrdSlipNo)
                .cstCd(CST_CD2)
                .shopCd(SHOP_CD2)
                .locNo("C010501")
                .luNo("NONE")
                .lotNo("22111701")
                .godCd("09911B1112255")
                .setQty(4)
                .pickQty(4)
                .packQty(4)
                .ordNo("234234234")
                .ordSeq(1)
                .wrkStat("1")
                .pickGear("")
                .build();

        //피킹차수(pick_seq) 조회 후 피킹 테이블에 등록
        salesOrderMapper.insertSubMateOutPickOrd(outPickOrdEntity);
    }

    @Test
    @DisplayName("3.출고패킹 검수 수량 설정, [검증완료]")
    public void updateCheckPackQty(){
        //데이터 세팅
        String godCd = "09911B112233";   //로열젤리
        String godCd2 = "09911B1112255"; //로열젤리 팸플릿
        Integer packQty = 5;
        Integer packQty2 = 4;
        String locNo = "C010101";
        String locNo2 = "C010501";
        String lotNo = "22110801";
        String lotNo2 = "22111701";
        List<String> godCdList = List.of(godCd, godCd2);
        List<Integer> packQtyList = List.of(packQty, packQty2);
        List<String> locNoList = List.of(locNo, locNo2);
        List<String> lotNoList = List.of(lotNo, lotNo2);
        salesOrderMapper.insertOutPickOrd(OutPickOrdBulkInsertDTO.builder()
                        .pickDt(WORK_DATE)
                        .whCd(WH_CD2)
                        .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                        .cstCd(CST_CD2)
                        .shopCd(SHOP_CD2)
                        .itemList(List.of(ItemEntity.builder().ordSeq(1).godCd(godCd).pickQty(5).ordQty(5).setQty(5).distTermDt("20250101").makeDt("20220101")
                                                    .locNo(locNo).lotNo(lotNo).luNo("NONE").build(),
                                            ItemEntity.builder().ordSeq(2).godCd(godCd2).pickQty(4).ordQty(4).setQty(4).distTermDt("20250102").makeDt("20220102")
                                                    .locNo(locNo2).lotNo(lotNo2).luNo("NONE").build())).build());

        //패킹검수 수량 설정 Bulk Update
        salesOrderMapper.updateBulkPackQty(OutPackQtyBulkUpdateDTO.builder()
                        .whCd(WH_CD2)
                        .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                        .packGodCancelYn("N")
                        .itemList(List.of(
                                ItemEntity.builder().locNo(locNo).lotNo(lotNo).luNo("NONE").godCd(godCd).packQty(packQty).build(),
                                ItemEntity.builder().locNo(locNo2).lotNo(lotNo2).luNo("NONE").godCd(godCd2).packQty(packQty2).build())
                        ).build());

        //패킹 조회
        List<OutPickOrdEntity> outPickOrdListFromDB = salesOrderMapper.findOutPickOrdList(WH_CD2, OUT_ORD_SLIP_NO2);

        //검증
        assertNotEquals(outPickOrdListFromDB.size(), 0);
        for(int i=0; i < outPickOrdListFromDB.size();i++){
            assertEquals(outPickOrdListFromDB.get(i).getGodCd(), godCdList.get(i));
            assertEquals(outPickOrdListFromDB.get(i).getLocNo(), locNoList.get(i));
            assertEquals(outPickOrdListFromDB.get(i).getLotNo(), lotNoList.get(i));
            assertEquals(outPickOrdListFromDB.get(i).getPackQty(), packQtyList.get(i));
        }
    }


    @Test
    @DisplayName("4.검수패킹시 피킹 작업상태 패킹완료로 전체 변경, 2(피킹완료) -> 3(검수패킹완료), [검증완료]")
    public void updateOutPickWorkStatus(){
        //데이터 세팅
        String godCd = "09911B112233";   //로열젤리
        String godCd2 = "09911B1112255"; //로열젤리 팸플릿
        String locNo = "C010101";
        String locNo2 = "C010501";
        String lotNo = "22110801";
        String lotNo2 = "22111701";
        salesOrderMapper.insertOutPickOrd(OutPickOrdBulkInsertDTO.builder()
                .pickDt(WORK_DATE)
                .whCd(WH_CD2)
                .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                .cstCd(CST_CD2)
                .shopCd(SHOP_CD2)
                .itemList(List.of(ItemEntity.builder().ordSeq(1).godCd(godCd).pickQty(5).ordQty(5).setQty(5).distTermDt("20250101").makeDt("20220101")
                                .locNo(locNo).lotNo(lotNo).luNo("NONE").build(),
                        ItemEntity.builder().ordSeq(2).godCd(godCd2).pickQty(4).ordQty(4).setQty(4).distTermDt("20250102").makeDt("20220102")
                                .locNo(locNo2).lotNo(lotNo2).luNo("NONE").build())).build());

        String wrkStat = SalesPickOrderWorkStatus.PICK3.getCode(); //검수패킹완료(3)
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .wrkStat(wrkStat)
                .outOrdSlipNo(OUT_ORD_SLIP_NO2).build();

        //작업상태 검수패킹완료(3)로 변경
        salesOrderMapper.updateOutPickWorkStatus(outPickOrdEntity);

        //검증
        List<OutPickOrdEntity> outPickOrdListFromDB = salesOrderMapper.findOutPickOrdList(WH_CD2, OUT_ORD_SLIP_NO2);
        outPickOrdListFromDB.stream().forEach(v-> assertEquals(v.getWrkStat(), wrkStat));
    }

    @Test
    @DisplayName("?.검수패킹시 출고지시 작업상태 변경, 2(출고작업중) -> 3(출고완료)")
    public void updateOutOrderWrkStat(){
        String slipNo = "1DT01OO221116000003";
        OutOrdEntity outOrdEntity = OutOrdEntity.builder()
                .whCd(WH_CD2)
                .wrkStat("3") //출고완료
                .slipNo(slipNo).build();
        salesOrderMapper.updateOutboundOrderWorkStatus(outOrdEntity);  //출고지시, wrkStat -> 3(출고완료)
    }

    @Test
    @DisplayName("5.취소시 출고지시 작업상태 변경, -> 9(재고결품부족), [검증완료]")
    public void updateOutOrderCancelWorkStatus(){
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                .build();
        salesOrderMapper.updateOutOrderCancelWorkStatus(outPickOrdEntity);
    }

    @Test
    @DisplayName("6.긴급작엽 여부가 Y이지만 당일 출고가 일어나지 않은경우 N으로 처리, [검증완료]")
    public void updateOutOrdEmgrYn(){
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                .build();
        salesOrderMapper.updateOutOrdEmgrYn(outPickOrdEntity);
    }

    @Test
    @DisplayName("7.긴급작엽 여부가 Y -> N으로 변경된 경우 TB_OUT_PACK.WORK_TP = '1' 변경")
    public void updateOutPackWorkTp(){
        String outSlipNo = "1DT01OO221117000001";
        OutPackEntity outPackEntity = OutPackEntity.builder()
                .whCd(WH_CD2)
                .workTp("1")
                .outOrdSlipNo(outSlipNo)
                .build();
        salesOrderMapper.updateOutPackWorkTp(outPackEntity);
    }

    @Test
    @DisplayName("8,9 패킹수량이 0인경우 삭제, 아닌경우 패킹 정보 저장, [검증완료]")
    public void insertOutPack(){
        //피킹 테이블 row수 만큼 패킹 정보 등록하기 (insertBulk)
        int packSeq = salesOrderMapper.findPackSeq( //패킹순번 가져오기
                OutPackEntity.builder()
                .whCd(WH_CD2)
                .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                .build()
        );
        String godCd = "09911B102233";
        String godCd2 = "09911B1112255";
        int packQty = 5;
        int packQty2 = 4;
        List<ItemEntity> itemList = List.of(ItemEntity.builder().godCd(godCd).packQty(packQty).build(),
                                            ItemEntity.builder().godCd(godCd2).packQty(packQty2).build());

        //패킹등록 Bulk Insert
        salesOrderMapper.insertBulkOutPack(
                OutPackBulkInsertDTO.builder()
                        .whCd(WH_CD2)
                        .packSeq(packSeq)           //패킹순번 넣어주기
                        .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                        .cstCd(CST_CD2)
                        .shopCd(SHOP_CD2)
                        .ordNo("234234234")
                        .ordSeq(1)
                        .bscFare(0)
                        .airFare(0)
                        .dealFare(0)
                        .shipFare(0)
                        .parcelCd("CJ")
                        .invoiceNo("346726735326")
                        .workTp("1")
                        .boxQuantity(packSeq)
                        .disposablePalette(0)
                        .fssPalette(0)
                        .kppPalette(0)
                        .oneWoodPalette(0)
                        .gearLocNumb("1113")
                        .palletYn("N")
                        .addSubMateYn("Y")
                        .itemList(itemList).build());

        //패킹등록 조회
        List<OutPackEntity> outPackListFromDB = salesOrderMapper.findOutPackList(OutPickOrdEntity.builder()
                                                                                .whCd(WH_CD2)
                                                                                .cstCd(CST_CD2)
                                                                                .outOrdSlipNo(OUT_ORD_SLIP_NO2).build());
        //검증
        assertNotEquals(outPackListFromDB.size(), 0);
        for(int i=0; i<outPackListFromDB.size(); i++){
            assertEquals(outPackListFromDB.get(i).getWhCd(), WH_CD2);
            assertEquals(outPackListFromDB.get(i).getCstCd(), CST_CD2);
            assertEquals(outPackListFromDB.get(i).getOutOrdSlipNo(), OUT_ORD_SLIP_NO2);
            assertEquals(outPackListFromDB.get(i).getGodCd(), itemList.get(i).getGodCd());
            assertEquals(outPackListFromDB.get(i).getPackQty(), itemList.get(i).getPackQty());
        }

    }

    @Test
    @DisplayName("10. 센터 부자재 등록, [검증완료]")
    public void insertOutPackWhSub(){
        //센터 부자재 데이터 수량 만큼 등록하기 (insertBulk)
        int packSeq = salesOrderMapper.findPackSeq(  //패킹순번 조회해서 가져오기
                OutPackEntity.builder()
                        .whCd(WH_CD2)
                        .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                        .build()
        );
        int packQty = 4;
        int idx = 0;
        OutPackWhSubEntity outPackWhSubEntity = OutPackWhSubEntity.builder()
                .whCd(WH_CD2)
                .packSeq(packSeq)
                .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                .cstCd(CST_CD2)
                .packQty(packQty)
                .whIdx(idx)
                .build();
        salesOrderMapper.insertOutPackWhSub(outPackWhSubEntity);
    }

    *//** 출고확정 **//*
    @Test
    @DisplayName("11. 출고확정 등록, [검증완료]")
    public void insertOut(){
        //피킹 리스트 만큼 tb_out insert (insertBulk)
        String slipNo = salesOrderMapper.getSlipNo(SlipNoEntity.builder().div("O").whCd(WH_CD2).build()); //출고확정번호 채번, "div","O", commonMapper.selectOne("classic.cmn.commInfo.getSlipNo", mainData);
        String godCd = "09911B112233";
        int packQty = 5;
        String locNo = "C010101";
        String lotNo = "22110801";
        String godCd2 = "09911B1112255";
        int packQty2 = 4;
        String locNo2 = "C010501";
        String lotNo2 = "22111701";
        List<ItemEntity> itemList = List.of(ItemEntity.builder().locNo(locNo).luNo("NONE").lotNo(lotNo).godCd(godCd).packQty(packQty).build(),
                ItemEntity.builder().locNo(locNo2).luNo("NONE").lotNo(lotNo2).godCd(godCd2).packQty(packQty2).build());

        //출고확정 등록
        salesOrderMapper.insertBulkOut(OutBulkInsertDTO.builder()
                        .whCd(WH_CD2)
                        .slipNo(slipNo)
                        .cstCd(CST_CD2)
                        .shopCd(SHOP_CD2)
                        .outOrdSlipNo(OUT_ORD_SLIP_NO2)
                        .ordNo("234234234")
                        .ordSeq(1)
                        .itemList(itemList).build()
        );
        //출고확정 조회
        List<OutEntity> outListFromDB = salesOrderMapper.findOutList(OutPickOrdEntity.builder()
                                                                        .whCd(WH_CD2)
                                                                        .cstCd(CST_CD2)
                                                                        .outOrdSlipNo(OUT_ORD_SLIP_NO2).build());
        //검증
        assertNotEquals(outListFromDB.size(), 0);
        for(int i=0; i<outListFromDB.size(); i++){
            assertEquals(outListFromDB.get(i).getWhCd(), WH_CD2);
            assertEquals(outListFromDB.get(i).getCstCd(), CST_CD2);
            assertEquals(outListFromDB.get(i).getOutOrdSlipNo(), OUT_ORD_SLIP_NO2);
            assertEquals(outListFromDB.get(i).getGodCd(), itemList.get(i).getGodCd());
            assertEquals(outListFromDB.get(i).getLocNo(), itemList.get(i).getLocNo());
            assertEquals(outListFromDB.get(i).getLotNo(), itemList.get(i).getLotNo());
            assertEquals(outListFromDB.get(i).getOutQty(), itemList.get(i).getPackQty());
        }

    }

    @Test
    @DisplayName("12. 재고 차감 (StockQty - PackQty), [검증완료]")
    public void updateWhStock(){
        //피킹 리스트 만큼 tb_wh_stock 재고 차감
        //로얄젤리 현 재고 74, -5차감 = 69
        String godCd = "09911B112233";
        String locNo = "C010101";
        String lotNo = "22110801";
        int packQty = 5;
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .locNo(locNo)
                .lotNo(lotNo)
                .godCd(godCd)
                .luNo("NONE")
                .packQty(packQty)
                .build();

        //로얄젤리 팸플릿(부자재) 현 재고 300, -4차감 = 296
        String godCd2 = "09911B1112255";
        String locNo2 = "C010501";
        String lotNo2 = "22111701";
        int packQty2 = 4;
        OutPickOrdEntity outPickOrdEntity2 = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .locNo(locNo2)
                .lotNo(lotNo2)
                .godCd(godCd2)
                .luNo("NONE")
                .packQty(packQty2)
                .build();

        salesOrderMapper.updateWhStock(outPickOrdEntity);
        salesOrderMapper.updateWhStock(outPickOrdEntity2);
        //차감됬는지 재고 테이블 확인
    }
    @Test
    @DisplayName("13. 출고지시 작업상태 출고완료(3)로 변경, [검증완료]")
    public void updateOutSetOrdWrkStat(){
        String outOrdSlipNo = "1DT01OO221116000003";
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .outOrdSlipNo(outOrdSlipNo)
                .build();
        salesOrderMapper.updateOutSetOrdWrkStat(outPickOrdEntity);
    }

    @Test
    @DisplayName("14. 출고피킹 작업상태 출고완료(4)로 변경, [검증완료]")
    public void updateOutPickOrdWrkStat(){
        String outOrdSlipNo = "1DT01OO221116000003";
        OutPickOrdEntity outPickOrdEntity = OutPickOrdEntity.builder()
                .whCd(WH_CD2)
                .outOrdSlipNo(outOrdSlipNo)
                .build();
        salesOrderMapper.updateOutPickOrdWrkStat(outPickOrdEntity);
    }
    //6번,7번 실행

    *//** 출고취소 **//*
    @Test
    @DisplayName("15.출고취소(8) 상태로 변경")
    public void updateOutCancel(){
        //검수패킹 완료단계
        String cstCancelDiv = "3";
        //지시일자 조회
        OutOrdEntity outOrdEntity = salesOrderMapper.findOneOutOrd(OutOrdDTO.builder()
                                    .whCd(WH_CD2)
                                    .cstCd(CST_CD2)
                                    .slipNo(OUT_ORD_SLIP_NO2)
                                    .build());

        salesOrderMapper.updateOutCancel(OutOrdEntity.builder()
                .ordDt(outOrdEntity.getOrdDt())
                .whCd(WH_CD2)
                .slipNo(OUT_ORD_SLIP_NO2)
                .cstCancelDiv(cstCancelDiv)
                .build());

    }
*/

}