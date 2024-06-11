package ai.fassto.connectivity.externalservice.wms.solochain.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
//        if (template.body() == null) {
//            return;
//        }
//
//        String oldMessage = StringUtils.toEncodedString(template.body(), UTF_8);
//        String newMessage = oldMessage.replaceAll("@M@M@B", "MaterialMaster");
//
//        template.body(newMessage);
    }
}
