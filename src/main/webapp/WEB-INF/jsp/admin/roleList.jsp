<%--
    Document   : roleList
    Created on : 25th Feb 2016, 08:13 AM
    Author     : Akil Mahimwala
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
                    <li><a href="#">Admin</a></li>
                    <li><a href="#">Role</a></li>
                    <li><a href="#">List Role</a></li>
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
                                    <h3 class="panel-title">Role list</h3>
                                    <ul class="panel-controls">
                                        <li><a href="../admin/roleAdd.htm"><span class="fa fa-plus"></span></a></li>
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
                                                        <th>Role Id</th>
                                                        <th>Role name</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${roleList}" var="r">
                                                        <tr class="clickableRow context-enabled" data-role-id="${r.roleId}">
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
                </div>
                <form name="form2" id="form2" action="" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="roleId" name="roleId"/>
                </form>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->        
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/contextMenu/jquery.contextMenu.css"  />
        <script type="text/javascript" src="../js/plugins/contextMenu/jquery.contextMenu.js"></script>

        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->
        <script type="text/javascript">

            function contextMenuItemClicked(key, options, roleId, element) {
                console.log("contextMenu");
                switch (key) {
                    case 'manage':
                        $('#roleId').val(roleId);
                        $('#form2').prop('action', '../admin/showCanCreateRoles.htm');
                        $('#form2').submit();
                        break;
                    case 'edit':
                        $('#roleId').val(roleId);
                        $('#form2').prop('action', '../admin/showRoleEdit.htm');
                        $('#form2').submit();
                        break;
                    default:
                }
            }

            $.contextMenu({
                selector: '.context-enabled',
                build: function ($trigger, e) {
                   return {
                        callback: function (key, options) {
                            contextMenuItemClicked(key, options, $trigger.data("role-id"), $trigger);
                        },
                        items: {
                            "edit": {name: "Edit Role", icon: "edit"}
                            , "sep1": "---------"
                            , "manage": {name: "Manage Roles", icon: "password"}
                        }
                    };
                }
            });

            $('.clickableRow td').click(function (e) {
                e.stopPropagation();
                $('#roleId').val($(this).parent().data("role-id"));
                $('#form2').prop('action', '../admin/showRoleEdit.htm');
                $('#form2').submit();
            });

        </script>
    </body>
</html>