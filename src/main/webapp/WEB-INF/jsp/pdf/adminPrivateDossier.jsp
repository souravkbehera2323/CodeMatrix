<%--
    Document   : testResultList
    Created on : 5th Sept 2019, 08:13 AM
    Author     : Yogesh Bhagwat
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
        <script type="text/javascript" src="../js/Chart.min.js"></script>
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Test Results</a></li>
                    <li><a href="#">View</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!--<div class="login-logo-pdf"></div>-->
                    <img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block">
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">

                            <form:form modelAttribute="testSessions" method="POST" name="form1" id="form1" action="../pdf/adminPrivateDossier.htm">
                                <c:set var="value" value="1"/>
                                <c:forEach items="${testSessions}" var="t" >
                                    <c:if test="${value==1}">
                                        <br><br><h1 style="text-align: center; color:Black;"><strong>Official C.O.D.E.Dossier</strong></h1>
                                        <br> <h2  style="text-align: center;"><strong>#${t.taker.takerId}</strong></h2>
                                        <div class="panel-body">
                                            <h4><strong>Test Taker: </strong> ${t.taker.firstName} ${t.taker.lastName}</h4>
                                            <h4><strong>Test Started: </strong> ${t.testStarted}</h4>
                                            <h4><strong>Test Ended: </strong> ${t.testEnded}</h4>
                                            <h4><strong>Testing Session ID: </strong> ${t.testSessionId}</h4>
                                        </div>
                                    </c:if>
                                    <c:set var="value" value="0"/> 
                                </c:forEach>
                                <br>
                                <%-- Etremeism Module Start --%>
                                <div class="panel-body">
                                    <h2 style="text-align: left; color:Black;">EXTREMEISM DIMENSION</h2>
                                    <div class="panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-9 scrollable">
                                                    <table class="table table-bordered">
                                                        <thead> 
                                                            <tr>
                                                                <th style="font-weight:bold">Aspect Name</th>
                                                                <th style="font-weight:bold">Average</th>
                                                                <th style="font-weight:bold">Test Percentage</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${list}" var="tl">
                                                                <tr class="table_row">
                                                                    <c:if test="${tl.flag==1}" >
                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">${tl.traitName}</td>
                                                                        <td>${tl.traitMean}</td>
                                                                        <td>${tl.traitStdDeviation}</td>  </c:if>
                                                                    <c:if test="${tl.flag==2}" >
                                                                        <td>${tl.aspectName}</td>
                                                                        <td>${tl.aspectMean}</td>
                                                                        <td>${tl.aspectStdDeviation}</td>
                                                                    </c:if>
                                                                </tr>

                                                            </c:forEach> 
                                                        </tbody>
                                                    </table>                  
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- Etremeism Module End --%>
                            </form:form>
                        </div>
                        <form:form name="pdfForm" id="pdfForm" method="POST" action="../pdf/adminPrivateDossier.htm?takerId=${takerId}">
                        </form:form>
                    </div>
                    <!-- END PAGE CONTENT WRAPPER -->
                </div>
                <!-- END PAGE CONTENT -->
            </div>
            <!-- END PAGE CONTAINER -->
        </div>
        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->        
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/contextMenu/jquery.contextMenu.css"  />
        <script type="text/javascript" src="../js/plugins/contextMenu/jquery.contextMenu.js"></script>

        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <script type="text/javascript" src="../js/plugins/morris/raphael-min.js"></script>
        <script type="text/javascript" src="../js/plugins/morris/morris.min.js"></script>       
        <script type="text/javascript" src="../js/plugins/moment.min.js"></script>
        <script type="text/javascript" src="../js/plugins/daterangepicker/daterangepicker.js"></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->
    </body>
</html>