# syntax=docker/dockerfile:experimental
## 使用 DOCKER_BUILDKIT=1 docker build -t 'imageName:tag' . 的方式進行打包
# 下載平台 apigateway（可討論是否由後端人員交付一個版本包含平台與maven的 base image）

#平台抓運行包方式有兩種方式，一種是透過module/pom.xml，另一種是從nexus
#應用可以自已選擇一種

#平台抓運行包方式1(module/pom.xml) 開始
#平台提供以下抓運行包的方式是從module/pom.xml方式，可以減少升級平台時要修改Dockerfile的版號
#前提是module/pom.xml要增加以下配置，平台5.0.1.1000版本，提供的pom.xml已增加以下配置
#(1-1)<dependencies>...</dependencies>增加運行包的依賴
#<dependency>
#	<groupId>com.digiwin</groupId>
#	<artifactId>dwapiplatform-appbackend</artifactId>
#	<version>${api.version}</version>
#	<type>war</type>			
#</dependency>
#(1-2)<build><plugins>...</plugins></build>增加運行包在package時輸出至target/dependency資料夾
#<plugin>
#	<groupId>org.apache.maven.plugins</groupId>
#	<artifactId>maven-dependency-plugin</artifactId>
#	<version>2.4</version>
#	<executions>
#		<execution>
#			<id>copy-dependencies</id>
#			<phase>package</phase>
#			<goals>
#				<goal>copy-dependencies</goal>
#			</goals>
#			<configuration>
#				<includeGroupIds>com.digiwin</includeGroupIds>
#				#<includeArtifactIds>dwapiplatform-appbackend</includeArtifactIds>							
#			</configuration>
#		</execution>					
#	</executions>
#</plugin>
ARG	    platformPath=/platform
ARG	    backendName=sampleapp1_backend
ARG	    packpath=/package

FROM          maven:3.5.3-jdk-8 AS base
ARG	          backendName
ARG	          platformPath
ENV           moduleRootPath=develop/module
ENV           moduleDependencyPath=${platformPath}/${moduleRootPath}/target/dependency
WORKDIR       ${platformPath}
COPY          ${backendName}/${moduleRootPath} ${moduleRootPath}
RUN           --mount=type=cache,target=/root/.m2 mvn -f ${moduleRootPath}/pom.xml clean package
RUN           tar -xvf ${moduleDependencyPath}/dwapiplatform-appbackend-*.war
RUN           mv app_backend ${backendName}
#平台抓運行包方式1(module/pom.xml) 結束

#平台抓運行包方式2(從nexus) 開始
ARG	    apiVersion=4.1.0.1008
ARG	    platformPath=/platform
ARG	    backendName=sampleapp1_backend
ARG	    packpath=/package
FROM    alpine AS base
# 由此設定相關參數
ARG        apiVersion
ARG        platformPath
ARG        backendName
ENV      nexusServer=repo.digiwincloud.com.cn    
WORKDIR       ${platformPath}
RUN           apk add wget --no-cache
RUN         wget     -nv    'https://'${nexusServer}'/nexus/service/rest/v1/search/assets/download?sort=version&repository=snapshots&group=com.digiwin&name=dwapiplatform-appbackend&maven.extension=war&maven.baseVersion='${apiVersion}  -O ${backendName}.war    \   
            && tar xvf ${backendName}.war \    
            && mv app_backend ${backendName}
#平台抓運行包方式2(從nexus) 結束
			
# 編譯
FROM        maven:3.5.3-jdk-8 AS builder
ARG	    apiVersion
ARG	    packpath
ARG	    backendName
ENV         build=/build modulePath=develop/module thirdParty=develop/DWThirdPartyLibrary
RUN         mkdir -p ${build}/thirdParty && mkdir -p ${packpath}/lib && mkdir -p ${packpath}/module
RUN	    mkdir -p ${packpath}/conf && mkdir -p ${packpath}/lang  && mkdir -p ${packpath}/lib
WORKDIR     ${build}
# config
COPY        ${backendName}/develop/conf/* ${packpath}/conf/
COPY        ${backendName}/develop/lang/* ${packpath}/lang/
COPY        ${backendName}/develop/lib/* ${packpath}/lib/
# thirdparty
# 目前sampleApp1沒有用到third party打包 先註解
#COPY        ${backendName}/${thirdParty} ${thirdParty}
#RUN         --mount=type=cache,target=/root/.m2 mvn -f ${thirdParty}/pom.xml package
#RUN         cp -r ${thirdParty}/target/dependency/* ${packpath}/lib

#服務模組打包方式有兩種，一種是透過module/pom.xml，另一種是服務模組下的pom.xml

#服務模組打包方式1(module/pom.xml) 開始
#在3.1.0以上版本，平台增加develop/module增加pom.xml
#服務模組的pom.xml調整pom.xml參考父層的pom.xml，可以透過以下指令這行一次打包全部模組
#modules package jar
COPY        ${backendName}/${modulePath}  ${modulePath}
RUN         --mount=type=cache,target=/root/.m2 mvn -f ${modulePath}/pom.xml package

# module1 copy
ENV         moduleName=Basic
RUN         cp -r ${modulePath}/${moduleName}/target/* ${packpath}/module
# module2 copy
ENV         moduleName=Basic2
RUN         cp -r ${modulePath}/${moduleName}/target/* ${packpath}/module
#服務模組打包方式1(module/pom.xml) 結束

#服務模組打包方式2(服務模組下的pom.xml) 開始
# 如果服務模組的pom.xml沒有調整參考父層pom.xml，那仍維持原本打包方式如下
# module1 package jar and copy
#ENV         moduleName=basic1
#COPY        ${backendName}/${modulePath}/${moduleName}  ${moduleName}
#RUN         --mount=type=cache,target=/root/.m2 mvn -f ${moduleName}/pom.xml package
#RUN         cp -r ${moduleName}/target/* ${packpath}/module
# module2 package jar and copy
#ENV         moduleName=basic2
#COPY        ${backendName}/${modulePath}/${moduleName}  ${moduleName}
#RUN         --mount=type=cache,target=/root/.m2 mvn -f ${moduleName}/pom.xml package
#RUN         cp -r ${moduleName}/target/* ${packpath}/module
#服務模組打包方式2(服務模組下的pom.xml) 結束

# 打包
FROM        registry.digiwincloud.com/dwsidecar/dwsidecar-1.0.0.0:1.0.0.24
ARG	    platformPath
ARG	    backendName
ARG	    packpath
COPY        --from=base ${platformPath}/${backendName} /${backendName}
WORKDIR     /${backendName}
COPY        --from=builder ${packpath}/ ./application/

RUN         chmod +x ./platform/bin/run.sh \
            && chmod +x ./platform/bin/stop.sh \
            && chmod +x ./platform/bin/docker/dockerEnv.sh \
            && chmod +x ./platform/bin/docker/dockerEnvReplace.sh \
            && chmod +x ./platform/bin/docker/dockerRun.sh
EXPOSE      22620
ENTRYPOINT  ["/sampleapp1_backend/platform/bin/docker/dockerRun.sh"]
