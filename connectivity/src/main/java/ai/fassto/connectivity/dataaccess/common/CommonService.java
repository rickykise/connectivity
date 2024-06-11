package ai.fassto.connectivity.dataaccess.common;

import ai.fassto.connectivity.dataaccess.common.dto.InQtyInfoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.LocNoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.SlipNoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.WorkDayCalcDTO;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CommonService {
    String getSlipNo(SlipNoDTO slipNoDTO);
    String getLocNo(LocNoDTO locNoDTO);
    LocalDateTime getCurrentTime();
    String getWorkdayCalc(WorkDayCalcDTO workDayCalcDTO);
    String getWhName(String whCd);
    InQtyInfoDTO getInQtyInfo(String inOrdSlipNo);
}
