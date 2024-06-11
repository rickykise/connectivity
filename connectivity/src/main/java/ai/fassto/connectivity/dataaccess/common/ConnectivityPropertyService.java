package ai.fassto.connectivity.dataaccess.common;

import ai.fassto.connectivity.domain.common.exception.PropertyNotFoundException;
import ai.fassto.connectivity.dataaccess.common.dto.ConnectivityPropertyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyComponent.getCacheKey;
import static ai.fassto.connectivity.dataaccess.common.valueobject.PropMainType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectivityPropertyService {
    private static final String DEFAULT = "DEFAULT";
    private static final String REQ_PARAM_MAX_SIZE = "REQ_PARAM_MAX_SIZE";
    private static final String RES_PARAM_MAX_SIZE = "RES_PARAM_MAX_SIZE";
    private static final Integer DEFAULT_REQ_PARAM_MAX_SIZE = 1024;
    private static final Integer DEFAULT_RES_PARAM_MAX_SIZE = 1024;
    private final ConnectivityPropertyComponent property;

    public String getSolochainHostUrl(String warehouseCode) {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                SOLOCHAIN_HOST.name(), warehouseCode, getCacheKey(SOLOCHAIN_HOST.name(), warehouseCode)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(SOLOCHAIN_HOST.name(), warehouseCode);
        }
        return connectivityPropertyOrNull.getValue();
    }

    public String getApiKey(String warehouseCode) {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                API_KEY.name(), warehouseCode, getCacheKey(API_KEY.name(), warehouseCode)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(SOLOCHAIN_HOST.name(), warehouseCode);
        }
        return connectivityPropertyOrNull.getValue();
    }

    public String getErpHostUrl() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                ERP_HOST.name(), DEFAULT, getCacheKey(ERP_HOST.name(), DEFAULT)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(ERP_HOST.name(), DEFAULT);
        }
        return connectivityPropertyOrNull.getValue();
    }

    public String getTmsHostUrl() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                TMS_HOST.name(), DEFAULT, getCacheKey(TMS_HOST.name(), DEFAULT)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(TMS_HOST.name(), DEFAULT);
        }
        return connectivityPropertyOrNull.getValue();
    }

    public String getYlpHostUrl() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                YLP_HOST.name(), DEFAULT, getCacheKey(YLP_HOST.name(), DEFAULT)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(YLP_HOST.name(), DEFAULT);
        }
        return connectivityPropertyOrNull.getValue();
    }

    public String getOmsSmartStoreHostUrl() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                OMS_SMART_STORE_HOST.name(), DEFAULT, getCacheKey(OMS_SMART_STORE_HOST.name(), DEFAULT)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(OMS_SMART_STORE_HOST.name(), DEFAULT);
        }
        return connectivityPropertyOrNull.getValue();
    }

    public String getPartnerApiHostUrl() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                PARTNERAPI_HOST.name(), DEFAULT, getCacheKey(PARTNERAPI_HOST.name(), DEFAULT)
        );
        if (connectivityPropertyOrNull == null) {
            throw new PropertyNotFoundException(PARTNERAPI_HOST.name(), DEFAULT);
        }
        return connectivityPropertyOrNull.getValue();
    }



    public Integer getRequestParamMaxSize() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                SIZE.name(), REQ_PARAM_MAX_SIZE, getCacheKey(SIZE.name(), REQ_PARAM_MAX_SIZE)
        );
        if (connectivityPropertyOrNull == null) {
            log.error("REQ_PARAM_MAX_SIZE is not found");
            return DEFAULT_REQ_PARAM_MAX_SIZE;
        }
        return Integer.valueOf(connectivityPropertyOrNull.getValue());
    }
    public Integer getResponseParamMaxSize() {
        ConnectivityPropertyDTO connectivityPropertyOrNull = property.getConnectivityPropertyOrNull(
                SIZE.name(), RES_PARAM_MAX_SIZE, getCacheKey(SIZE.name(), RES_PARAM_MAX_SIZE)
        );
        if (connectivityPropertyOrNull == null) {
            log.error("RES_PARAM_MAX_SIZE is not found");
            return DEFAULT_RES_PARAM_MAX_SIZE;
        }
        return Integer.valueOf(connectivityPropertyOrNull.getValue());
    }

    public void refreshAllLocalCaches() {
        property.refreshCacheForConnectivityPropertyList();
        property.refreshCacheForConnectivityProperty();
    }

    public void refreshCacheAtConnectivityProperty(String cacheKey) {
        property.refreshCacheAtConnectivityProperty(cacheKey);
    }
}
