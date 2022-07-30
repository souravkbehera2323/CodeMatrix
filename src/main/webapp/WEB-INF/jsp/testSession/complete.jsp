<%--
    Document   : complete
    Created on : 24t Nov 2019, 23:07:50
    Author     : Yogesh Bhagwat
    Modified on : 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->

        <!-- CSS INCLUDE -->
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->
        <style>
            [class*='col'] {
                position: static;
            }
            div.fixed {
                position: fixed;

                margin-left: -50px; 
            }
        </style>
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-navigation-toggled page-container-wide">


            <!-- PAGE CONTENT -->
            <div class="page-content" style="background-color: #012e4f;">
                <!--<div class="login-logo-reg"></div>-->
                <div class="col-md-12">
                    <div class="col-md-10"><img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block"></div>

                    <div class="col-md-3 pull-right ">
                        <a href="#" onclick="myFunction()"  style="color: white;" ><span class="fa fa-bars fa-2x" ></span> <span class="xn-text"></span></a>
                        <div id="loginAndContinue" hidden="true" class="fixed">
                            <a href="../home/login.htm" class="alink" style="color: white;">Login and view results</a><br>
                            <a href="#" class="add-button" style="color: white;">Install C.O.D.E.</a>
                        </div>
                    </div>

                </div>


                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form modelAttribute="testSession" method="POST" name="form1" id="form1">
                                <div class="panelTaker panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <div class="col-md-2 col-xs-12"></div>
                                            <div class="col-md-8 col-xs-12">
                                                <input type="hidden" cssClass="form-control" id="testSessionId" name="testSessionId" value=${testSession.testSessionId}/>
                                                <h4><font color="white">User : ${testSession.taker.firstName}</h4>
                                                <h4><font color="white">#Test Session ID ${testSession.testSessionId}</font></h4>
                                                <h4><b><font color="white">Progress </font></b></h4>
                                                <div class="progress progress-striped progress-striped active" >
                                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="594" style="width: 100%;"></div>
                                                </div>
                                                <h3><font color="white">You have answered all the questions!</font></h3><br>
                                                <h2 style="color: white;text-align: center;">
                                                    Congratulations!  You have completed the test!
                                                </h2>
                                            </div>
                                            <%--<div style="text-align: center;float:right;">&nbsp;&nbsp;
                                            <h6 style="color: white;text-align: center;float:right;"> Thank you for taking the time to complete this extensive test.  We assure you it will be well worth your time.  Now, please sit back, relax and take a breather
                                                while we assign your test to one of our certified <span class="font-weight-bold">CODE BREAKERS</span> who will evaluate and deliver your personalized test results.
                                                If you have any questions or concerns please feel free to contact our offices:<br>
                                                Phone: 1.888.555.5555<br>
                                                Email: breakers@thecodetest.com
                                            </h6>
                                        </div><br>--%>
                                            <div style="text-align: center;float:right;">&nbsp;&nbsp;
                                                <h6 style="color: white;text-align: center;float:right;"> Thank you so much for delving deep into yourself. The information and data you have provided will be crucial in combating this threat, as well as preventing future ones. We will be providing you more information and insights on you data in the coming days, so please stay tuned! 
                                                    Check out our group to stay up to date and to see what other way you can help! We will be honoring people such as yourself for helping out during this time.
                                                    <a href="https://www.facebook.com/groups/coronacoalition/">https://www.facebook.com/groups/coronacoalition/</a><br>

                                                </h6>
                                            </div><br>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div> 
                    </div>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>


        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->        
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/pwa/pwa-customization.js"></script>    
        <!-- END THIS PAGE PLUGINS-->        
        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script>
                                function myFunction() {
                                    $("#loginAndContinue").toggle();
                                }
        </script>
    </body>
</html>