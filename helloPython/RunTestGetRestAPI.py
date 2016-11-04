#!/usr/bin/env python
# Author: Sun Bo
# Date: 2016-Aug-17

import datetime as dt
import getopt
import sys
import requests

##########Configs##########
short_args = 'h:u:k:v:a:t:r'
long_args = ['URL=', 'KEY=', 'VALUE=', 'ALERT=', 'TIMEOUT=', 'RETRY=']

# Default
alertSeconds = 30
timeoutSeconds = 80
retryLimit = 3

#########Constants###########
codeGeneralErrorClient = 201
codeGeneralErrorInternal = 202
codeTestFail = 233
codeTestOKButTooLong = 101
codeTestOKButRetry = 102
##########Functions##########


def toInt(s):
    try:
        return int(s)
    except Exception:
        return None


def usage():
    print "~~~How to use this script~~~"
    print 'Usage1: '+sys.argv[0]+' -u <endpoint_url> -v <value_to_test>'
    print 'Usage2: '+sys.argv[0]+' -u <endpoint_url> -k <key_to_test> -v <value_to_test> -a <alert_seconds> -t <timeout_seconds> -r <retry_limit>'
    print 'Usage3: '+sys.argv[0]+' --URL <endpoint_url> --KEY <key_to_test> --VALUE <value_to_test> -ALERT <alert_seconds> -TIMEOUT <timeout_seconds> -RETRY <retry_limit>'
    print "<endpoint_url> is mandatory. <alert_seconds> default 30. <timeout_seconds> default 80. <retry_limit> default 3."
    return


def make_return(msg, showUsage, exitcode):
    if (msg):
        print 'Message=' + str(msg)

    if (showUsage == True):
        usage()

    code = toInt(exitcode)
    sys.exit(code)
    return

###########################################
if __name__ == "__main__":

    testEndPoint = None
    testKey = None
    testValue = None

    try:
        opts, args = getopt.getopt(sys.argv[1:], short_args, long_args)

        if not opts:
            make_return('No options supplied', True, codeGeneralErrorClient)

        for optk, optv in opts:
            opti = toInt(optv)
            if optk in ('-h', '--help'):
                make_return(None, True, None)
            if optk in ('-u', '--URL'):
                testEndPoint = optv
                continue
            if optk in ('-k', '--KEY'):
                testKey = optv
                continue
            if optk in ('-v', '--VALUE'):
                testValue = optv
                continue
            if optk in ('-a', '--ALERT') and opti > 0:
                alertSeconds = opti
                continue
            if optk in ('-t', '--TIMEOUT') and opti > 0:
                timeoutSeconds = opti
                continue
            if optk in ('-r', '--RETRY') and opti > 0:
                retryLimit = opti
                continue

        if (not testEndPoint):
            make_return(
                'The endpoint_URL is mandatory.', False, codeGeneralErrorClient)
    except getopt.GetoptError as err:
        make_return(err, True, codeGeneralErrorClient)

    # print 'Input parameters: testEndPoint=' + testEndPoint + ' testKey=' +
    # str(testKey) + ' testValue='+ str(testValue)

    # Negative value means HTTP call failed
    deltaSeconds = 0
    httpContentTestOKFlag = False
    httpTooLongFlag = False
    httpRetriedFlag = False

    for i in range(0, retryLimit):
        # print i
        try:
            timeStart = dt.datetime.now()
            r = requests.get(testEndPoint, timeout=timeoutSeconds)
            timeEnd = dt.datetime.now()

            if (200 <= r.status_code < 300):
                deltaSeconds = (timeEnd-timeStart).seconds
                httpTooLongFlag = (deltaSeconds > alertSeconds)
                break
            else:
                httpRetriedFlag = True
        except requests.exceptions.RequestException as reqex:
            # print reqex
            pass
        except Exception as internalex:
            make_return(internalex, True, codeGeneralErrorInternal)
    else:
        # No break message, fail the test.
        make_return("HTTP request failed after " +
                    str(retryLimit) + " retries", False, codeTestFail)

    # If testKey is provided, test with json.
    # Otherwise test with response body subString.
    if testKey:
        rObj = r.json()
        if testKey in rObj:
            rValue = rObj[testKey]
            # OK only if the retrieved value exactly matches testValue
            httpContentTestOKFlag = (rValue == testValue)
    else:
        httpContentTestOKFlag = True if (
            not testValue) else (testValue in str(r.content))

    # print 'Test result: httpContentTestOKFlag=%s, httpTooLongFlag=%s,
    # httpRetriedFlag=%s' % (httpContentTestOKFlag, httpTooLongFlag,
    # httpRetriedFlag)

    if (not httpContentTestOKFlag):
        make_return(
            "No matching testValue for input testKey", False, codeTestFail)

    if (httpTooLongFlag == True):
        make_return("OK but took longer than " +
                    str(alertSeconds)+" seconds", False, codeTestOKButTooLong)
    elif (httpRetriedFlag == True):
        make_return(
            "OK but took " + str(i+1)+" retries", False, codeTestOKButRetry)
    else:
        make_return("OK", False, None)
