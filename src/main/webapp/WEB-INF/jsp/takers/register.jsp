
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head><!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->
        <!-- CSS INCLUDE -->        
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->  
        <style type="text/css">
            label {
                color: white;
                font-size: 15px;
            }
            div.panel-title {
                font-size: 25px;
                color: white;
            }
            input[type=text] {
                font-size: 15px;
            }
            p {
                color: white;
                font-size: 15px;
            }
            h2{
                color: white;
                font-size: 25px;
            }

        </style>
    </head>
    <body>        
        <div class="login-containerRe" >

            <div class="login-box-reg animated fadeInDown">

                <div class="login-body-reg" id="loginid">
                    <img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block">
                    <!-- START LOGIN FORM HERE -->
                    <form:form cssClass="form-horizontal" modelAttribute="taker" method="POST" name="form1" id="form1" enctype="multipart/form-data">
                        <div class="form-group">
                            <br>
                            <label class="col-md-8 col-xs-12 control-label"><h2>Register For C.O.D.E to get immediate access to your results!</h2></label>
                        </div>
                        <input type="hidden" name="lastNameId" id="lastNameId" value="${lastNameId}"/>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">First Name:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="firstName" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">Last Name:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="lastName" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class=" col-md-2 col-xs-12 control-label">Password:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:password path="password" id="password" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class=" col-md-2 col-xs-12 control-label">Confirm Password:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:password path="confirmPassword" id="confirmPassword" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">Email:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="email" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">Phone:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="phone" maxlength="50" cssClass="form-control" />
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">Age:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="age" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">                                        
                            <label class="col-md-2 col-xs-12 control-label">Gender:</label>
                            <div class="col-md-6 col-xs-12">                                                                                
                                <form:select path="gender" maxlength="50" cssClass="form-control select">
                                    <form:option value="1">Male</form:option>
                                    <form:option value="2">Female</form:option>
                                </form:select>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">ZipCode:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="zipCode" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <label class="col-md-2 col-xs-12 control-label">Referral:</label>
                            <div class="col-md-6 col-xs-12">
                                <form:input path="referredBy" maxlength="50" cssClass="form-control"/>
                            </div>
                        </div></br>
                        <div class="form-group">
                            <div class="col-md-4">
                            </div>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-success" id="loginBtn" onclick="return checkValidation();">Register for C.O.D.E  </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                            </div>
                            <div class="col-md-5">
                                <p id="two">Already started? <a  href="../takers/resume.htm" id="regBtn">Resume by clicking here</a></p>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-8 col-xs-12 control-label"><h3 style="color: white;">*  Answer the remain questionings how you are now, not how you are normally.</h3></label>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12">
                                <c:if test="${param.error != null}">
                                    <span class="text-danger">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                    <div>
                                        <span class="text-danger">You have been logged out.</span>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </form:form>
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <%@include file="../common/script.jsp" %>
                    <%@include file="../common/messagebox.jsp" %>    

                    <div class="col-md-12 col-xs-12">                                                                                
                        <spring:hasBindErrors name="taker">
                            <span class="text-danger">
                                <form:errors path="*"/>
                            </span>
                        </spring:hasBindErrors>
                    </div>
                </div>
            </div>
        </div>

        <!-- START THIS PAGE PLUGINS-->        
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script> 
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->
        <script type="text/javascript">

                                    $("#regid").hide();
                                    $("#regBtn").click(function (e) {
                                        $("#loginid").hide();
                                        $("#regid").show();
                                    });


        </script>
        <script type="text/javascript">

            var jvalidate = $("#form1").validate({
                ignore: [],
                rules: {
                    firstName: {
                        required: true,
                        minlength: 4,
                        maxlength: 25
                    },
                    lastName: {
                        required: true,
                        maxlength: 50
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    phone: {
                        required: true,
                        minlength: 10,
                        maxlength: 10,
                        number: true
                    },
                    age: {
                        required: true
                    }
                    ,
                    gender: {
                        required: true
                    }
                },
                errorPlacement: function (error, element) {
                    if (element.hasClass('select')) {
                        error.insertAfter(element.next(".bootstrap-select"));
                        element.next("div").addClass("error");
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            function checkPassword()
            {
                if ($("#password").val() == $("#confirmPassword").val())
                {
                    return true;
                } else {
                    alert("password and confirm password does not match");
                    return false;
                }
            }

            function checkEmailId()
            {
                var emailId = $("#email").val();
                var result = false;
                $.ajax({
                    type: "GET",
                    url: "../takers/checkEmailId.htm",
                    async: false,
                    data: {
                        emailId: emailId
                    },
                    dataType: "json",
                    success: function (json) {

                        if (json != null) {
                            if (json == true)
                            {
                                alert("Email Id Already Exist");
                                result = false;
                            } else {
                                result = true;
                            }
                        }
                    }
                });
                return result;
            }
            function checkValidation()
            {
                var form = $('#form1');
                if (form.valid()) {
                    if (!checkPassword())
                    {
                        return false;
                    }
                    if (!checkEmailId())
                    {
                        return false;
                    }
                    return true;
                } else {
                    return false;
                }
            }
        </script>
        <script language="JavaScript">
            javascript:window.history.forward(1);
        </script>
    </body>
</html>