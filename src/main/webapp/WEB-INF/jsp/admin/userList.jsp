<%--
    Document   : userList
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
                    <li><a href="#">User</a></li>
                    <li><a href="#">List User</a></li>
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
                                    <h3 class="panel-title">Users Module</h3>
                                    <ul class="panel-controls">
                                        <li><a href="../admin/userAdd.htm"><span class="fa fa-plus"></span></a></li>
                                        <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>
                                        <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Name</th>
                                                        <th>Email</th>
                                                        <th>Username</th>
                                                        <th>Role</th>
                                                        <th>Status</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${userList}" var="u">
                                                        <tr class="clickableRow context-enabled" data-user-id="${u.userId}" data-status="${u.activeString}">
                                                           <td>${u.userId}</td>
                                                            <td>${u.name}</td>
                                                            <td>${u.emailId}</td>
                                                            <td>${u.username}</td>
                                                            <td>${u.roleList}</td>
                                                            <td>${u.active}</td>
                                                             <sec:authorize access="hasAuthority('ROLE_BF_EDIT_USER')"> 
                                                                 <td style="text-align: center;">
                                                                    <a href="../admin/showUserEdit.htm?userId=${u.userId}" class="editLink"  title="Edit User">
                                                                    <img src="../images/edit.png" style="border: 0;"/></a>
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
                    <input type="hidden" id="userId" name="userId"/>
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

            $('.table tbody tr').each(function () {
                var status = $(this).data("status");
                if (status === 'Locked') {
                    $(this).addClass('warning');
                }
                if (status === 'Disabled') {
                    $(this).addClass('danger');
                }
            });

            function contextMenuItemClicked(key, options, userId, status, element) {
                console.log("contextMenu");
                switch (key) {
                    case 'undo':
                        console.log("Inside undo");
                        $.ajax({
                            url: "../admin/resetPassword.htm",
                            type: "POST",
                            data: {
                                userId: userId,
                                ${_csrf.parameterName}: '${_csrf.token}'
                            },
                            dataType: "text",
                            success: function (response) {
                            },
                            error: function (e) {
                                console.log(e);
                                alert('There was an error whith the Ajax call');
                            }
                        });
                        break;
                    case 'unlock':
                        $.ajax({
                            url: "../admin/failedAttemptsReset.htm",
                            type: "POST",
                            data: {
                                userId: userId,
                                ${_csrf.parameterName}: '${_csrf.token}'
                            },
                            dataType: "text",
                            success: function (response) {
                                element.removeClass("warning");
                                element.data("status", "Active");
                                element.find('td').eq(3).text("Active");
                            },
                            error: function (e) {
                                alert('There was an error whith the Ajax call');
                            }
                        });
                        break;
                    case 'edit':
                        $('#userId').val(userId);
                        $('#form2').prop('action', '../admin/showUserEdit.htm');
                        $('#form2').submit();
                        break;
                    default:
                }
            }

            $.contextMenu({
                selector: '.context-enabled',
                build: function ($trigger, e) {
                    var status = $trigger.data("status");
                    if (status === 'Active') {
                        return {
                            callback: function (key, options) {
                                contextMenuItemClicked(key, options, $trigger.data("user-id"), $trigger.data("status"), $trigger);
                            },
                            items: {
                                "edit": {name: "Edit user", icon: "edit"}
                                , "sep1": "---------"
                                , "undo": {name: "Reset password", icon: "password"}
                            }
                        };
                    } else if (status === 'Locked') {
                        return {
                            callback: function (key, options) {
                                contextMenuItemClicked(key, options, $trigger.data("user-id"), $trigger.data("status"), $trigger);
                            },
                            items: {
                                "edit": {name: "Edit user", icon: "edit"}
                                , "sep1": "---------"
                                , "unlock": {name: "Unlock User", icon: "locked"}
                            }
                        };
                    } else if (status === 'Disabled') {
                        return {
                            callback: function (key, options) {
                                contextMenuItemClicked(key, options, $trigger.data("user-id"), $trigger.data("status"), $trigger);
                            },
                            items: {
                                "edit": {name: "Edit user", icon: "edit"}
                            }
                        };
                    } else {
                        return null;
                    }
                }
            });
            
            $('.clickableRow td').click(function (e) {
                e.stopPropagation();
                $('#userId').val($(this).parent().data("user-id"));
                $('#form2').prop('action', '../admin/showUserEdit.htm');
                $('#form2').submit();
            });

        </script>
    </body>
</html>