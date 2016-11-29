#!/bin/sh

echo -n "madoka says, Enter your msso SessionID: "
read SID

./GeneralRun com.ufinity.msso.cron.TestPrintSession "$SID"

