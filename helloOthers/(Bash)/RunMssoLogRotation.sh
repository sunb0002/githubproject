#!/bin/bash

# (TODO) Use Linux logrotate

# We delete logs older than 92 days (3 months), and zip those older than 2 days.
logs_tomcat_home=/home/hahaha/msso/mssoappserver/logs/
if [ -d $logs_tomcat_home ]
then
        cd $logs_tomcat_home
        rm -f `find . -mtime +92`
        find . -mtime +2 ! -name "*.out" -exec gzip -q {} \;
fi

