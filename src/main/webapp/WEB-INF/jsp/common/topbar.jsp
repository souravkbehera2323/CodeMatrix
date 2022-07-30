<style>
    label.titleId {  
         /*position: fixed;*/
        /*font-size: 15px;*/
        font-size: 2.2vmin;
    }
</style>

<!-- START X-NAVIGATION VERTICAL -->
<ul class="x-navigation x-navigation-horizontal x-navigation-panel">
    <!-- TOGGLE NAVIGATION -->
    <li class="xn-icon-button">
        <a href="#" class="x-navigation-minimize"><span class="fa fa-dedent"></span></a>
    </li>
    <li class="xn-titleText">
    <label class="titleId">
        Admin Dashboard Running CODEkor &nbsp;&nbsp;&nbsp;<span class="text-builtBy" style="font-size: 12px;">ver <spring:eval expression="@versionProperties.getProperty('major')" />.<spring:eval expression="@versionProperties.getProperty('minor')" /></span>
    </label>
</li>
<!-- SIGN OUT -->
<li class="xn-icon-button pull-right">
    <a href="#" class="mb-control" data-box="#mb-signout"><span class="fa fa-sign-out"></span></a>                        
</li> 
<!-- END SIGN OUT -->
<!-- CHANGE PASSWORD -->
<li class="xn-icon-button pull-right">
    <a href="../home/changePassword.htm" title="Change Password"><span class="fa fa-key"></span></a>
</li>
<!-- END CHANGE PASSWORD -->
<!-- END TOGGLE NAVIGATION -->
</ul>
<!-- END X-NAVIGATION VERTICAL --> 
