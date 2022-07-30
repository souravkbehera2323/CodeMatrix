
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" style="background-color: #012e4f;">
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
        <div class="login-containerSession" >

            <div class="login-box-reg animated fadeInDown">

                <div class="login-body-reg" id="loginid">
                    <img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block">

                    <div class="row">
                        <div class="col-md-12">
                            <!-- START LOGIN FORM HERE -->
                            <form:form cssClass="form-horizontal" modelAttribute="taker" method="POST" name="form1" id="form1" enctype="multipart/form-data" action="../takers/resume.htm">
                                <div class="form-group">
                                    <br>
                                    <label class="col-md-6 col-xs-12 control-label"><h2>Continue Your C.O.D.E. Test</h2></label>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="form-group">
                                    <label class="col-md-2 col-xs-12 control-label">Last Name:</label>
                                    <div class="col-md-6 col-xs-12">
                                        <form:input path="lastName" maxlength="50" cssClass="form-control"/>
                                    </div>
                                </div></br>
                                <div class="form-group">
                                    <label class="col-md-2 col-xs-12 control-label">Email:</label>
                                    <div class="col-md-6 col-xs-12">
                                        <form:input path="email" maxlength="50" cssClass="form-control"/>
                                    </div>
                                </div></br>
                                <div class="form-group">
                                    <div class="col-md-4">
                                    </div>
                                    <div class="col-md-6">
                                        <button class="btn btn-success" id="loginBtn">   Continue My C.O.D.E Test </button>
                                    </div>

                                </div>

                                </form:form>
                            <!-- MESSAGE SECTION -->
                            <%@include file="../common/message.jsp"%>
                            <!-- END MESSAGE SECTION -->
                            <%@include file="../common/script.jsp" %>
                        </div>
                    </div> 
                </div>
            </div>

            <!-- START TEMPLATE -->
            <script type="text/javascript" src="../js/plugins.js"></script>        
            <script type="text/javascript" src="../js/actions.js"></script>
            <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
            <!-- END TEMPLATE -->
            <script type="text/javascript">
                $("#regid").hide();
                $("#regBtn").click(function (e) {
                    $("#loginid").hide();
                    $("#regid").show();
                });

                var jvalidate = $("#form1").validate({
                    ignore: [],
                    rules: {
                        lastName: {
                            required: true,
                            maxlength: 50
                        },
                        email: {
                            required: true,
                            email: true
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
            </script>
    </body>
</html>