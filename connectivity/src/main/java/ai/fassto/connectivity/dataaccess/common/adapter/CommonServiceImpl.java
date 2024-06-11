package ai.fassto.connectivity.dataaccess.common.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.dto.*;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.CommonMapper;
import ai.fassto.connectivity.dataaccess.common.CommonService;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import ai.fassto.connectivity.domain.item.core.exception.ItemNotFoundException;
import ai.fassto.connectivity.domain.item.core.exception.ItemNotUsedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final CommonMapper commonMapper;

    @Override
    public String getSlipNo(SlipNoDTO slipNoDTO) {
        return commonMapper.getSlipNo(slipNoDTO);
    }

    @Override
    public String getLocNo(LocNoDTO locNoDTO){
        return commonMapper.getLocNo(locNoDTO);
    }

    @Override
    public LocalDateTime getCurrentTime() {
        // DB 에서 시간 가져옴
        DateTimeDTO dateTime = commonMapper.getDbDateAndTime();

        String nowDateString = dateTime.getNowDate(); //20210426
        String nowTimeString = dateTime.getNowTime(); //091421

        LocalDate nowDate = LocalDate.parse(nowDateString, DateTimeFormatter.BASIC_ISO_DATE);
        LocalTime nowTime = LocalTime.parse(nowTimeString, DateTimeFormatter.ofPattern("Hmmss"));

        // 현재 시간
        LocalDateTime currentTime = LocalDateTime.of(nowDate, nowTime);
        return currentTime;
    }

    @Override
    public String getWorkdayCalc(WorkDayCalcDTO workDayCalcDTO) {
        return commonMapper.getWorkdayCalc(workDayCalcDTO);
    }

    @Override
    public String getWhName(String whCd) {
        return commonMapper.getWhName(whCd);
    }

    @Override
    public InQtyInfoDTO getInQtyInfo(String inOrdSlipNo) {
        return commonMapper.getInQtyInfo(inOrdSlipNo);
    }

}
