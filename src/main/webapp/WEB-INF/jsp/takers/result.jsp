<%-- 
    Document   : result
    Created on : 31 Mar, 2020, 7:30:09 PM
    Author     : altius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" style="background-color: #012e4f;">
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
            table thead tr th {                
                font-size: 30px;
                text-align: center;
            }

            table tbody tr td {                
                color: white;                                
            }
        </style>
    </head>
    <body>        
        <div class="login-containerSession" >

            <div class="login-box-reg animated fadeInDown">

                <div class="login-body-reg" id="loginid">
                    <img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block">
                    <div style="padding:46px;">
                        <h2>Your C.O.D.E. Test Result</h2>
                    </div>
                    <div class="row">                        
                        <div class="col-md-8"></div>
                        <div class="col-md-2">
                            <div class="">
                                <a href="../takers/register.htm?lastNameId=${lastNameId}" class="btn btn-lg btn-success">Register here to Continue..</a>
                            </div>
                        </div>                                                       
                        <div class="col-md-2"></div>
                    </div>
                    <br>
                    <div class="row">                                                                                       
                        <div class="col-md-2"></div>                                
                        <div class="col-md-8">                                
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
                            <br>                            

                            <!-- MESSAGE SECTION -->
                            <%@include file="../common/message.jsp"%>
                            <!-- END MESSAGE SECTION -->
                            <%@include file="../common/script.jsp" %>
                        </div>                                                                          
                    </div> 
                </div>
            </div>

            <!-- START TEMPLATE -->
            <script type="text/javascript" src="../js/plugins.js"></script>        
            <script type="text/javascript" src="../js/actions.js"></script>
            <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    

            <!-- END TEMPLATE -->   
            <script>
                // Disable back button
                history.pushState(null, document.title, location.href);
                window.addEventListener('popstate', function (event)
                {
                    history.pushState(null, document.title, location.href);
                });
                
                //disble automatic table sorting
            $('.table').dataTable({  
                "dom": '<"toolbar">rt<"toolbar">',
                "order": [],
                "bPaginate": false
            });
            </script>
    </body>
</html>
