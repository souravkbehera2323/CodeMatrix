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
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Test Session Results</h3>
                                </div>
                                <sec:authorize access="hasAnyRole('ROLE_BF_LIST_USER')">
                                    <form name="form1" id="form1" method="post" >
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label>Takers :</label>
                                                        <select name="takerId" maxlength="50" class="form-control select">
                                                            <option value="0">Select Any</option>
                                                            <option value="1">Unregistered Takers</option>
                                                            <option value="2">Registered Takers</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <br>
                                                    <button type="submit" class="btn btn-success">Submit</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </sec:authorize>    
                                <div class="panel-body">
                                    <div class="row">
                                        <div id="loader" style="display: none">
                                            <div id="loadingText" class="btn"><b>May take around 30 seconds as CODE is calculating your results</b></div>
                                            <div id="loaderImg"></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered">
                                                <thead> 
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Taker Name</th>
                                                        <th>Test Started</th>
                                                        <th style="width: 80px;">Last Question Sort</th>
                                                        <th>Test Ended</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${takerResultSessionList}" var="u">
                                                        <tr class="clickableRow context-enabled" data-question-id="${u.taker.takerId}">
                                                            <td>${u.taker.takerId}</td>
                                                            <td>${u.taker.firstName} ${u.taker.lastName}</td>
                                                            <%--${u.taker.lastName}--%>
                                                            <td>${u.testStartedTime}</td>
                                                            <td>${u.lastSortOrderId}</td>
                                                            <td>${u.testEndedTime}</td>
                                                            <sec:authorize access="hasAuthority('ROLE_BF_LIST_QUESTIONS')">
                                                                <td style="text-align: center;">

                                                                    <sec:authorize access="hasAuthority('ROLE_BF_CREATE_QUESTIONS')">
                                                                        <a href="../report/exportRaw.htm?takerId=${u.taker.takerId}" class="editLink"  title="Export Raw">Export Raw<img src="../images/excelIcon.png" style="border: 0;"/></a>
                                                                            <%--<a href="../report/exportRaw.htm?takerId=${u.taker.takerId}" class="editLink"  title="Public Dossier">Public Dossier<img src="../images/excelIcon.png" style="border: 0;"/></a>--%>
                                                                        </sec:authorize>
                                                                        <%-- <a href="../pdf/newPdf.htm?takerId=${u.taker.takerId}"  class="editLink"  title="Private Dossier">Private Dossier<img src="../images/dossier.png" style="border: 0;"/></a>--%>
                                                                    <a onclick="showLoading();" href="../pdf/comprehensiveDossier.htm?takerId=${u.taker.takerId}"  class="editLink"  title="Comprehensive Dossier">Comprehensive Dossier<img src="../images/dossier.png" style="border: 0;"/></a>
                                                                        <sec:authorize access="hasAuthority('ROLE_BF_ADMIN_PRIVATE_DOSSIER')">
                                                                        <a href="../pdf/adminPrivateDossier.htm?takerId=${u.taker.takerId}"  class="editLink"  title="Admin Private Dossier">Admin Private Dossier<img src="../images/dossier.png" style="border: 0;"/></a>
                                                                        </sec:authorize>
                                                                        <c:if test="${maxSortOrder!=u.lastSortOrderId}">
                                                                        <a href="../takers/resume.htm?takerId=${u.taker.takerId}"  class="editLink"  title="Answer More Question">Answer More Question<img src="../images/exam.png" style="border: 0;"/></a>
                                                                        </c:if>
                                                                </td>
                                                            </sec:authorize>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>                  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form name="form2" id="form2" action="" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript">
                                                                        function showLoading() {
                                                                            document.getElementById("loader").style.display = "block";
                                                                        }


                                                                        $('.table').dataTable({
                                                                            aLengthMenu: [
                                                                                [10, 25, 50, 100],
                                                                                [10, 25, 50, 100]
                                                                            ],
                                                                            iDisplayLength: 100
                                                                        });
        </script>
    </body>
</html>