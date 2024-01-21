[開發工具-簡易式模版-配置資源 有下列文件]
----------------------------------------------------
A.應用層
    1.application.properties
    2.spring-application.xml
    3.service-retry-config-application.json
B.平台層
    1.platform.properties
    2.log4j2.xml
    3.service-retry-config-platform.json
====================================================


[1.上述文件在平台生成時機點]

1.1.打包開發包時(Backend_common_package_dev_releases), 會從模組化運行包中複製
----------------------------------------------------
    A.1. 從運行包路徑下 application\conf 複製
    A.2. 從運行包路徑下 application\conf 複製
    B.1. 從運行包路徑下 platform\conf 複製
    B.2. 從運行包路徑下 platform\conf 複製
    B.3. 從運行包路徑下 platform\conf 複製
        service-retry-config.json 更名為
        service-retry-config-platform.json
====================================================

1.2.開發包中已經預置所需的文件
----------------------------------------------------
    A.3. DWAPI\app_backend_dev\tool\simplified-dev-template\develop\conf\service-retry-config-application.json
====================================================