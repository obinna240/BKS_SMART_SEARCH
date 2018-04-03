<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


		
		
		
<div class="navbar navbar-inverse">
	<div class="navbar-inner">
        <div class="navbar-bg"></div>
		<div class="container pos-rel">
			<s:url var="Logo" value="http://www.publicconsultinggroup.co.uk" /> <%-- Back to Bucks cc website --%>
			
		  	<%--  <div class="logo"><a style="display: block; height: 86px;" href="${Logo}"></a></div> --%>
		    <div class="logo" style="display: block; height: 26px;"></div>
		    <s:url var="img_src" value="/css/images/assist_logo.png" />
		    
		    <div class="logo-title"><a href="${home}"><img src="${img_src}" title="Assist Logo - Click to go to homepage" alt="Assist Logo - Click to go to homepage"></a></div>
		    
		    <div class="main-search">
		    	<s:url var="searchLanding" value="/" />
		    	<ul>
		    		<li><a href="${searchLanding}">Home</a></li>
		    		<li><a href="">profile</a></li>
		    		<li><a href="">Login</a></li>
		    	</ul>
		   </div><!--main-search-->
		</div>
	</div><!--navbar-inner-->
</div>

<%--
<div id="nav">
	<div class="container">
		<div class="sixteen columns">
			<sf:form action="/parlsearcher/docReport" method="GET" >
				<label>Retrieve report for</label>
				<input type="text" name="date" placeholder="yyyy-mm-dd">
				<input type="submit" value="Submit" >	
			</sf:form>
		</div>
		
		<div class="four columns" style="display: none;">
			<form action="/parlsearcher/searchAnythingGeneric" method="GET" >
				<label>Search for anything (G)</label><input type="text" name="searchAnythingGeneric" placeholder="search for anything">
				<input type="submit" value="Submit" >	
			</form>
		</div>
	</div>
</div>
<div id="adv-search">
	<div class="container">
		<div class="sixteen columns">
		<c:choose>
			<c:when test="${not empty docReport.previousDocReportDate}">
				<s:url var="prevSearch" value="/docReport/${docReport.previousDocReportDate}" /><a href="${prevSearch}" class="adv-search">Get Previous Report</a>
			</c:when>
			<c:otherwise>
				Get Previous Report
			</c:otherwise>
		</c:choose>
		<c:url var="advSearch" value="/advanced" /><a href="${advSearch}" class="adv-search">Try advanced search</a>
		<c:choose>
			<c:when test="${not empty docReport.nextDocReportDate}">
				<s:url var="nextSearch" value="/docReport/${docReport.nextDocReportDate}" /><a href="${nextSearch}" class="adv-search">Get Next Report</a>
			</c:when>
			<c:otherwise>
				Get next Report
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>
--%>