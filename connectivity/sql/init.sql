drop table if exists TB_IN_ORD;
drop table if exists TB_IN_WRK_INFO_TEMP;
drop table if exists TB_GOD_LOT;
drop table if exists TB_IN_CHECK;
drop table if exists TB_IN;
drop table if exists TB_IN_WRK_INFO;
drop table if exists TB_IN_ORD_CNCL_HIST;

/**
  입고 요청
 */
CREATE TABLE TB_IN_ORD
(
    ORD_DT              VARCHAR(8)                               NOT NULL COMMENT '지시일자',
    WH_CD               VARCHAR(10)                              NOT NULL COMMENT '창고코드',
    SLIP_NO             VARCHAR(20)                              NOT NULL COMMENT '전표번호',
    CST_CD              VARCHAR(10)                              NOT NULL COMMENT '고객사코드',
    SUP_CD              VARCHAR(10)                              NOT NULL COMMENT '공급사코드',
    GOD_CD              VARCHAR(30)                              NOT NULL COMMENT '상품코드',
    MAKE_DT             VARCHAR(8)                               NOT NULL COMMENT '제조일자',
    DIST_TERM_DT        VARCHAR(8)                               NOT NULL COMMENT '유통기한일자',
    ORD_QTY             INT(10)                                  NULL COMMENT '지시수량',
    IN_DIV              VARCHAR(1)                               NULL COMMENT '입고구분(1:입고,2:반품)',
    ORD_NO              VARCHAR(100)                             NULL COMMENT '주문번호',
    RTN_RESERVATION     VARCHAR(1) DEFAULT 'N'                   NULL COMMENT '반품 예약 구분(N:일반 반품 신청, Y:반품 예약 신청)',
    RTN_TYPE            VARCHAR(1)                               NULL COMMENT '반품 요청 구분(1:고객반품신청, 2:센터반품신청)',
    RTN_GUBUN           VARCHAR(5)                               NULL COMMENT '반품 신청 사유 구분(01:반품, 02: 교환, 03:환불) = TB_COM_MST.MAIN_CD = ''INP13''',
    RTN_REASON          VARCHAR(5)                               NULL COMMENT '반품 신청 상세 사유 코드= TB_COM_MST.MAIN_CD = ''INP14''',
    RTN_DETAIL_REASON   TEXT                                     NULL COMMENT '반품 신청 상세 사유',
    IN_PR               INT(10)                                  NULL COMMENT '입고가',
    IN_WAY              VARCHAR(5)                               NULL COMMENT '입고방식(INP01)',
    PARCEL_CD           VARCHAR(10)                              NULL COMMENT '택배사코드(TB_COM_MST.MAIN_CD = ''STD22'') - 반품집하요청에서 처리할 때 저장',
    PARCEL_COMP         VARCHAR(50)                              NULL COMMENT '택배사 - 직접 저장할 때 사용',
    PARCEL_INVOICE_NO   VARCHAR(20)                              NULL COMMENT '택배송장번호',
    RTN_GATH_YN         VARCHAR(1)                               NULL COMMENT '반품집하여부',
    WRK_STAT            VARCHAR(1)                               NULL COMMENT '작업상태(1: 입고 반품 예정(지연), 2: 입고 검수 진행, 3: 입고 확정, 4: 입고 완료, 5: 입고 취소)',
    ORD_END_DIV         VARCHAR(1)                               NULL COMMENT '지시종결구분(1: 정상, 2: 부분입고(확정), 3: 입고취소, 4: 초과입고(확정)',
    VOLUN_END_FLAG      VARCHAR(1) DEFAULT 'N'                   NULL COMMENT '입고임의종결여부',
    REG_ID              VARCHAR(36)                              NULL COMMENT '등록자',
    REG_TIME            TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NULL COMMENT '등록시간',
    UPD_ID              VARCHAR(36)                              NULL COMMENT '수정자',
    UPD_TIME            TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NULL COMMENT '수정시간',
    REG_UPD_ID          VARCHAR(36)                              NULL COMMENT '등록수정',
    REG_UPD_TIME        TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NULL COMMENT '등록수정시간',
    OUT_ORD_SLIP_NO     VARCHAR(20)                              NULL,
    CUST_NM             VARCHAR(120)                             NULL COMMENT '반품고객명',
    ORG_PARCEL_COMP     VARCHAR(20)                              NULL COMMENT '원택배사',
    ORG_INVOICE_NO      VARCHAR(20)                              NULL COMMENT '원운송장번호',
    EMGR_YN             VARCHAR(1) DEFAULT 'N'                   NULL COMMENT '긴급작업여부',
    REMARK              TEXT                                     NULL COMMENT '비고',
    ORD_SEQ             INT(10)                                  NULL COMMENT '요 청 순 번',
    CEN_ARV_DT          VARCHAR(8)                               NULL COMMENT '센터 도착 일',
    CEN_ARV_TIME        VARCHAR(8)                               NULL COMMENT '센터 도착 시간',
    CEN_ARV_GBN         VARCHAR(8)                               NULL COMMENT '센터 도착 구분 ( 1 : 정상 도착, 2: 지연 도착, 3: 조기 도착, 0: 도착 안함 )',
    CONF_ID             VARCHAR(36)                              NULL COMMENT '입고확정자ID',
    CONF_TIME           TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NOT NULL COMMENT '입고확정시간',
    PRE_ARV             VARCHAR(1) DEFAULT 'N'                   NOT NULL COMMENT '사전도착',
    RELEASE_DT          DATETIME   DEFAULT '0000-00-00 00:00:00' NULL COMMENT '출고 가능일',
    RELEASE_GBN         VARCHAR(2)                               NULL COMMENT '출고 가능 타입 (0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고)',
    IN_CHECK_START_TIME DATETIME                                 NULL COMMENT '입고 검수 시작',
    IN_CHECK_END_TIME   DATETIME                                 NULL COMMENT '입고 검수 완료',
    DAY_INOUT_YN        VARCHAR(1) DEFAULT 'N'                   NOT NULL COMMENT '당일입출고 여부',
    ORD_RESERVE_SLIP_NO VARCHAR(20)                              NULL COMMENT '출고예약 전표번호',
    PRIMARY KEY (ORD_DT, WH_CD, SLIP_NO, CST_CD, SUP_CD, GOD_CD, MAKE_DT, DIST_TERM_DT)
)
    COMMENT '입고지시';


INSERT INTO TB_IN_ORD ( ORD_DT -- 지시일자
                      , WH_CD -- 창고코드
                      , SLIP_NO -- 전표번호
                      , CST_CD -- 고객사코드
                      , SUP_CD -- 공급사코드
                      , GOD_CD -- 상품코드
                      , MAKE_DT -- 제조일자
                      , DIST_TERM_DT -- 유통기한일자
                      , ORD_QTY -- 지시수량
                      , IN_DIV -- 입고구분
                      , IN_PR -- 입고가
                      , IN_WAY -- 입고방식  INP01
                      , PARCEL_COMP -- 택배사
                      , PARCEL_INVOICE_NO -- 택배송장번호
                      , RTN_GATH_YN -- 반품집하여부
                      , WRK_STAT -- 작업상태(1:입고예정(지연),2:입고확정,3:LU지정,4:적치)
                      , ORD_END_DIV -- 지시종결구분(1:정상,2:부분입고완료,3:입고취소)
                      , REG_ID -- 등록자
                      , REG_TIME -- 등록시간
                      , UPD_ID -- 수정자
                      , UPD_TIME -- 수정시간
                      , REG_UPD_ID -- 등록수정자
                      , REG_UPD_TIME -- 등록수정시간
                      , EMGR_YN -- 긴급여부
                      , REMARK -- 비고
                      , ORD_SEQ -- 요청 순번
                      , PRE_ARV -- 사전 도착 여부
                      , RELEASE_DT -- 출고 가능일
                      , RELEASE_GBN -- 출고 가능 타입 (0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고)
)
VALUES ( '20221114' -- 지시일자
       , 'DT01' -- 창고코드
       , '1DT01IO221114001' -- 전표번호
       , '01270' -- 고객사코드
       , '99999' -- 공급사코드
       , '01270HA00020' -- 상품코드


       , IFNULL(DATE_FORMAT(NULL, '%Y%m%d'), '') -- 제조일자


       , IFNULL(DATE_FORMAT('20230505', '%Y%m%d'), '') -- 유통기한일자

       , IFNULL(15, 0) -- 지시수량
       , '1' -- 입고구분
       , 0 -- 입고가
       , '01' -- 입고방식  INP01
       , IFNULL('', '') -- 택배사
       , IFNULL('', '') -- 택배송장번호
       , 'N' -- 반품집하여부
       , '1' -- 작업상태(1: 입고 반품 예정(지연), 2: 입고 검수 진행, 3: 입고 확정, 4: 입고 완료)
       , '1' -- 지시종결구분(1: 정상, 2: 부분입고(확정), 3: 입고취소, 4: 초과입고(확정)
       , '73e72413-74fe-11ec-9e3f-0a5b64398cfa' -- 등록자
       , NOW() -- 등록시간
       , '73e72413-74fe-11ec-9e3f-0a5b64398cfa' -- 수정자
       , NOW() -- 수정시간
       , '73e72413-74fe-11ec-9e3f-0a5b64398cfa' -- 등록수정자
       , NOW() -- 등록수정시간
       , 'N' -- 긴급여부
       , '' -- 비고
       , '1' -- 요청 순번
       , 'N' -- 사전 도착 여부
       , '2022-11-16' -- 출고 가능일
       , '2' -- 출고 타입 (0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고)
       );


INSERT INTO TB_IN_ORD ( ORD_DT -- 지시일자
                      , WH_CD -- 창고코드
                      , SLIP_NO -- 전표번호
                      , CST_CD -- 고객사코드
                      , SUP_CD -- 공급사코드
                      , GOD_CD -- 상품코드
                      , MAKE_DT -- 제조일자
                      , DIST_TERM_DT -- 유통기한일자
                      , ORD_QTY -- 지시수량
                      , IN_DIV -- 입고구분
                      , IN_PR -- 입고가
                      , IN_WAY -- 입고방식  INP01
                      , PARCEL_COMP -- 택배사
                      , PARCEL_INVOICE_NO -- 택배송장번호
                      , RTN_GATH_YN -- 반품집하여부
                      , WRK_STAT -- 작업상태(1:입고예정(지연),2:입고확정,3:LU지정,4:적치)
                      , ORD_END_DIV -- 지시종결구분(1:정상,2:부분입고완료,3:입고취소)
                      , REG_ID -- 등록자
                      , REG_TIME -- 등록시간
                      , UPD_ID -- 수정자
                      , UPD_TIME -- 수정시간
                      , REG_UPD_ID -- 등록수정자
                      , REG_UPD_TIME -- 등록수정시간
                      , EMGR_YN -- 긴급여부
                      , REMARK -- 비고
                      , ORD_SEQ -- 요청 순번
                      , PRE_ARV -- 사전 도착 여부
                      , RELEASE_DT -- 출고 가능일
                      , RELEASE_GBN -- 출고 가능 타입 (0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고)
)
VALUES ( '20221114' -- 지시일자
       , 'DT01' -- 창고코드
       , '1DT01IO221114001' -- 전표번호
       , '01270' -- 고객사코드
       , '99999' -- 공급사코드
       , '01270HA00045' -- 상품코드


       , IFNULL(DATE_FORMAT(NULL, '%Y%m%d'), '') -- 제조일자


       , IFNULL(DATE_FORMAT('20230505', '%Y%m%d'), '') -- 유통기한일자

       , IFNULL(20, 0) -- 지시수량
       , '1' -- 입고구분
       , 0 -- 입고가
       , '01' -- 입고방식  INP01
       , IFNULL('', '') -- 택배사
       , IFNULL('', '') -- 택배송장번호
       , 'N' -- 반품집하여부
       , '1' -- 작업상태(1: 입고 반품 예정(지연), 2: 입고 검수 진행, 3: 입고 확정, 4: 입고 완료)
       , '1' -- 지시종결구분(1: 정상, 2: 부분입고(확정), 3: 입고취소, 4: 초과입고(확정)
       , '73e72413-74fe-11ec-9e3f-0a5b64398cfa' -- 등록자
       , NOW() -- 등록시간
       , '73e72413-74fe-11ec-9e3f-0a5b64398cfa' -- 수정자
       , NOW() -- 수정시간
       , '73e72413-74fe-11ec-9e3f-0a5b64398cfa' -- 등록수정자
       , NOW() -- 등록수정시간
       , 'N' -- 긴급여부
       , '' -- 비고
       , '2' -- 요청 순번
       , 'N' -- 사전 도착 여부
       , '2022-11-16' -- 출고 가능일
       , '2' -- 출고 타입 (0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고)
       );


/**
  입고 검수
 */
CREATE TABLE TB_IN_WRK_INFO_TEMP
(
    ORD_DT            VARCHAR(8)                               NOT NULL COMMENT '지시일자',
    WH_CD             VARCHAR(10)                              NOT NULL COMMENT '창고코드',
    SLIP_NO           VARCHAR(20)                              NOT NULL COMMENT '전표번호',
    SEQ               INT(10)    DEFAULT 0                     NOT NULL COMMENT '전표순번',
    CST_CD            VARCHAR(10)                              NOT NULL COMMENT '고객사코드',
    IN_ARV_DT         VARCHAR(8)                               NOT NULL,
    IN_ARV_TIME       VARCHAR(4)                               NOT NULL,
    IN_WRK_PER        INT(2)                                   NULL,
    IN_WRK_TIME       INT(4)                                   NULL,
    IN_WRK_CD         VARCHAR(2)                               NULL,
    IN_RTN_PAY_CD     VARCHAR(2)                               NULL,
    REG_ID            VARCHAR(36)                              NOT NULL,
    REG_TIME          DATETIME   DEFAULT '0000-00-00 00:00:00' NULL,
    UPD_ID            VARCHAR(36)                              NOT NULL,
    UPD_TIME          DATETIME   DEFAULT '0000-00-00 00:00:00' NULL,
    CUST_NM           VARCHAR(120)                             NULL,
    IN_RTN_PAY        INT(10)                                  NULL,
    PAY_TIME          INT(4)                                   NULL COMMENT '청구시간',
    PLT_CNT           INT(10)                                  NULL COMMENT '파레트수',
    DROP_PLT_CNT      int unsigned default 0                   NOT NULL COMMENT '하차 파레트수',
    BOX_CNT           INT(10)    DEFAULT 0                     NULL COMMENT '박스수 (사용하지 않음)',
    SUB_BOX_CNT       INT(10)    DEFAULT 0                     NULL COMMENT '부자재 박스수',
    MIX_IN_YN         VARCHAR(1) DEFAULT 'N'                   NULL COMMENT '혼적 여부',
    GOD_BARCD_ATT_CNT INT(10)    DEFAULT 0                     NULL COMMENT '바코드부착수',
    WEIGHT_5          INT(10)    DEFAULT 0                     NULL COMMENT '박스 5KG 이하',
    WEIGHT_10         INT(10)    DEFAULT 0                     NULL COMMENT '박스 5KG 초과 ~ 10KG 이하',
    WEIGHT_15         INT(10)    DEFAULT 0                     NULL COMMENT '박스 10KG 초과 ~ 15KG 이하',
    WEIGHT_20         INT(10)    DEFAULT 0                     NULL COMMENT '박스 15KG 초과 ~ 25KG 이하',
    WEIGHT_20_OVER    INT(10)    DEFAULT 0                     NULL COMMENT '박스 25KG 초과 ',
    MIX_IN_CNT        INT(10)    DEFAULT 0                     NULL COMMENT '혼적 박스 수',
    RELEASE_GBN       VARCHAR(2)                               NULL COMMENT '출고 가능 타입 (0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고 )',
    IN_WAY            VARCHAR(5)                               NULL COMMENT '입고 방식',
    PRIMARY KEY (ORD_DT, WH_CD, SLIP_NO, SEQ, CST_CD)
);


create table TB_GOD_LOT
(
    WH_CD        varchar(10)                             not null comment '창고코드',
    LOT_NO       varchar(20)                             not null comment 'LOT번호',
    GOD_CD       varchar(30)                             not null comment '상품코드',
    IN_DT        varchar(8)                              null comment '입고일자',
    MAKE_DT      varchar(8)                              null comment '제조일자',
    DIST_TERM_DT varchar(8)                              null comment '유통기한일자',
    REG_GEAR     varchar(5)                              null comment '등록장비',
    REG_ID       varchar(36)                             null comment '등록자',
    REG_TIME     timestamp default '0000-00-00 00:00:00' null comment '등록시간',
    UPD_ID       varchar(36)                             null comment '수정자',
    UPD_TIME     timestamp default '0000-00-00 00:00:00' null comment '수정시간',
    primary key (WH_CD, LOT_NO, GOD_CD)
)
    comment '상품LOT';


CREATE TABLE TB_IN_CHECK
(
    IN_DT              VARCHAR(8)                               NOT NULL COMMENT '입고일자',
    WH_CD              VARCHAR(10)                              NOT NULL COMMENT '창고코드',
    SLIP_NO            VARCHAR(20)                              NOT NULL COMMENT '전표번호',
    CST_CD             VARCHAR(10)                              NOT NULL COMMENT '고객사코드',
    SUP_CD             VARCHAR(10)                              NOT NULL COMMENT '공급사코드',
    LOC_NO             VARCHAR(20)                              NOT NULL COMMENT '로케이션번호',
    LU_NO              VARCHAR(20)                              NOT NULL COMMENT 'LU번호',
    LOT_NO             VARCHAR(20)                              NOT NULL COMMENT 'LOT번호',
    GOD_CD             VARCHAR(30)                              NOT NULL COMMENT '상품코드',
    IN_QTY             INT(10)                                  NULL COMMENT '입고수량',
    IN_QTY_SCAN_CNT    INT(10)                                  NULL COMMENT '입고수량 SCAN COUNT',
    IN_DIV             VARCHAR(1)                               NULL COMMENT '입고구분(1:입고,2:반품)',
    IN_PR              INT(10)                                  NULL COMMENT '입고가',
    IN_WAY             VARCHAR(5)                               NULL COMMENT '입고방식(INP01)',
    CAR_KIND           VARCHAR(5)                               NULL COMMENT '차종(INP04)',
    REC_LOC_NO         VARCHAR(20)                              NULL COMMENT '추천로케이션번호',
    IN_ORD_SLIP_NO     VARCHAR(20)                              NULL COMMENT '입고지시전표번호',
    IN_ORD_SEQ         INT(10)                                  NULL COMMENT '입고지시순번',
    IN_TP              VARCHAR(1) DEFAULT '1'                   NULL COMMENT '입고유형(1:정상,2:고객사재고이관,9:예외)',
    PLT_CNT            INT(10)                                  NULL COMMENT '파레트수',
    DROP_PLT_CNT      int unsigned default 0                    NOT NULL COMMENT '하차 파레트수',
    BOX_CNT            INT(10)    DEFAULT 0                     NULL COMMENT '박스수',
    SUB_BOX_CNT        INT(10)    DEFAULT 0                     NULL COMMENT '부자재 박스수',
    MIX_IN_YN          VARCHAR(1) DEFAULT 'N'                   NULL COMMENT '혼적 여부',
    GOD_BARCD_ATT_CNT  INT(10)    DEFAULT 0                     NULL COMMENT '바코드부착수',
    RATE_GBN           VARCHAR(100)                             NULL,
    IN_ARV_DT          VARCHAR(8)                               NOT NULL,
    IN_ARV_TIME        VARCHAR(4)                               NOT NULL,
    IN_WRK_PER         INT(2)                                   NULL,
    IN_WRK_TIME        INT(4)                                   NULL,
    PAY_TIME           INT(4)                                   NULL COMMENT '청구시간',
    IN_WRK_CD          VARCHAR(2)                               NULL,
    IN_RTN_PAY         INT(10)                                  NULL,
    IN_RTN_PAY_CD      VARCHAR(2)                               NULL,
    RTN_GOD_CHECK_STAT VARCHAR(2)                               NULL,
    CUST_NM            VARCHAR(120)                             NULL,
    CONF_ID            VARCHAR(36)                              NULL COMMENT '입고확정자ID',
    CONF_TIME          TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NOT NULL COMMENT '입고확정시간',
    REG_GEAR           VARCHAR(5)                               NULL COMMENT '등록장비',
    REG_ID             VARCHAR(36)                              NULL COMMENT '등록자',
    REG_TIME           TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NULL COMMENT '등록시간',
    UPD_ID             VARCHAR(36)                              NULL COMMENT '수정자',
    UPD_TIME           TIMESTAMP  DEFAULT '0000-00-00 00:00:00' NULL COMMENT '수정시간',
    REMARK             VARCHAR(100)                             NULL COMMENT '비고',
    RTN_MIS_YN         VARCHAR(1) DEFAULT 'N'                   NULL COMMENT '오반품여부',
    XY_REMARK          VARCHAR(100)                             NULL COMMENT 'X,Y존이동비고',
    RTN_BOX_TP         VARCHAR(10)                              NULL COMMENT '반품 박스사이즈',
    CST_MEMO           MEDIUMTEXT                               NULL COMMENT '고객사메모',
    ORD_SEQ            INT(10)    DEFAULT 0                     NOT NULL COMMENT '요 청 순 번',
    WEIGHT_5           INT(10)    DEFAULT 0                     NULL COMMENT '박스 5KG 이하',
    WEIGHT_10          INT(10)    DEFAULT 0                     NULL COMMENT '박스 5KG 초과 ~ 10KG 이하',
    WEIGHT_15          INT(10)    DEFAULT 0                     NULL COMMENT '박스 10KG 초과 ~ 15KG 이하',
    WEIGHT_20          INT(10)    DEFAULT 0                     NULL COMMENT '박스 15KG 초과 ~ 20KG 이하',
    WEIGHT_20_OVER     INT(10)    DEFAULT 0                     NULL COMMENT '박스 20KG 초과',
    MIX_IN_CNT         INT(10)    DEFAULT 0                     NULL COMMENT '혼적 박스 수',
    DEAL_TEMP          VARCHAR(5)                               NULL COMMENT '취급온도(STD10)',
    PRIMARY KEY (IN_DT, WH_CD, SLIP_NO, CST_CD, SUP_CD, LOC_NO, LU_NO, LOT_NO, GOD_CD, ORD_SEQ)
)
    COMMENT '입고';


create table TB_IN
(
    IN_DT              varchar(8)                               not null comment '입고일자',
    WH_CD              varchar(10)                              not null comment '창고코드',
    SLIP_NO            varchar(20)                              not null comment '전표번호',
    CST_CD             varchar(10)                              not null comment '고객사코드',
    SUP_CD             varchar(10)                              not null comment '공급사코드',
    LOC_NO             varchar(20)                              not null comment '로케이션번호',
    LU_NO              varchar(20)                              not null comment 'LU번호',
    LOT_NO             varchar(20)                              not null comment 'LOT번호',
    GOD_CD             varchar(30)                              not null comment '상품코드',
    RTN_GOD_CHECK_STAT varchar(2)                               null comment '반품상품검수상태(INP06), 01:정상, 02:불량, 03:보류',
    IN_QTY             int(10)                                  null comment '입고수량',
    IN_QTY_SCAN_CNT    int(10)                                  null comment '입고수량 SCAN COUNT',
    IN_DIV             varchar(1)                               null comment '입고구분(1:입고,2:반품)',
    IN_PR              int(10)                                  null comment '입고가',
    IN_WAY             varchar(5)                               null comment '입고방식(INP01)',
    CAR_KIND           varchar(5)                               null comment '차종(INP04)',
    PLT_CNT            int(10)                                  null comment '파레트수',
    BOX_CNT            int(3)     default 0                     null,
    REC_LOC_NO         varchar(20)                              null comment '추천로케이션번호',
    IN_ORD_SLIP_NO     varchar(20)                              null comment '입고지시전표번호',
    ORD_NO             varchar(100)                             null comment '주문번호',
    IN_ORD_SEQ         int(10)                                  null comment '입고지시순번',
    IN_TP              varchar(1) default '1'                   null comment '입고유형(1:정상,2:고객사재고이관,9:예외)',
    REG_GEAR           varchar(5)                               null comment '등록장비',
    REG_ID             varchar(36)                              null comment '등록자',
    REG_TIME           timestamp  default '0000-00-00 00:00:00' null comment '등록시간',
    UPD_ID             varchar(36)                              null comment '수정자',
    UPD_TIME           timestamp  default '0000-00-00 00:00:00' null comment '수정시간',
    REMARK             text                                     null comment '비고',
    RTN_MIS_YN         varchar(1) default 'N'                   null comment '오반품여부',
    XY_REMARK          varchar(100)                             null comment 'X,Y존이동비고',
    rtn_box_tp         varchar(10)                              null comment '반품 박스사이즈',
    CST_MEMO           text                                     null comment '고객사메모',
    ORD_SEQ            int(10)    default 0                     not null comment '요청순번',
    MIX_IN_CNT         int(10)    default 0                     null comment '혼적 박스 수',
    primary key (IN_DT, WH_CD, SLIP_NO, CST_CD, SUP_CD, LOC_NO, LU_NO, LOT_NO, GOD_CD, ORD_SEQ)
)
    comment '입고';

create table TB_IN_WRK_INFO
(
    ORD_DT            varchar(8)                               not null comment '지시일자',
    WH_CD             varchar(10)                              not null comment '창고코드',
    SLIP_NO           varchar(20)                              not null comment '전표번호',
    SEQ               int(10)    default 0                     not null comment '전표순번',
    CST_CD            varchar(10)                              not null comment '고객사코드',
    IN_ARV_DT         varchar(8)                               not null,
    IN_ARV_TIME       varchar(4)                               not null,
    IN_WRK_PER        int(2)                                   null,
    IN_WRK_TIME       int(4)                                   null,
    IN_WRK_CD         varchar(2)                               null,
    IN_RTN_PAY_CD     varchar(2)                               null,
    REG_ID            varchar(36)                              not null,
    REG_TIME          timestamp  default '0000-00-00 00:00:00' null,
    UPD_ID            varchar(36)                              not null,
    UPD_TIME          timestamp  default '0000-00-00 00:00:00' null,
    CUST_NM           varchar(120)                             null,
    IN_RTN_PAY        int(10)                                  null,
    PAY_TIME          int(4)                                   null comment '청구시간',
    PLT_CNT           int(10)                                  null comment '파레트수',
    DROP_PLT_CNT      int unsigned default 0                   not null comment '하차 파레트수',
    BOX_CNT           int(10)    default 0                     null comment '박스수',
    SUB_BOX_CNT       int(10)    default 0                     null comment '부자재 박스수',
    MIX_IN_YN         varchar(1) default 'N'                   null comment '혼적 여부',
    GOD_BARCD_ATT_CNT int(10)    default 0                     null comment '바코드부착수',
    RATE_GBN          varchar(100)                             null comment '입고 할증률',
    CONF_ID           varchar(36)                              null comment '입고확정자ID',
    CONF_TIME         timestamp  default '0000-00-00 00:00:00' not null comment '입고확정시간',
    WEIGHT_5          int(10)    default 0                     null comment '박스 5kg 이하',
    WEIGHT_10         int(10)    default 0                     null comment '박스 5kg 초과 ~ 10kg 이하',
    WEIGHT_15         int(10)    default 0                     null comment '박스 10kg 초과 ~ 15kg 이하',
    WEIGHT_20         int(10)    default 0                     null comment '박스 15kg 초과 ~ 25kg 이하',
    WEIGHT_20_OVER    int(10)    default 0                     null comment '박스 25kg 초과',
    MIX_IN_CNT        int(10)    default 0                     null comment '혼적 박스 수',
    HAND_WORK_COST    int(10)    default 0                     null comment '입고비용(수작업 상하차 비용)',
    primary key (ORD_DT, WH_CD, SLIP_NO, SEQ, CST_CD)
);

create table TB_IN_ORD_CNCL_HIST
(
    ORD_DT       varchar(8)                            not null comment '지시일자',
    WH_CD        varchar(10)                           not null comment '창고코드',
    SLIP_NO      varchar(20)                           not null comment '전표번호',
    CST_CD       varchar(10)                           not null comment '고객사코드',
    SUP_CD       varchar(10)                           not null comment '공급사코드',
    GOD_CD       varchar(30)                           not null comment '상품코드',
    MAKE_DT      varchar(8)                            not null comment '제조일자',
    DIST_TERM_DT varchar(8)                            not null comment '유통기한일자',
    ORD_QTY      int(10)                               null comment '주문수량',
    IN_DIV       varchar(1)                            null comment '입고구분(1:입고,2:반품)',
    ORD_NO       varchar(100)                          null comment '주문번호',
    RTRN_CST_NM  varchar(120)                          null comment '반품고객명',
    ORG_CRR      varchar(20)                           null comment '원택배사',
    ORG_INVC_NO  varchar(20)                           null comment '원운송장번호',
    REG_TIME     timestamp default current_timestamp() not null comment '등록일자',
    REG_ID       varchar(50)                           not null comment '등록자 ID',
    primary key (ORD_DT, WH_CD, SLIP_NO, CST_CD, SUP_CD, GOD_CD, MAKE_DT, DIST_TERM_DT)
)
    comment '입고지시 취소 히스토리'