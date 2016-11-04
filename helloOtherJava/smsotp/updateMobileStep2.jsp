<tbody id="OTPValidateDiv">
<tr>
    <td class="TMSLabel">
        <div style="height:10px;">&nbsp;</div>
    </td>
</tr>
<tr>
    <td class="TMSLabel">
        <div style="height:10px;">&nbsp;</div>
    </td>
</tr>
<tr>
    <td COLSPAN="2" align="center">
        <p class="lblChangePwdErrorDiv" id="changePwdErrorDiv"><%=errorMessage%>
        </p>
    </td>
</tr>
<tr>
    <td class="TMSLabel">
        <div style="height:10px;">&nbsp;</div>
    </td>
</tr>
<tr>
    <td width="50%">
        <table class="TMSLoginTable" align="center">
            <tr>
                <td class="TMSLabel">
                    <div style="height:10px;">&nbsp;</div>
                </td>
            </tr>
            <tr>
                <td class="TMSLabel" colspan="2" align="left">
                    1. A SMS from SGX will be sent to the below below mobile.
                </td>
            </tr>
            <tr>
                <td class="TMSLabel" colspan="2" align="left">
                    2. Key in the number you received from your SMS.
                </td>
            </tr>
            <tr>
                <td class="TMSLabel">
                    <div style="height:10px;">&nbsp;</div>
                </td>
            </tr>
            <tr>
                <td class="TMSLabel">
                    <div class="logLblTMS"><label>New Mobile No.</label></div>
                </td>
                <td class="TMSInput" style="vertical-align: bottom;">
                    <%=ESAPI.encoder().encodeForHTML(newMobile)%>
                </td>
            </tr>
            <tr>
                <td class="TMSLabel">
                    <div style="height:10px;">&nbsp;</div>
                </td>
            </tr>
            <tr>
                <td class="TMSLabel">
                    <div class="logLblTMS"><label>OTP</label></div>
                </td>
                <td class="TMSInput">
                    <input id="OTPinput" name="OTPinput" size="15" type="password"
                           autocomplete="off" maxlength="8"/>
                </td>
            </tr>
            <tr>
                <td class="TMSLabel">
                    <div style="height:10px;">&nbsp;</div>
                </td>
            </tr>
            <tr>
                <td align="left" width="5%"><span class="sidetext">&nbsp;</span></td>
                <td align="right" width="40%">
                    <input id="pin" name="pin" type="hidden"/>
                    <input id="newcc" name="newcc" type="hidden"/>
                    <input id="newac" name="newac" type="hidden"/>

                    <input id="OTPmode_sent" name="OTPmode_sent" type="hidden"/>
                    <input id="TAN_pass" name="TAN_pass" type="hidden"/>
                    <input id="goto" name="goto" type="hidden"
                           value="<c:out value="${requestScope.goto}" default="/" />"/>
                </td>
            </tr>
            <tr>
                <td class="TMSLabel">
                    <div style="height:10px;">&nbsp;</div>
                </td>
            </tr>
            <tr>
                <td class="left-col-button">
                    <button id="ExecutionButton" type="button" class="regBtn"
                            onclick="javascript:submitValidateOTP()">Submit
                    </button>
                </td>

                <td class="right-col-button">
                    <button type="button" class="regBtn"
                            onclick="javascript:location.href = '<c:out value="${requestScope.goto}" default="/"/>'">
                        Cancel
                    </button>
                </td>
            </tr>

            <tr>
                <td class="TMSLabel">
                    <div style="height:10px;">&nbsp;</div>
                </td>
            </tr>
            <tr>
                <td class="left-col-button">
                    <button type="button" class="regBtn" onclick="javascript:submitValidateOTP('resendOTP')">Resend
                        SMS
                    </button>
                </td>
                <td class="right-col-button">
                    &nbsp;
                </td>
            </tr>
        </table>
    </td>
    <td width="50%" style="text-align: center; vertical-align:middle;">
        <table align="center">
            <tr>
                <td colspan="5">Sample SMS Message</td>
            </tr>
            <tr>
                <td colspan="5" class="phone-body"><!-- top of phone --></td>
            </tr>
            <tr><!-- main phone + screen -->
                <td class="phone-body-side"><!-- side of phone --></td>
                <td colspan="3" class="smsotp-message">
                    <b>From: SGX</b><br/>
                    SGX: Your One-Time Password is <span class="highlight">12345678</span>. valid for 100 seconds.
                </td>
                <td class="phone-body-side"><!-- side of phone --></td>
            </tr>
            <tr>
                <td colspan="5" class="phone-body"><!-- bottom of phone --></td>
            </tr>
        </table>
    </td>
</tr>
</tbody>
