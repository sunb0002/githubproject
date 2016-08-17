#!/usr/bin/python
# Author: Sun Bo
# Date: 2016-Apr-21

import httplib
import re
import urllib
import time
import sys

portNo = int(sys.argv[1])  # Port number to be monitored

alertSeconds = 30
timeoutSeconds = 80
retryLimit = 3

DBSeconds = -10  # Used as flag of service restart
restartFlag = False
msg = 'OK'
error1 = 'eSSO testdb.jsp is not responding after %s retries, restarting eSSO tomcat.' % retryLimit
error2 = 'eSSO testdb.jsp indicates that DB takes more than %s seconds to reponse.' % alertSeconds


for i in range(0, retryLimit):
    try:
        conn = httplib.HTTPConnection('localhost', portNo)
        # conn = httplib.HTTPConnection('login.dev.starhubgee.com.sg', 80)
        conn.timeout = timeoutSeconds
        conn.set_debuglevel(0)
        conn.request("GET", "/sso/testdb/testdb.jsp")
        # print 'Retrying for %sth time.' % (i+1)

        resp = conn.getresponse()
        if (httplib.OK == resp.status):
            DBSeconds = re.search(
                'time taken=(.+?) millseconds', resp.read()).group(1)
            DBSeconds = int(DBSeconds)
            break
        else:
            # print resp.reason
            time.sleep(2)

        conn.close()
    except Exception as ex:
        # print ex
        time.sleep(2)
        continue


if (DBSeconds < 0):
    # No response or exception happened, to send alert and restart eSSO
    # service.
    restartFlag = True
    msg = error1
elif (DBSeconds > alertSeconds*1000):
    # DB slowness, to send alert only.
    msg = error2


# Final output, dont change this part
print '%s' % urllib.quote(msg)
if (restartFlag):
    sys.exit(103)  # Madoka's birthday
