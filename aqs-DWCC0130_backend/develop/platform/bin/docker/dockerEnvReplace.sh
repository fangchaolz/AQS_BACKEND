#!/bin/sh
# dockerEnv.sh 參數
## 1. 掃描參數的資料夾
## 2. 掃描的檔案類型
## 3. 要排除掃描的檔案名稱

jarLocation=./platform/bin/docker/dap-config-value-replacer.jar
java -jar $jarLocation ./platform/bin sh dockerEnvReplace.sh dockerEnv.sh dockerRun.sh
java -jar $jarLocation /jmx properties
java -jar $jarLocation ./conf xml
java -jar $jarLocation ./conf properties
java -jar $jarLocation ./lang properties