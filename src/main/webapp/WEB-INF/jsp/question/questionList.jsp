<%--
    Document   : questionList
    Created on : 18th Oct 2019, 23:07:50
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
                    <li><a href="#">Question</a></li>
                    <li><a href="#">Question List</a></li>
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
                                    <h3 class="panel-title">Question</h3>
                                    <ul class="panel-controls">
                                        <li><a href="../question/questionAdd.htm"><span class="fa fa-plus"></span></a></li>
                                        <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>
                                        <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-9 scrollable">
                                            <table class="table datatable table-bordered">
                                                <thead> 
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Question Type</th>
                                                        <th>Test</th>
                                                        <th>Name</th>
                                                        <th>Sort Order</th>
                                                        <th>Status</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${questionList}" var="u">
                                                        <tr class="clickableRow context-enabled" data-question-id="${u.questionId}">
                                                            <td>${u.questionId}</td>
                                                            <td>${u.questionType.questionTypeName}</td>
                                                            <td>${u.questionTestType.questionTestTypeName}</td>
                                                            <td>${u.questionName}</td>
                                                            <td>${u.sortOrder}</td>
                                                            <td>
                                                                <c:if test="${u.active==true}"><spring:message code="active"/></c:if>
                                                                <c:if test="${u.active==false}"><spring:message code="inactive"/></c:if>
                                                                </td>
                                                            <sec:authorize access="hasAuthority('ROLE_BF_LIST_QUESTIONS')"> <td style="text-align: center;">
                                                                    <a href="../question/editQuestion.htm?questionId=${u.questionId}" class="editLink"  title="Edit User">Edit<img src="../images/edit.png" style="border: 0;"/></a>
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
                    <input type="hidden" id="questionId" name="questionId"/>
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
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

    </body>
</html>