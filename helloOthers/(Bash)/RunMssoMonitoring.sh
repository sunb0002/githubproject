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

Msg=`python /home/hahaha/ESSOCronJob/RunTestGetRestAPI.py -u http://localhost:4321/msso/json -k ret_msg -v madoka -t 60`
ECode=`echo $?`
echo ExitCode: $ECode ---------- Message: $Msg

# Response took too long
if [[ "101" == "$ECode" ]]
then
   SMSAlert "$Msg"
fi

# Test failed, restaring service
if [[ "233" == "$ECode" ]]
then
   echo "Restaring MSSO service!"
   SMSAlert "Restaring MSSO service (CG71)"
   cd /home/hahaha/msso/
   sh stop_server.sh; sh start_server.sh;
fi

