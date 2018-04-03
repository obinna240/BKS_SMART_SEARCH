<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="numFound" value="${searchResultBean.numFound}" />
<c:set var="paginatorBean" value="${searchResultBean.paginatorBean}" />
<c:set var="alternativeResults" value="${searchResultBean.alternativeResults}" />
<c:set var="authorsFacet" value="${searchResultBean.authors}" />
<c:set var="asscUrlsFacet" value="${searchResultBean.asscUrls}" />
<c:set var="ownerFacet" value="${searchResultBean.owner}" />

<c:set var="docGroupFacet" value="${searchResultBean.docGroup}" />
<c:set var="listOfResultBean" value="${searchResultBean.listOfResultBean}" />
<c:set var="tempUrlsString" value="${searchResultBean.tempUrlsString}" />

<c:set var="containsResult" value="${searchResultBean.containsResult}" />
	
		
		<div class="col-md-3">
				
					
					
				
					<c:if test="${containsResult }">	
						
						
 					<div class="panel panel-default">
 						<div class="panel-heading" style="color:#fff;background-color: purple;">
 							<strong>
 								<span>Filter Results</span>
 							</strong>
 						</div>
 					</div>
					        
							
		
					
					
					</c:if>
				
				<c:if test="${containsResult }">		
				<div class="panel panel-default">
					<div class="js-collapse-head panel-heading pos-rel"><strong>Categories</strong><i class="arrow fa fa-caret-down"></i></div>
					
					<ul class="list-group" >
						
											
						<%-- 
						<c:choose>
							<c:when test="${not empty docGroupFacet}">
								<c:forEach var="dgrp" items="${docGroupFacet}" varStatus="loop">
									
									<li class="list-group-item" >${dgrp.key } - ${dgrp.value } </li>
									
								</c:forEach>
							</c:when>
							
						</c:choose>
						--%>
						
						<c:choose>
							<c:when test="${not empty searchResultBean.gTheCareAct}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">The Care Act</s:param>
									</s:url>
									
									<a href="${searchFilter}"> <strong>The Care Act</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o" disabled="disabled" ></i> The Care Act</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gForm}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Forms</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Forms and Templates</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Forms and Templates</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gGuidanceNote}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Guidance Note</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Guidance Notes</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Guidance Notes</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gFactSheet}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Factsheet</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Help, Factsheets and Tips</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Help, Factsheets and Tips</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gConferences}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Conferences</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Meetings and Conferences</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Meetings and Conferences</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gNewsletters}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
										<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Newsletters</s:param>
										</s:url>
									<a href="${searchFilter}"> <strong>Newsletters and Statements</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Newsletters and Statements</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gPresentations}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Presentations</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Presentations and Slides</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Presentations and Slides</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gPolicy}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Policy</s:param>
									</s:url>
									<a href="${searchFilter}" > <strong>Policy</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Policy</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gStrategy}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Strategy</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Strategy, Frameworks and Tools</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Strategy, Frameworks and Tools</li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty searchResultBean.gOthers}">
								<li class="list-group-item" ><i class="fa fa-square-o"></i>
									<s:url var="searchFilter" value="/search/Category/1">
											<s:param name="basicquery">${basicForm.basicquery}</s:param>
											<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
											<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
											<s:param name="pValue">${pValue}</s:param>
											<s:param name="f">Others</s:param>
									</s:url>
									<a href="${searchFilter}"> <strong>Other Documents</strong></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="list-group-item" ><i class="fa fa-square-o"></i> Other Documents</li>
							</c:otherwise>
						</c:choose>

					</ul>
					

				</div>
				
				</c:if>
				
				<c:if test="${containsResult }">				
					<div class="panel panel-default">
						<div class="js-collapse-head panel-heading pos-rel"><strong>Authors</strong><i class="arrow fa fa-caret-right"></i></div>
						<ul class="js-collapse-body list-group">
							<c:choose>
								<c:when test="${not empty authorsFacet}">
									<c:forEach var="dgrp" items="${authorsFacet}" varStatus="loop">
										<li class="list-group-item"> 
											
											<s:url var="searchFilter" value="/search/Author/1">
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="pValue">${pValue}</s:param>
												<s:param name="f">${dgrp.key}</s:param>
											</s:url>
											
											<a href="${searchFilter}">${dgrp.key }</a> <%--- ${dgrp.value }  --%>
											
										</li>
									</c:forEach>
								</c:when>
								
							</c:choose>
						</ul>
					</div>
				</c:if>
				
				<c:if test="${containsResult }">
					<div class="panel panel-default">
						<div class="js-collapse-head panel-heading pos-rel"><strong>Owners</strong><i class="arrow fa fa-caret-right"></i></div>
						<ul class="js-collapse-body list-group">
							<c:choose>
								<c:when test="${not empty ownerFacet}">
									<c:forEach var="dgrp" items="${ownerFacet}" varStatus="loop">
										<li class="list-group-item">
											<s:url var="searchFilter" value="/search/Owner/1">
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="pValue">${pValue}</s:param>
												<s:param name="f">${dgrp.key}</s:param>
											</s:url>
											
											<a href="${searchFilter}">${dgrp.key }</a> <%--- ${dgrp.value } --%>
										</li>
									</c:forEach>
								</c:when>
								
							</c:choose>
						</ul>
					</div>
				</c:if>
				
				<c:if test="${containsResult }">
					<div class="panel panel-default">
						<div class="js-collapse-head panel-heading pos-rel"><strong>Important Links</strong><i class="arrow fa fa-caret-right"></i></div>
						<ul class="js-collapse-body list-group">
							<c:choose>
								<c:when test="${not empty tempUrlsString}">
									<c:forEach var="dgrp" items="${tempUrlsString}" varStatus="loop" >
										<li class="list-group-item"><a href="${dgrp.key }">${dgrp.value }</a> </li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li class="list-group-item">No Associated Urls</li>
								</c:otherwise>
								
							</c:choose>
						</ul>
					</div>
				</c:if>
				
				<c:if test="${containsResult }">	
					<br /><br />
					<s:url var="basicSearchFormLink" value="/dateSearch" />
					<div class="panel panel-default">
	 						<div class="panel-heading" style="color:#fff;background-color: purple;">
	 							<strong>
	 								<span>Date Filter</span>
	 							</strong>
	 						</div>
	 				</div>
					
					<form class="form-horizontal" name="form" action="${basicSearchFormLink}" method="GET">
						
						
						<div class="form-group">
								    <label for="inputEmail3" class="col-sm-2 control-label">From</label>
								    <div class="col-sm-10">
								      <input type="date" class="form-control" id="from" value="">
								    </div>
						</div>
						
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">To</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="to" value="">
								</div>		    
						</div>
						
						<div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-sm btn-info">Submit</button>
						    </div>
	  					</div>		
					</form>
				</c:if>
						
					
						
		

				
		</div>
	


					
					



















<%--<ul>
					<c:choose>
						<c:when test="${not empty asscUrlsFacet}">
							<c:forEach var="dgrp" items="${asscUrlsFacet}" varStatus="loop" >
								<li><a href="${dgrp.key }">${dgrp.key }</a> - ${loop.index} ${dgrp.value } </li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li>No Associated Urls</li>
						</c:otherwise>
						
					</c:choose>
				</ul> --%>