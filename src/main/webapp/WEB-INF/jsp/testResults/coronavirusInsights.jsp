<%-- 
    Document   : coronavirusInsights
    Created on : 27 Mar, 2020, 3:08:57 PM
    Author     : altius
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
                    <li><a href="#">Coronavirus Insights</a></li>
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
                                    <h3 class="panel-title">Coronavirus Insights</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-10 scrollable">
                                            <table class="table datatable table-bordered">
                                                <thead> 
                                                    <tr>
                                                        <th>Question</th>
                                                        <th>Percentage</th>                                                                                                                
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${dataList}" var="item">
                                                        <tr>
                                                            <td>${item['question']}</td>
                                                            <td>${item['normalDstrValue']}</td>                                                            
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
         <script>
            //disble automatic table sorting
            $('.table').dataTable({                
                "order": [],
                "bPaginate": false
            });
        </script>
    </body>
</html>