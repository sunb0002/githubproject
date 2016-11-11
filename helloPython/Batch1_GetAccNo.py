#!/usr/bin/env python
# Author: Sun Bo
# Date: 2016-Nov-10

import re
import sys
import requests
import time
import requests.packages.urllib3

#########Constants###########
csvFile = "SR16J2480Z_To_validate_then_fire_84218.csv"
csvFileOK = csvFile + ".result"
csvFileErr = csvFile + ".error"
sleepSec = 0.1
testEndPoint = "https://hahahaha"
#########Function############


def getAcc(nric):
    try:
        requests.packages.urllib3.disable_warnings()
        r = requests.get(
            testEndPoint, params={"DOCNO": nric}, timeout=60, verify=False)
    except requests.exceptions.RequestException:
        return None

    body = str(r.content)
    if not (body):
        return None

    accno = None
    if body.index('RETURN_CODE=1') > -1:
        accno = re.search('ACCT_NO_1=(\d+.\d+)', body).group(1)

    return accno


#########Main################
if __name__ == "__main__":
    if len(sys.argv) > 1:
        maxLine = int(sys.argv[1])
    else:
        maxLine = -1

    errList = list()
    okList = list()

    with open(csvFile, "r") as f:
        for idx, line in enumerate(f):
            if idx + 1 > maxLine:
                break

            cols = line.split(",")
            STATUS = cols[1]
            HUBID = cols[2]
            SSOID = cols[3]
            if not (HUBID) or not (SSOID) or not (STATUS) or not ('active' == STATUS.lower()):
                print "INVALID INPUT: %s" % (line.strip())
                errList.append(line)
                continue

            NRIC = cols[0]
            DOCTYPE = "NRIC" if NRIC.startswith('S') else "FIN"
            ACCNO = getAcc(NRIC)

            if (ACCNO):
                s = "%s,%s,%s,%s,%s" % (DOCTYPE, NRIC, HUBID, ACCNO, SSOID)
                print "SUCCESS: %s" % (s)
                okList.append(s)
            else:
                print "NOT FOUND IN SIEBEL: %s" % (line.strip())
                errList.append(line)

            time.sleep(sleepSec)

    # Shame that have to use Python 2.6 way
    with open(csvFileOK, "w") as f2:
        for okline in okList:
            f2.write(str(okline) + '\n')

    with open(csvFileErr, "w") as f3:
        for errline in errList:
            f3.write(str(errline))
