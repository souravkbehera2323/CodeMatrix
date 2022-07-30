<%-- 
    Document   : TakerAttemptedQuestions
    Created on : 21-Apr-2020, 12:47:12 PM
    Author     : yogesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <h3 class="panel-title">No. Of Question Attempted By Taker</h3>
                                    <ul class="panel-controls">
                                        <c:if test="${fn:length(takerAttemptedQuestionsList)>0}"><li><a href="#" onclick="$('#excelForm').submit();" title="Export to excel"><span class="fa fa-file-excel-o"></span></a></li></c:if>
                                            <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                        </ul>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 scrollable">
                                                <table class="table datatable table-bordered">
                                                    <thead> 
                                                        <tr>
                                                            <th>Taker First Name</th>
                                                            <th>Taker Last Name</th>
                                                            <th>No. of Question Attempted</th>
                                                            <th style="width: 180px;">Test Started</th>
                                                            <th style="width: 180px;">Test Ended</th>
                                                            <th style="width: 180px;">Referal</th>
                                                            <th style="width: 200px;">Email Id</th>
                                                            <th style="width: 250px;">Phone No</th>
                                                            <th style="width: 150px;">Zip Code</th>
                                                            <th style="width: 200px;">Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                    <c:forEach items="${takerAttemptedQuestionsList}" var="u">
                                                         <c:choose>
                                                            <c:when test="${u.active}"><c:set var="rowClass" value=""/></c:when>
                                                            <c:otherwise><c:set var="rowClass" value="warning1"/></c:otherwise>
                                                        </c:choose>
                                                           <tr class="clickableRow context-enabled ${rowClass}" data-taker-id="${u.takerId}">
                                                            <td>${u.firstName}</td>
                                                            <td>${u.lastName}</td>
                                                            <td>${u.noOfQuestionAttempted}</td>
                                                            <td>${u.testStarted}</td>
                                                            <td>${u.testEnded}</td>
                                                            <td>${u.referredBy}</td>
                                                            <td>${u.email}</td>
                                                            <td>${u.phone}</td>
                                                            <td>${u.zipCode}</td>
                                                            <sec:authorize access="hasAuthority('ROLE_BF_LIST_QUESTIONS')"> 
                                                                <td style="text-align: center;">
                                                                    <a href="../takers/editNoOfQuestionAttemptedByTaker.htm?takerId=${u.takerId}"  class="editLink"  title="Edit User"><img src="../images/edit.png" style="border: 0;"/></a>
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
                    <form name="excelForm" id="excelForm" method="get" action="../report/reportNoofTakerAttemptedQuestion.htm">

                    </form>
                </div>
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

    </body>
</html>