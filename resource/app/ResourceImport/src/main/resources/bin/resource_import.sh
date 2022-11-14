#!/usr/bin/env bash

# JVMFLAGS JVM参数可以在这里设置
JVMFLAGS=-Dfile.encoding=UTF-8

RES_IMPORT_HOME="${BASH_SOURCE-$0}"
RES_IMPORT_HOME="$(dirname "${RES_IMPORT_HOME}")"
RES_IMPORT_HOME="$(cd "${RES_IMPORT_HOME}/../"; pwd)"

if [ "$JAVA_HOME" != "" ]; then
  JAVA="$JAVA_HOME/bin/java"
  JAR="$JAVA_HOME/bin/jar"
else
  JAVA=java
  JAR=jar
fi

#echo $RES_IMPORT_HOME

mkdir -p $RES_IMPORT_HOME/logs
mkdir -p $RES_IMPORT_HOME/pid

cd "$RES_IMPORT_HOME"/conf/

cd "$RES_IMPORT_HOME"/bin/

# 转化为绝对路径
CONF_HOME=$RES_IMPORT_HOME/conf
LIB_DIR=$RES_IMPORT_HOME/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

_RI_DAEMON_OUT="$RES_IMPORT_HOME/logs/ri.out"
RI_MAIN="platform.resource.main.DisplayResource"

RI_PID_FILE="$RES_IMPORT_HOME/pid/ri.pid"

case $1 in
start)
    echo "Starting DCT RES_IMPORT ... "
    if [ -f "$RI_PID_FILE" ]; then
      if kill -0 `cat "$RI_PID_FILE"` > /dev/null 2>&1; 
      then
        echo $command already running as process `cat "$RI_PID_FILE"`. 
      	exit 0
      fi
    fi

    "$JAVA" -classpath $CONF_HOME:$LIB_JARS $JVMFLAGS $RI_MAIN "$CONF_HOME"/ > $_RI_DAEMON_OUT 2>&1 &

	if [ $? -eq 0 ]
    then
      if /bin/echo -n $! > "$RI_PID_FILE"
      then
        sleep 1
        echo "STARTED"
      else
        echo "FAILED TO WRITE PID"
        exit 1
      fi
    else
      echo "TASK_TRACKER DID NOT START"
      exit 1
    fi
	
;;
restart)
    sh $0 stop
    sleep 3
    sh $0 start
;;
stop)
    echo "Stopping DCT TASK_TRACKER ... "
    if [ ! -f "$RI_PID_FILE" ]
    then
      echo "no tasktracker to stop (could not find file $RI_PID_FILE)"
    else
      kill -9 $(cat "$RI_PID_FILE")
      rm "$RI_PID_FILE"
      echo "STOPPED"
    fi
    exit 0
;;
*)
    echo "Usage: $0 {start|stop|restart}" >&2
esac





