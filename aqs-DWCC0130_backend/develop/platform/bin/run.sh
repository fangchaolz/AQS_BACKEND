#!/bin/sh
cd ..
cd ..
#Backend/
WORKDIR=`pwd`
export WORKDIR

CLASSPATH=.:$WORKDIR:$WORKDIR/lib/*:$WORKDIR/conf:$WORKDIR/lang
export CLASSPATH

MAINCLASS=@mainClass@
export MAINCLASS

sh /dwsidecar/sidecar/bin/docker/dockerRun.sh &

if test -z "$pinpoint_appname";
then
  java -cp $CLASSPATH @jvmArgs@ $MAINCLASS
else
  export AGENT_PATH=/agent_pinpoint/pinpoint-bootstrap.jar
  export AGENT_NAME=$pinpoint_appname
  export AGENT_ID=$pinpoint_id
  export AGENT_OPTS="-javaagent:$AGENT_PATH -Dpinpoint.agentId=$AGENT_ID -Dpinpoint.applicationName=$AGENT_NAME"
  java $AGENT_OPTS -cp $CLASSPATH @jvmArgs@ $MAINCLASS
fi
