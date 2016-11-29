#!/bin/sh
HOME=/virtual/hahaha
cd $HOME/sso/appserver/bin
./shutdown.sh
sleep 5
GREPPID=`ps aux | grep "sso\/appserver" | grep tomcat | xargs | cut -f2 -d' '`
echo $GREPPID
if ((GREPPID > 0)); then
kill -9 $GREPPID;
fi

