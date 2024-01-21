cd ..
cd ..
SET CLASSPATH=./*;./lib/*;./conf;./lang
SET PATH=%PATH%;C:\Program Files\Java\jdk1.8.0_131\bin
java -cp %CLASSPATH% com.digiwin.simplified.app.test.DWSimplifiedModeMyApp

REM java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=127.0.0.1:18085 sampleapp1_backend-5.0.1.2000.jar
REM java -jar sampleapp1_backend-5.0.1.2000.jar