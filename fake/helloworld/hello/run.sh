#!/bin/sh

DIRNAME=`dirname $0`
RUNHOME=`cd $DIRNAME/;pwd`

if [ -f "$RUNHOME/setenv.sh" ];then
    . "$RUNHOME/setenv.sh"
else
    echo "can not found $RUNHOME/setenv.sh"
fi


echo start $APP_INFO ...

JAVA="$JAVA_HOME/bin/java"
JAVA_OPTS="-Xms50m -Xmx128m"
JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"
port=8777
#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$port,server=y,suspend=n"
CLASS_PATH="$RUNHOME/:$RUNHOME/$Main_JAR"

### declare smartsight agent begin ###

AGENT_PATH="/home/smartsight-agent"
AGENT_ID="smartsight_helloworld"
APPLICATION_NAME="smartsight_helloworld"
JAVA_OPTS="$JAVA_OPTS -javaagent:$AGENT_PATH/smartsight-bootstrap.jar"
JAVA_OPTS="$JAVA_OPTS -Dsmartsight.agentId=$AGENT_ID"
JAVA_OPTS="$JAVA_OPTS -Dsmartsight.applicationName=$APPLICATION_NAME"
JAVA_OPTS="$JAVA_OPTS -DsystemType=paas"

### declare smartsight agent end ###

echo ===============RUN INFO====================
echo @RUNHOME@ $RUNHOME
echo @Main_Class@ $Main_Class
echo @APP_INFO@ $APP_INFO
echo @Main_JAR@ $Main_JAR
echo @JAVA_HOME@ $JAVA_HOME
echo @JAVA@ $JAVA
echo @JAVA_OPTS@ $JAVA_OPTS
echo @CLASS_PATH@ $CLASS_PATH
echo ===========================================
#sleep 15s
"$JAVA" $JAVA_OPTS -classpath "$CLASS_PATH" -jar $RUNHOME/$Main_JAR
