package com.digiwin.aqs_dwcc0130.utils;

import com.digiwin.app.common.DWApplicationConfigUtils;
import com.digiwin.app.container.exceptions.DWException;
import com.digiwin.app.service.DWServiceContext;
import com.digiwin.athena.esp.sdk.Invoker;
import com.digiwin.athena.esp.sdk.init.EspSdkInitialize;
import com.digiwin.athena.esp.sdk.model.RequestModel;
import com.digiwin.athena.esp.sdk.model.ResponseModel;

import java.util.Map;
import java.util.Objects;

/**
 * @Description:Esp服務調用工具
 * @ClassName:EspServiceUtil
 */
public class EspServiceUtil {
    private static void initEspConfig() {
        String domain_esp = DWApplicationConfigUtils.getProperty("domain.esp");
        String domain_mdc = DWApplicationConfigUtils.getProperty("domain.mdc");
        String domain_tm = DWApplicationConfigUtils.getProperty("domain.tm");
        String eocUrl = DWApplicationConfigUtils.getProperty("eocUrl");
        EspSdkInitialize.initConfig(domain_esp, domain_mdc, domain_tm, eocUrl);
        String appId = DWApplicationConfigUtils.getProperty("appId");
        String appToken = DWApplicationConfigUtils.getProperty("iamApToken");
        String lmcUrl = DWApplicationConfigUtils.getProperty("domain.lmc");
        EspSdkInitialize.initAppConfig(appId, appToken);
        EspSdkInitialize.initLmcConfig(lmcUrl);
        EspSdkInitialize.enableEspRouting(true);
    }

    /**
     *  呼叫同步调用ESP服务
     *
     * @param eocHeader 运营单元Map , 例如{"eoc_company_id":"1","eoc_site_id":"1"}
     * @param serviceName 服务名称
     * @param hostProd 默认Athena，也可指定产品别，比如：E10
     * @param hostId 注册在中台上的host id
     * @param para 请求Body
     * @return response body
     */
    public static String invokeRestSyncApi(Map eocHeader, String serviceName, String hostProd, String hostId, String para) throws Exception {
        initEspConfig();
        RequestModel requestModel = new RequestModel();
        buildCommonHeader(requestModel, serviceName, hostProd, hostId, eocHeader);
        requestModel.setBodyJsonString(para);
        ResponseModel responseModel = Invoker.invokeRestSync(requestModel);

        //check response
        // 稳敏API发生调用异常  => digi-code 非0开头
        if (Objects.isNull(responseModel.getEspCode()) || !responseModel.getEspCode().startsWith("0")) {
            throw new DWException("digi-code is not 0 error");
        }

        // 业务异常
        if (!"000".equals(responseModel.getSrvCode())) {
            throw new DWException("srvCode is not 000 error");
        }

        return responseModel.getBodyJsonString();
    }

    private static void buildCommonHeader(RequestModel requestModel, String serviceName, String hostProd, String hostId, Map eocHeader){

        DWServiceContext context = DWServiceContext.getContext();
        String tenantName = context.getProfile().get("tenantId").toString();
        String token = context.getToken();
        requestModel.setHostProd(hostProd);
        requestModel.setHostId(hostId);
        requestModel.setHostVer(DWApplicationConfigUtils.getProperty("eaiHostVer"));
        requestModel.setHostAcct("athena");
        requestModel.setLanguage("zh_CN");
        requestModel.setTenantId(tenantName);
        requestModel.setServiceName(serviceName);
        requestModel.setEocMap(eocHeader);
        requestModel.addHeader("token", token);
    }
}
