<!-- START PAGE SIDEBAR -->
<div class="page-sidebar toggled">
    <!-- START X-NAVIGATION -->
    <ul class="x-navigation x-navigation-minimized">
        <li class="xn-logo">
            <a href="index.htm"><div class="xn-logoImage"></div></a>
            <a href="#" class="x-navigation-control"></a>
        </li>
        <li class="xn-profile">
             <%--<a href="#" class="profile-mini"><img class="image" src="${curUser.imageDataBase64}" alt='<c:out value="${curUser.name}"/>'/></a>--%>
           <a href="#" class="profile-mini"><img class="image" src="../assets/images/users/no-image.jpg" alt='<c:out value="${curUser.name}"/>'/></a>
            <div class="profile">
                <div class="profile-image">
                     <%-- <img class="image" src="${curUser.imageDataBase64}" style="align-content: center;" alt='<c:out value="${curUser.name}"/>'/>--%>
                   <img class="image" src="../assets/images/users/no-image.jpg" style="align-content: center;" alt='<c:out value="${curUser.name}"/>'/>
                </div>
                <div class="profile-data">
                    <div class="profile-data-name"><c:out value="${curUser.name}"/></div>
                </div>
            </div>
        </li>
        <!--<li class="xn-title">Navigation</li>-->
        <li class="active">
            <a href="../home/index.htm"><span class="fa fa-home"></span> <span class="xn-text">Home</span></a>
        </li>
        <sec:authorize access="hasAnyRole('ROLE_BF_RELOAD_APP_LAYER,ROLE_BF_CREATE_USER,ROLE_BF_LIST_USER')">
            <li class="xn-openable">
                <a href="#" title="Users"><span class="fa fa-users"></span> <span class="xn-text">Users</span></a>
                <ul>
                    <li><a href="../admin/userAdd.htm"><span class="fa fa-user"></span> Create User</a></li>
                    <li><a href="../admin/userList.htm"><span class="fa fa-list-alt"></span> List User</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_BF_TEST_RESULT,ROLE_BF_CORONAVIRUS,ROLE_BF_CORONAVIRUS_INSIGHTS,ROLE_BF_QUESTION_SET_COMPLETED,
                       ROLE_BF_QUESTION_SET_PENDING,ROLE_BF_CODECORE')">
            <li class="xn-openable">
                <a href="#" title="Test Results"><span class="fa fa-rebel"></span> <span class="xn-text">View</span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_QUESTION_SET_COMPLETED')">
                        <li><a href="../testResults/questionsSetCompleted.htm"><span class="fa fa-check-square-o"></span>Question Set Completed</a></li>                        
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CORONAVIRUS')">
                        <li><a href="../testResults/coronavirus.htm"><span class="fa fa-list-alt"></span>Coronavirus</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CORONAVIRUS_INSIGHTS')">
                        <li><a href="../testResults/coronavirusInsights.htm"><span class="fa fa-list-alt"></span>Coronavirus Insights</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CODECORE')">
                        <li><a href="../testResults/codecore.htm"><span class="fa fa-bar-chart-o"></span>CODEcore</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_TEST_RESULT')">
                        <li><a href="../testResults/view.htm"><span class="fa fa-won"></span>View Results</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_QUESTION_SET_PENDING')">
                        <li><a href="../testResults/questionsSetPending.htm"><span class="fa fa-square-o"></span>Question Set Not Completed</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_QUESTIONS')">
                        <li><a href="../takers/noOfQuestionAttemptedByTaker.htm"><span class="fa fa-square-o"></span>No of Question Attempted By Taker</a></li>
                    </sec:authorize>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_BF_RELOAD_APP_LAYER,ROLE_BF_CREATE_QUESTIONS')">
            <li class="xn-openable">
                <a href="#" title="Question"><span class="fa fa-question-circle"></span> <span class="xn-text">Question</span></a>
                <ul>
                    <li><a href="../question/questionAdd.htm"><span class="fa fa-plus"></span> Add Question</a></li>
                    <li><a href="../question/listQuestion.htm"><span class="fa fa-list-alt"></span> List Question</a></li>
                </ul>
            </li>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('ROLE_BF_ADD_QUESTION_OPTIONS','ROLE_BF_LIST_QUESTION_OPTIONS')">
            <li class="xn-openable">
                <a href="#" title="Question Options"><span class="fa fa-adn"></span> <span class="xn-text">Question Options</span></a>
                <ul>
                    <li><a href="../questionOptions/addQuestionOptions.htm"><span class="fa fa-user"></span>Create Question Options</a></li>
                    <li><a href="../questionOptions/listQuestionOptions.htm"><span class="fa fa-list-alt"></span>List Question Options</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_BF_ADD_QUESTION_OPTIONS','ROLE_BF_LIST_QUESTION_OPTIONS')">
            <li class="xn-openable">
                <a href="../report/wisdomTakerList.htm?takerId=${u.taker.takerId}" title="Export Wisdom Taker List"><span class="fa fa-adn"></span> <span class="xn-text"> Wisdom Takers</span></a>
            </li>
        </sec:authorize>
    </ul>
    <!-- END X-NAVIGATION -->
</div>
<!-- END PAGE SIDEBAR -->