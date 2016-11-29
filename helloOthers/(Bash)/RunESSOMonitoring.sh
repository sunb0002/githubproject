#!/bin/sh

SMSAlert() {
  recipients="12345300"
  msg="$1"
  for i in `echo $recipients | tr "," "\n"`
    do
      echo sending SMS to $i with msg $msg
      lwp-request "http://localhost:1234/sso/sendSMS.jsp?siteid=esso&mobile=$i&msg=$msg";
      done
}

Msg=`python /home/hahaha/ESSOCronJob/RunTestGetRestAPI.py -u http://localhost:4321/sso/testdb/testdb.jsp -v "time taken"`
ECode=`echo $?`
echo ExitCode: $ECode ---------- Message: $Msg

# Response took too long
if [[ "101" == "$ECode" ]]
then
   SMSAlert "ESSO service (CG71) monitoring alert: $Msg"
fi

# Test failed, restaring service
if [[ "233" == "$ECode" ]]
then
   echo "Restaring ESSO service!"
   SMSAlert "Restaring ESSO service (CG71)"
   sh /virtual/hahaha/restart_esso_server.sh
fi

