<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->

<head>

	<meta charset="utf-8">
	
	<title>POLICY SEARCH</title>
	
	<meta name="description" content="Bucks_ss">
	<meta name="author" content="PCG">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
	
	<spring:url var="js_smartsuggest" value="/js/jquery.smartsuggest.js" /><script src="${js_smartsuggest}"></script>
	<spring:url var="smartsuggestcss" value="/css/smartsuggest.css" /><link rel="stylesheet" href="${smartsuggestcss}">

	
	<!-- Bootsrap social -->
	<spring:url var="bootstrapsocialcss" value="/css/bootstrap/bootstrap-social.css" /><link rel="stylesheet" href="${bootstrapsocialcss}" />
	
	<!-- Bootstrap -->
	<spring:url var="js_bootstrap" value="/js/bootstrap/bootstrap.min.js" /><script src="${js_bootstrap}"></script>
	<spring:url var="bootstrapcss" value="/css/bootstrap/bootstrap.css" /><link rel="stylesheet" href="${bootstrapcss}" />
	
	<!-- Font Awesome -->
	<spring:url var="fontawesomecss" value="/css/fontawesome.min.css" /><link rel="stylesheet" href="${fontawesomecss}" />
	
	<spring:url var="jqueryExpander" value="/js/jquery.expander.js" /><script src="${jqueryExpander}"></script>
	 
</head>

<body>	
	<tiles:insertAttribute name="nav"  />  
	<tiles:insertAttribute name="header" />
	
	<div class="container">
		<div class="row">
	
			<tiles:insertAttribute name="leftNav" ignore="true" />
		    <tiles:insertAttribute name="body" ignore="true" /> 
			<tiles:insertAttribute name="footer" />
			
		</div>
	</div>
		
	<c:url var="ssData" value="/smartSuggest" /> 
	
	<script>
	$(function() {
  		$('#my-input').smartSuggest({
    	src: '${ssData}'
  		})
  	});
	</script>
	
	<script>
	$(function() {
  		$('.contentBody').expander({
    	slicePoint: 190
  		})
  	});
	</script>
	<spring:url var="app_js" value="/js/app.js" /><script type="text/javascript" src="${app_js}"></script>
</body>
</html>