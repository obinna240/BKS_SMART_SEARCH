<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="basicForm" value="${basicForm}" />

<div class="nav-bg mar-bot2">
	<div class="container">
		<div class="search-bar-container">
			<s:url var="basicSearchFormLink" value="/search/basic" />
			<sf:form class="form-inline" role="form" modelAttribute="basicForm" name="form" action="${basicSearchFormLink}" method="GET">
			
				<%-- 
				<div class="col span_2_of_12">
					<select>
						<option value="AllBucks">All Bucks</option>
						<option value="National">National</option>
						<option value="otherLocalAuthorities">Other Local Authorities</option>
									
					</select>
				</div>
				--%>
				<%--
				<div class="col span_2_of_12">
				<div class="btn-group top-nav">
				  <button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    Search all Documents<span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
					    <li><strong>National Documents</strong></li>
					    <li><strong>Local Documents</strong></li>
				  </ul>
				</div>
				</div>
				--%>
				<div class="col span_9_of_12"><%--<div class="col span_7_of_12">--%>
					
		           	 <div class="form-group">         
					    <sf:input id="my-input" name="basicquery" type="text" value="${basicForm.basicquery}" path="basicquery" placeholder="Search Anything" />
					    			     							
		 				<sf:input type="hidden" id="basicqueryType" name="basicqueryType" path="basicqueryType" value="${basicForm.basicqueryType}" />
		 				<sf:input type="hidden" id="pValue" name="pValue" path="pValue" />
		 				
					</div>
				</div>
				<div class="col span_1_of_12">
					<sf:button class="submit-btn">Search</sf:button>
				</div>
				<div class="col span_2_of_12">		
					<div class="checkbox" style="display: none">
		             	<label>Sort by Date
		                   	<sf:checkbox name="basicsortBy" id="basicsortBy" value="${basicform.basicsortBy}"  path="basicsortBy" />
						</label>           	
		 	                 		    
		             </div>
		             
					<s:url var="advanced" value="/advanced" />
					<s:url var="alerts" value="/alerts" />
					<a href="${advanced}">Advanced</a>
					<a href="${alerts}">Alerts</a>
			
			     </div>
	
			</sf:form>	
			
			
			
		</div>
	</div>
</div>

<script>
function checkSortBy()
{
	var val;
	var sortBool = document.getElementById("basicsortBy").checked;
	if(sortBool == true)
		{
			val = document.getElementById("basicsortBy");
			val.value = true;
		}
	else
		{
			val = document.getElementById("basicsortBy");
			val.value = false;
		}
	return val;
}
</script>

<script>
$(document).ready(function()
	{
		$sortByVal = '${basicform.basicsortBy}';
		if($sortByVal == true)
			{
				$('#basicsortBy').prop('checked',true);
			}
		else
			{
				$('#basicsortBy').prop('checked',false);
			}
	});
</script>



