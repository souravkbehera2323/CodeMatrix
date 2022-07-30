<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="body-full-height">
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
                    <div class="login-logo-reg"></div>
                    <div>
                        <h2 style="padding:46px;"></h2>
                    </div>
                    <div>
                        <center><h3 style="color: white;"></h3></center> </br>
                    </div>
                    <!-- START LOGIN FORM HERE -->
                    <form cssClass="form-horizontal" method="POST" name="form1" id="form1">

                        <div class="form-group">                                        
                            <label class="col-md-4 col-xs-12 control-label" id="medicalProfessional">Is Medical Professional:</label>
                            <div class="col-md-6 col-xs-12">                                                                                
                                <label style="color: white">Yes</label>&nbsp;<input type="radio" name="medicalProfessional" value="true" id="medicalProfessionalYes"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <label style="color: white">No</label>&nbsp;<input type="radio" name="medicalProfessional" value="false" id="medicalProfessionalNo"/>
                                <span class="help-block"></span>
                            </div>
                        </div>                                                
                        <div class="form-group" id="covidDiv" style="display: none;">                                        
                            <label class="col-md-4 col-xs-12 control-label">Related to covid </label>
                            <div class="col-md-6 col-xs-12">                                                                                
                                <select name="covid" maxlength="50" cssClass="form-control select">
                                    <option value="0">Select Any</option>
                                    <option value="Directly related to COVID">Directly related to COVID</option>
                                    <option value="Indirectly related to COVID">Indirectly related to COVID</option>
                                    <option value="Not related to COVID">Not related to COVID</option>
                                </select>
                            </div>
                        </div></br>
                        <div class="form-group">

                            <div class="col-md-6">
                                <button type="submit" class="btn btn-success" id="loginBtn">Submit</button>
                            </div>

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
                    </form>

                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <%@include file="../common/script.jsp" %>
                    <%@include file="../common/messagebox.jsp" %>
                    <!-- START THIS PAGE PLUGINS-->        
                    <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
                    <!-- END THIS PAGE PLUGINS-->        

                    <!-- START TEMPLATE -->
                    <script type="text/javascript" src="../js/plugins.js"></script>        
                    <script type="text/javascript" src="../js/actions.js"></script>
                    <!-- END TEMPLATE -->

                </div>
            </div>
            <script type="text/javascript">

                $("#medicalProfessionalYes") // select the radio by its id
                        .change(function () { // bind a function to the change event
                            if ($(this).is(":checked")) { // check if the radio is checked
                                var val = $(this).val(); // retrieve the value
                                if (val == "true") {
                                    $("#covidDiv").show();
                                }

                            }
                        });
                $("#medicalProfessionalNo") // select the radio by its id
                        .change(function () { // bind a function to the change event
                            if ($(this).is(":checked")) { // check if the radio is checked
                                var val = $(this).val(); // retrieve the value
                                if (val == "false") {
                                    $("#covid").val("0");
                                    $("#covidDiv").hide();
                                }
                            }
                        });


            </script>
    </body>
</html>