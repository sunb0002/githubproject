<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="com.ufinity.sgx.tms.log.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link href="../resources/style.css" rel="stylesheet" type="text/css"/>
    <script src="../js/jquery-1.8.2.min.js"></script>
    <style type="text/css">
        .lblChangePwdErrorDiv {
            font-family: arial, helvetica;
            font-weight: bold;
            font-size: 11pt;
            color: #771111;
            padding: 5px;
            text-align: center;
        }
    </style>
    <title>SGX 2FA: Change Mobile No. for SMS OTP</title>
    <%
        LogUtil.tmsdebug("\nUpdateMobile.jsp==");
        //DSSS AS only stores BIG letter user name, and case sensitive. 

        String userID = (String) request.getAttribute("userID");


        String OTPmode_sent = (String) request.getAttribute("OTPmode_sent");
        String TAN_pass = (String) request.getAttribute("TAN_pass");
        String errorMessage = (String) request.getAttribute("message");

        LogUtil.tmsdebug("TS.51 OTPmode_sent: " + OTPmode_sent + " -TAN_pass: " + TAN_pass);

        //Input parameters
        String newMobile = request.getParameter("pin"); // New mobile no.
        String OTPinput = request.getParameter("OTPinput"); // SMS OTP validation 
        String newCC = request.getParameter("newcc"); //New Mobile country code
        String newAC = request.getParameter("newac"); //New Mobile country code
        String oldmobile = (String) request.getAttribute("oldmobile");


        LogUtil.tmsdebug("TS.62 userID: " + userID + " -oldmobile: " + oldmobile);


        //if new number is not valid or mobile number is updated successfully
        if (("New mobile number must be valid and different from current mobile number").equalsIgnoreCase(errorMessage) ||
                ("Mobile number updated successfully!").equalsIgnoreCase(errorMessage)) {
            newMobile = "";
            newAC = "";
        }

        // Fix part 1 of 2 for M05 - CFS
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        // done 1 of 2 for M05

        //Fix for G8 - Sensitive Information Cached on disk
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        // done for G8
        // to pass Prateek
        response.setHeader("X-UA-Compatible", "IE=edge");
    %>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>


</head>
<body>
<div id="wrapper">
    <%@ include file="../header-body.jsp" %>
    <div id="header-seperate">
        <h4>Change Mobile Number for ${requestScope.userID}</h4>
    </div>
    <div class="twoColumWrapper clearfix">
        <div style="margin: 0px auto; text-align: center;" class="sidebarBoxTMS nomargin clearfix tsm" align="center">
            <div style="margin: 0px auto; text-align: center;" class="sidebarBoxLoginTMS" align="center">
                <div style="height: 5px;"> &nbsp; </div>
                <div style="height: 5px;"> &nbsp; </div>
                <div style="height: 10px;"> &nbsp; </div>
                <form id="chgMobileBaseForm">
                    <table width="100%">
                        <tr>
                            <td class="TMSLabel">
                                <div class="logLblTMS" id="useriddiv">
                                        <span class="sidetext"><b> <!--UserId-->
                                        </b></span>
                                </div>
                            </td>
                        </tr>

                        <% if ("true".equalsIgnoreCase(TAN_pass)) { %>
                        <%@ include file="updateMobileStep2.jsp" %>
                        <%} else { %>
                        <%@ include file="updateMobileStep1.jsp" %>
                        <%} %>

                    </table>

                </form>
            </div>
            <div style="height:30px;">&nbsp;</div>

            <div id="DivFormsToSubmit">
                <form id="chgMobileStep1" method="post" action="updateMobile">
                    <input name="pin" type="hidden"/>
                    <input name="TAN_pass" type="hidden"/>
                    <input name="TAN_input" type="hidden"/>
                    <input id="goto" name="goto" type="hidden"
                           value="<c:out value="${requestScope.goto}" default="/" />"/>
                </form>

                <form id="chgMobileStep2" method="post" action="updateMobile">
                    <input name="pin" type="hidden"/>
                    <input name="TAN_pass" type="hidden"/>
                    <input name="OTPmode_sent" type="hidden"/>
                    <input name="OTPinput" type="hidden"/>
                    <input id="goto" name="goto" type="hidden"
                           value="<c:out value="${requestScope.goto}" default="/" />"/>
                </form>

            </div>

            <%@ include file="../footer.jsp" %>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        //do nothing
    });

    function submitValidateTAN() {

        var newcc = $("#chgMobileBaseForm #newcc").val();
        var newac = $("#chgMobileBaseForm input#newac").val();
        var newmob = $("#chgMobileBaseForm input#newmob").val();
        var TAN_input = $("#chgMobileBaseForm input#TAN_input").val();

        console.log("[Debug] submitValidateTAN input: cc=" + newcc + ", ac=" + newac + ", mob = " + newmob + ", TAN=" + TAN_input);

        // Validate mobile number
        if (!$.trim(newmob) || isNaN(newmob) || isNaN(newac)) {
            alert("Please remove any hyphen, space or slash in the mobile number. There should only be numbers.");
            return;
        }

        // Validate TAN (transaction signing)
        if (!$.trim(TAN_input) || isNaN(TAN_input)) {
            alert("Please remove any hyphen, space or slash in the Sign Code. There should only be numbers.");
            return;
        }

        var newmobFull = newcc + newac + newmob;
        $("#chgMobileStep1 input[name='pin']").val(newmobFull);
        $("#chgMobileStep1 input[name='TAN_pass']").val("false");
        $("#chgMobileStep1 input[name='TAN_input']").val(TAN_input);
        $("#chgMobileStep1").submit();

    }

    function submitValidateOTP(mode) {

        var pin = "<%=ESAPI.encoder().encodeForJavaScript(newMobile)%>";
        $("#chgMobileStep2 input[name='pin']").val(pin);

        if ("resend" == mode) {
            $("#chgMobileStep2 input[name='TAN_pass']").val("false");
            $("#chgMobileStep2 input[name='OTPmode_sent']").val("false");
        } else if ("resendOTP" == mode) {
            $("#chgMobileStep2 input[name='TAN_pass']").val("true");
            $("#chgMobileStep2 input[name='OTPmode_sent']").val("resend");
        } else {
            $("#chgMobileStep2 input[name='TAN_pass']").val("true");
            var OTPinput = $("#chgMobileBaseForm input#OTPinput").val();
            console.log("[DEBUG]submitValidateOTP, OTP input=" + OTPinput);
            $("#chgMobileStep2 input[name='OTPinput']").val(OTPinput);
        }

        $("#chgMobileStep2").submit();

    }

</script>
</body>
</html>