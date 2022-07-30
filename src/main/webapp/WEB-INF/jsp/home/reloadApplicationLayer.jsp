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
                    <li><a href="#">Dashboard</a></li>
                </ul>
                <!-- END BREADCRUMB --> 
                <div class="page-content-wrap" >
                    <!-- PAGE TITLE -->
                    <div class="page-title">                    
                        <h2>Home</h2>
                    </div>
                    <!-- END PAGE TITLE -->
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Role list</h3>
                                </div>
                                <div class="panel-body">
                                    <!-- START FILTER PANEL -->

                                    <!-- END FILTER PANEL -->
                                    <div class="row">
                                        <div class="col-md-9 scrollable">
                                            <table class="table datatable table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Role Id</th>
                                                        <th>Role Name</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${roleList}" var="r">
                                                        <tr class="context-enabled">
                                                            <td>${r.roleId}</td>
                                                            <td>${r.roleName}</td>
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
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Current Business Functions</h3>
                                </div>
                                <div class="panel-body">
                                    <!-- START FILTER PANEL -->

                                    <!-- END FILTER PANEL -->
                                    <div class="row">
                                        <div class="col-md-9 scrollable">
                                            <table class="table datatable table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Business function</th>
                                                        <th>Business function desc</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${bfList}" var="b">
                                                        <tr class="context-enabled">
                                                            <td>${b.businessFunctionId}</td>
                                                            <td>${b.businessFunctionDesc}</td>
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
            </div>
            <!-- START PAGE CONTAINER -->
        </div>
    </div>
    <!-- PAGE CONTENT -->
    <%@include file="../common/messagebox.jsp" %>

    <%@include file="../common/script.jsp" %>
    <!-- START THIS PAGE PLUGINS-->        
    <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
    <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
    <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
    <!-- END THIS PAGE PLUGINS-->        

    <!-- START TEMPLATE -->
    <script type="text/javascript" src="../js/plugins.js"></script>        
    <script type="text/javascript" src="../js/actions.js"></script>
    <!-- END TEMPLATE -->
</body>
</html>