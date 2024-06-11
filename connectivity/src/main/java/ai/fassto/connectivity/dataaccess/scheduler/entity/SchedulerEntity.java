package ai.fassto.connectivity.dataaccess.scheduler.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerEntity {
    private Integer seq; // int unsigned auto_increment comment '스케줄러 순번'
    private String type; // varchar(32)   not null comment '스케줄러 유형 : DAILY_STOCK (재고 일배치)',
    private String status; // varchar(32)   not null comment '스케줄러 상태(REGISTERED: 등록, IN_PROGRESS: 진행 중, COMPLETED: 완료, FAILED: 실패, CANCELLED: 취소)',
    private String taskId; // char(36)   not null comment 작업 아이디(조회를 위한 작업 구분)',
    private String meta;  //  varchar(64)   not null comment 'type에 따라서 WH_CD 가 될 수도 있고 WH_CD:::ITEM_CD가 될 수 있음',
    private String description; // varchar(1024) null comment '설명: 실패사유',
    private String email; // varchar(128)  null comment '알림을 위한 이메일',
    private LocalDateTime createdAt; // datetime      not null comment '스케줄러 생성 일시',
    private LocalDateTime startedAt; // datetime      null comment '스케줄러 작업 시작 일시',
    private LocalDateTime completedAt; //  datetime      null comment '작업 완료 일시: 성공/실패/취소 시간'
    private LocalDateTime reservedAt; // datetime  not null comment '실행시간 예약'
}
