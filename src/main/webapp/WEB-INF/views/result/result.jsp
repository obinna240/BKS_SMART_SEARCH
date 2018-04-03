<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="numFound" value="${searchResultBean.numFound}" />
<c:set var="paginatorBean" value="${searchResultBean.paginatorBean}" />
<c:set var="alternativeResults" value="${searchResultBean.alternativeResults}" />

<c:set var="listOfResultBean" value="${searchResultBean.listOfResultBean}" />

<c:set var="containsResult" value="${searchResultBean.containsResult}" />
<c:set var="altlistOfResultBean" value="${searchResultBean.altlistOfResultBean}" />
<c:set var="docTypeFacet" value="${searchResultBean.docType}" />

<c:set var="listOfImages" value="${searchResultBean.listOfImageObjects}" />



		<div class="col-md-9">
		
				<c:choose>
					<c:when test="${containsResult }">
					
						<div class="row filters-container">
						
							<s:url var="basicSearchFormLink" value="/search/basic" />
 							
 							<div class="col span_3_of_12">
		 					    <form  action="${basicSearchFormLink}" method="GET">
							                 
								    <input type="hidden" value="${basicForm.basicquery}" name="basicquery" />
								    			     							
					 				<input type="hidden"  name="basicqueryType" value="${basicForm.basicqueryType}" />
					 				<input type="hidden"  name="pValue" />
					 				<input type="checkbox" hidden value="${basicform.basicsortBy}" name="basicsortBy" />
									
									<button class="btn btn-info block-center"><strong>Clear All Filters</strong></button>
							        		
								</form>
							</div>
							
							<div class="col span_3_of_12">
								
								<div class="btn-group ">
								  <button type="button" class="btn btn-sm" disabled="disabled"><strong>Select Content Type</strong></button>
								  <button type="button" class="btn btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    <span class="caret"></span>
								    <span class="sr-only">Toggle Dropdown</span>
								  </button>
								  <ul class="dropdown-menu">
								  	<c:choose>
								  		<c:when test="${not empty docTypeFacet}">
								  			<c:forEach var="dgrp" items="${docTypeFacet}" varStatus="loop">
								  				<s:url var="searchFilter" value="/search/Availability/ex/2"> 
													<s:param name="basicquery">${basicForm.basicquery}</s:param>
													<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
													<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
													<s:param name="pValue">${pValue}</s:param>
													<s:param name="availabilityFilter">${dgrp.key}</s:param>
													
													<c:if test="${not empty filterType}">
														<s:param name="filterType">${filterType}</s:param>
														<s:param name="filterValue">${filterValue}</s:param>
													</c:if>
													
												</s:url>
												<li><a href="${searchFilter}">
													<c:choose>
														<c:when test="${dgrp.key == 'CareAct_Directive'}">
															<strong><c:out value="CA Directive" /></strong>
															<span class="pull-right" data-toggle="tooltip" data-placement="left" title="Show only Care Act Directives">
																<i style="font-size:20px"><strong>i</strong></i> 
															</span>
														</c:when>
														<c:when test="${dgrp.key == 'CareAct_Summary'}">
															<strong><c:out value="CA Summary" /></strong>
															<span class="pull-right" data-toggle="tooltip" data-placement="left" title="Show only Care Act Summaries">
																<i style="font-size:20px"><strong>i</strong></i> 
															</span>
														</c:when>
														<c:when test="${dgrp.key == 'FullBody'}">
															<strong>Whole doc</strong>
															<span class="pull-right" data-toggle="tooltip" data-placement="left" title="Show the entire content of the text found in the search">
																<i style="font-size:20px"><strong>i</strong></i> 
															</span>
														</c:when>
														<c:otherwise>
															<strong>${dgrp.key}</strong>
																<c:choose>
																	<c:when test="${dgrp.key == 'Hints'}">
																		<span class="pull-right" data-toggle="tooltip" data-placement="left" title="Show only Question/Answer(s) found in the search">
																			<i style="font-size:20px"><strong>i</strong></i> 
																		</span>
																	</c:when>
																	<c:when test="${dgrp.key == 'Topic'}">
																		<span class="pull-right" data-toggle="tooltip" data-placement="left" title="Show only Subtopics found in the search">
																			<i style="font-size:20px"><strong>i</strong></i> 
																		</span>
																	</c:when>
																	
																</c:choose>
															
														</c:otherwise>
													</c:choose>
												</a></li>
											   
											    
											    
								  			</c:forEach>
								  		</c:when>
								  	</c:choose>
								    
								  </ul>
								</div>
								
								<%--
								<select class="" id="dynamicSelect">
									<option><strong>Text Availability</strong></option>
									<c:choose>
										<c:when test="${not empty docTypeFacet}">
											<c:forEach var="dgrp" items="${docTypeFacet}" varStatus="loop">
												<s:url var="searchFilter" value="/search/Availability/ex/2"> 
													<s:param name="basicquery">${basicForm.basicquery}</s:param>
													<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
													<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
													<s:param name="pValue">${pValue}</s:param>
													<s:param name="availabilityFilter">${dgrp.key}</s:param>
													
													<c:if test="${not empty filterType}">
														<s:param name="filterType">${filterType}</s:param>
														<s:param name="filterValue">${filterValue}</s:param>
													</c:if>
													
												</s:url>
												<option value="${searchFilter}">
													<c:choose>
														<c:when test="${dgrp.key == 'CareAct_Directive'}">
															<c:out value="Care Act Directive" />
														</c:when>
														<c:when test="${dgrp.key == 'CareAct_Summary'}">
															<c:out value="Care Act Summary" />
														</c:when>
														<c:otherwise>
															${dgrp.key}
														</c:otherwise>
													</c:choose>
													
												</option> 
											</c:forEach>
										</c:when>
									</c:choose>
								
								</select> --%>
							</div> 
							
							<%-- 
							<span class="col-md-4 text-center">
								<strong>Sort By</strong>
								<select class="">
									<option>Most Relevant</option>
									<option>By Date</option>
								</select>
							</span> 
							--%>
							
							<div class="col span_3_of_12">
								<strong>Summary</strong>
								<select class="Summary">
									<option value="ShowAll">Default</option>
									<option value="PartOf">Part of</option>
									<option value="otherTitles">Other titles</option>
									<option value="Owner">Owner</option>
									<option value="chatperTitle">Chapter titles</option>
									<option value="isType">Type</option>
								</select>
							</div>
							
							<div class="col span_3_of_12">
								<a href="" class="btn btn-info block-center pull-right"><strong>Bookmark search</strong></a>
							</div>
							
						</div>
						
						<c:set var="ftype" value="" />
						
						<div class="row results-notifications">
							<span>
								Results found for <strong>'${basicForm.basicquery}'</strong> 
								<c:if test="${not empty filterType || not empty availabilityFilterType}">
									<strong>with FILTER/S</strong>
								</c:if>
								
								<c:if test="${not empty filterType}">
										<c:choose>
											<c:when test="${filterType == 'Category'}">
												<c:set var="ftype" value="Categories" />
											</c:when>
										</c:choose>
										
											
											<strong>
											
												<span style="border:2px solid black;">
														
													  ${filterValue}
													 <c:choose>
													 	<c:when test="${empty availabilityFilterType}">
													 		<s:url var="removeFilter" value="/search">
																<s:param name="basicquery">${basicForm.basicquery}</s:param>
																<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
																<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
															</s:url>
															<a href="${removeFilter}" class="btn"><span style="color:red;font-size:21px;">&times;</span></a>
													 	</c:when>
													 	<c:when test="${not empty availabilityFilterType}">
													 		<s:url var="removeFilter" value="/search/Availability/1">
																<s:param name="basicquery">${basicForm.basicquery}</s:param>
																<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
																<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
																<s:param name="f">${availabilityFilterValue}</s:param>
															</s:url>
															<a href="${removeFilter}" class="btn"><span style="color:red;font-size:21px;">&times;</span></a>
													 	</c:when>
													  	
													</c:choose>
												</span>
												
											</strong>
											
									
										
								</c:if>
									
								<c:if test="${not empty availabilityFilterType}">
										
										&nbsp;
										<strong>
											
												<span style="border:2px solid black;">
														
													  ${availabilityFilterValue} 
													<c:choose>
														<c:when test="${empty filterType}">
															<s:url var="removeFilter" value="/search">
																<s:param name="basicquery">${basicForm.basicquery}</s:param>
																<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
																<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
															</s:url>
															<a href="${removeFilter}" class="btn"><span style="color:red;font-size:21px;">&times;</span></a>
														</c:when>
														<c:when test="${not empty filterType}">
															<s:url var="removeFilter" value="/search/${filterType}/1">
																<s:param name="basicquery">${basicForm.basicquery}</s:param>
																<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
																<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
																<s:param name="f">${filterValue}</s:param>
															</s:url>
															<a href="${removeFilter}" class="btn"><span style="color:red;font-size:21px;">&times;</span></a>
														</c:when>
													</c:choose>
												</span>
										</strong>
								</c:if>
								
							</span> 
							<span class="pull-right"><strong>${numFound} results found</strong>
								
								<c:if test="${not empty listOfImages}">
									&nbsp;&nbsp;<a class="block-center" href="" data-toggle="modal" data-target="#myModal"><span style="border-style:solid;border-color:#0000ff"><strong>See Images</strong></span></a>
								</c:if>
								
							</span>
						</div>
						
						<c:choose>
							<c:when test="${not empty listOfResultBean}">

								<c:forEach var="dgrp" items="${listOfResultBean}" varStatus="loop">
								<div class="row">
									<div class="search-result-item">
										

										
											<div class="col-md-2 js-full-height doc-type-sidebar">
												<p class="document-group">${dgrp.docGroup} </p>
												
												<%--
												<p class="isType">
												
													<c:choose>
															<c:when test="${dgrp.docType == 'CareAct_Directive'}">
																<c:out value="Care Act Directive" />
															</c:when>
															<c:when test="${dgrp.docType == 'CareAct_Summary'}">
																<c:out value="Care Act Summary" />
															</c:when>
															<c:when test="${dgrp.docType == 'CareAct_Schedule'}">
																<c:out value="Care Act Schedule" />
															</c:when>
															<c:otherwise>
																${dgrp.docType}
															</c:otherwise>
														</c:choose>
												</p>
												 --%>
											</div>
											
											<div class="col-md-10">
												<h2>
													<c:if test="${not empty dgrp.title}">
														<c:forEach var="dgrps" items="${dgrp.title}" varStatus="loop">
															${dgrps} 
														</c:forEach>
													</c:if>
												</h2>
												
												
												<p>	
													<span class="Owner"><strong>Owner</strong> - ${dgrp.owner} </span>
												</p>
												
												
												
												<c:if test="${not empty dgrp.body}">
													<p class="contentBody"><strong>Content</strong> - ${dgrp.body} </p>
												</c:if>
				
												<c:if test="${not empty dgrp.listOfTitles}">
													<p><strong>List of Summaries</strong></p>
													<ul>
														
														<c:forEach var="dgrps" items="${dgrp.listOfTitles}" varStatus="loop">
															
															<s:url var="searchSummary" value="/search/AAvailability/3">
															
																<s:param name="basicquery">${dgrps}</s:param>
																<s:param name="basicqueryType">CareAct_Directive</s:param>
																<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
																<s:param name="f">${dgrp.docType2}</s:param>
																
															</s:url>
															
															<li><p><a href="${searchSummary}">${dgrps}</a></p></li>
															
														</c:forEach>
														
													</ul>
												</c:if>
												
												<%-- 
												<c:if test="${not empty dgrp.associatedUrls}">
													<p><strong>Related Urls</strong></p>
													<ul>
														
														<c:forEach var="dgrps" items="${dgrp.associatedUrls}" varStatus="loop">
														
															<li><p><a href="">  ${dgrps} </a></p></li>
														</c:forEach>
														
													</ul>
												</c:if>
												--%>
												
												<c:if test="${not empty dgrp.other_title}">
													<p class="otherTitles"><strong>OTHER TITLES</strong></p>
													<ul>
														
														<c:forEach var="dgrps" items="${dgrp.other_title}" varStatus="loop">
														
															<li><p><a href="">  ${dgrps} </a></p></li>
														</c:forEach>
														
													</ul>
												</c:if>
			
												<c:if test="${not empty dgrp.chapterTitle}">
													<p class="chatperTitle"><strong>CHAPTER</strong></p>
													<ul>
														
														<c:forEach var="dgrps" items="${dgrp.chapterTitle}" varStatus="loop">
														
															<li><p><a href="">  ${dgrps} </a></p></li>
														</c:forEach>
														
													</ul>
												</c:if>
										
												<c:if test="${not empty dgrp.partTitle}">
													<p class="PartOf"><strong>PART</strong></p>
													<ul>
														
														<c:forEach var="dgrps" items="${dgrp.partTitle}" varStatus="loop">
														
															<li><p>  ${dgrps}</p></li>
														</c:forEach>
														
													</ul>
												</c:if>
										
												<c:if test="${not empty dgrp.partOfDocTitle}">
													<p class="otherTitles"><span><strong>Other Doc Titles -</strong>
													
														
														<c:forEach var="dgrps" items="${dgrp.partOfDocTitle}" varStatus="loop">
														
															[${dgrps}] 
														</c:forEach>
														
													</span></p>
												</c:if>
												
												<!--
												<c:if test="${not empty dgrp.partOfDocBody}">
													<div class="PartOf">
																										
														<p class="contentBody"><strong>PART OF </strong>${dgrp.partOfDocBody}</p>
													</div>									
												</c:if>
												-->
												<p class="isType">
													<strong>Is Type</strong> - 
													<c:choose>
															<c:when test="${dgrp.docType == 'CareAct_Directive'}">
																<c:out value="Care Act Directive" />
															</c:when>
															<c:when test="${dgrp.docType == 'CareAct_Summary'}">
																<c:out value="Care Act Summary" />
															</c:when>
															<c:when test="${dgrp.docType == 'CareAct_Schedule'}">
																<c:out value="Care Act Schedule" />
															</c:when>
															<c:otherwise>
																${dgrp.docType}
															</c:otherwise>
														</c:choose>
												</p>
												
												<c:if test="${not empty dgrp.docType2}">
													<p class="isType">
														<strong>Is Type</strong> - 
														
														<c:choose>
															<c:when test="${dgrp.docType2 == 'CareAct_Directive'}">
																<c:out value="Care Act Directive" />
															</c:when>
															<c:when test="${dgrp.docType2 == 'CareAct_Summary'}">
																<c:out value="Care Act Summary" />
															</c:when>
															<c:when test="${dgrp.docType2 == 'CareAct_Schedule'}">
																<c:out value="Care Act Schedule" />
															</c:when>
															<c:otherwise>
																${dgrp.docType2}
															</c:otherwise>
														</c:choose>
														
													</p>
												</c:if>
												
												
												<c:if test="${not empty dgrp.version}">
													<p><strong>Version - </strong><c:out value="${dgrp.version}" /></p>
												</c:if>
												<c:if test="${not empty dgrp.dateOfPublication}">
													<p><strong>Date of Publication - </strong><c:out value="${dgrp.dateOfPublication}" /></p>
												</c:if>
												
												
												<c:choose>
													<c:when test="${dgrp.owner != 'UK-GOV'}">
														<s:url var="dUrl" value="/fileDownload">
															<s:param name="documentName">${dgrp.documentUrl}</s:param>
														</s:url>
														<span class="pull-left mar-bot1"><strong><a class="btn btn-sm btn-primary" role="button" href="${dUrl}">Download document</a></strong></span>
														<!--<span class="pull-right"><a href="${dUrl}">${dgrp.documentUrl}</a> </span>-->
													</c:when>
													<c:when test="${dgrp.owner eq 'UK-GOV'}">
														<s:url var="dUrl" value="${dgrp.documentUrl}" />
														<span class="pull-left mar-bot1"><strong><a class="btn btn-sm btn-primary" role="button"  href="${dUrl}">Download document</a></strong></span>
														<!--<span class="pull-right"><a class="pull-right" href="${dUrl}">${dgrp.documentUrl}</a> </span>-->
													</c:when>
													<c:otherwise>
														
													</c:otherwise>
												</c:choose>

											</div>
										

											
									</div>
								</div>
								</c:forEach>

						</c:when>
						<c:otherwise>
							
						</c:otherwise>
						
					</c:choose>
					
					<!-- More results -->
					<c:choose>
							<c:when test="${ alternativeResults gt 0}">
							
								<div class="row other-suggestions">
									<strong class="pull-left">Other Suggestions</strong>
									<span class="pull-right"><c:out value="${alternativeResults} results found" /></span>
								</div>
								<c:forEach var="dgrp" items="${altlistOfResultBean}" varStatus="loop" >
									<c:choose>
											<c:when test="${dgrp.owner != 'UK-GOV'}">
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${dgrp.documentUrl}</s:param>
												</s:url>
												<p><strong>download document</strong> - <a href="${dUrl}">${dgrp.documentUrl}</a> </p>
											</c:when>
											<c:when test="${dgrp.owner eq 'UK-GOV'}">
												<s:url var="dUrl" value="${dgrp.documentUrl}" />
												<p><strong>download document</strong> - <a href="${dUrl}">${dgrp.documentUrl}</a> </p>
											</c:when>
											<c:otherwise>
												
											</c:otherwise>
										</c:choose>
								</c:forEach>
								
								<c:if test="${alternativeResults gt 5}">
									<s:url var="seeMore" value="/search">
										<s:param name="basicquery">${basicForm.basicquery}</s:param>
									
										<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>	
									</s:url>
									<a href="${seeMore}">See All Results <i class="fa fa-angle-double-right"></i></a>
								</c:if>
								
							</c:when>
							
							<c:otherwise>
								
							</c:otherwise>
					</c:choose>
						
					
			
					<c:set var="pager" value="${paginatorBean}" />
					<c:if test="${pager.totalPages > 0}">
						<div class="dr-pagination pull-right">
							<ul class="pagination pagination-sm">
								<c:choose>
									<c:when test="${paginatorType eq 0 }">
										<c:if test="${pager.page gt 1 }">
											<s:url var="previous" value="/search" >
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${qt}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="pValue">${pager.page-1}</s:param>
												
											</s:url>
											<li style="padding-right:1px">
											
												
												<a href="${previous}" >&laquo;</a>
											</li>
										</c:if>
										
										<c:forEach var="p" begin="${pager.firstPage}" end="${pager.lastPage}" step="1" varStatus="pStatus">
											<s:url var="curr" value="/search"><!-- /retrieve/search" -->
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${qt}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="pValue">${p}</s:param>
																					
											</s:url>
											  <c:choose>
												  <c:when test="${p eq pager.page}">
													<li class="active" style="padding-right:1px"><a href="${curr}">${p}</a></li>
												  </c:when>
												  <c:otherwise>
													<li style="padding-right:1px">
														<a href="${curr}" class="">${p}</a>
														
													</li>
												  </c:otherwise>
											  </c:choose>
										 
										</c:forEach>
										
										<c:if test="${pager.page ne pager.totalPages}">
											<s:url var="next" value="/search"> 
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${qt}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="pValue">${pager.page+1}</s:param>
												
											</s:url>
											<li style="padding-right:1px"><a href="${next}">&raquo;</a></li>
										</c:if>
										
									</c:when>
									<c:when test="${paginatorType eq 1 }">
										<c:if test="${pager.page gt 1 }">
											<s:url var="previous" value="/search/${substitute}/1" >
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="f">${f}</s:param>
												<s:param name="pValue">${pager.page-1}</s:param>
												
											</s:url>
											<li style="padding-right:1px">
											
												
												<a href="${previous}" >&laquo;</a>
											</li>
										</c:if>
										
										<c:forEach var="p" begin="${pager.firstPage}" end="${pager.lastPage}" step="1" varStatus="pStatus">
											<s:url var="curr" value="/search/${substitute}/1"><!-- /retrieve/search" -->
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="f">${f}</s:param>
												<s:param name="pValue">${p}</s:param>
																					
											</s:url>
											  <c:choose>
												  <c:when test="${p eq pager.page}">
													<li class="active" style="padding-right:1px"><a href="${curr}">${p}</a></li>
												  </c:when>
												  <c:otherwise>
													<li style="padding-right:1px">
														<a href="${curr}" class="">${p}</a>
														
													</li>
												  </c:otherwise>
											  </c:choose>
										 
										</c:forEach>
										
										<c:if test="${pager.page ne pager.totalPages}">
											<s:url var="next" value="/search/${substitute}/1"> 
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="f">${f}</s:param>
												<s:param name="pValue">${pager.page+1}</s:param>
												
											</s:url>
											<li style="padding-right:1px"><a href="${next}">&raquo;</a></li>
										</c:if>
										
									</c:when>
									
									<c:when test="${paginatorType eq 2 }">
										<c:if test="${pager.page gt 1 }">
											<s:url var="previous" value="/search/Availability/ex/2" >
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="availabilityFilter">${availabilityFilterValue}</s:param>
												<s:param name="pValue">${pager.page-1}</s:param>
												<c:if test="${not empty filterType}">
													<s:param name="filterType">${filterType}</s:param>
													<s:param name="filterValue">${filterValue}</s:param>
												</c:if>
												
											</s:url>
											<li style="padding-right:1px">
											
												
												<a href="${previous}" >&laquo;</a>
											</li>
										</c:if>
										
										<c:forEach var="p" begin="${pager.firstPage}" end="${pager.lastPage}" step="1" varStatus="pStatus">
											<s:url var="curr" value="/search/Availability/ex/2" ><!-- /retrieve/search" --><!-- value="/search/${substitute}/1"> -->
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="availabilityFilter">${availabilityFilterValue}</s:param>
												<s:param name="pValue">${p}</s:param>
												<c:if test="${not empty filterType}">
													<s:param name="filterType">${filterType}</s:param>
													<s:param name="filterValue">${filterValue}</s:param>
												</c:if>									
											</s:url>
											  <c:choose>
												  <c:when test="${p eq pager.page}">
													<li class="active" style="padding-right:1px"><a href="${curr}">${p}</a></li>
												  </c:when>
												  <c:otherwise>
													<li style="padding-right:1px">
														<a href="${curr}" class="">${p}</a>
														
													</li>
												  </c:otherwise>
											  </c:choose>
										 
										</c:forEach>
										
										<c:if test="${pager.page ne pager.totalPages}">
											<s:url var="next" value="/search/Availability/ex/2" > 
												<s:param name="basicquery">${basicForm.basicquery}</s:param>
												<s:param name="basicqueryType">${basicForm.basicqueryType}</s:param>
												<s:param name="basicsortby">${basicForm.basicsortBy}</s:param>
												<s:param name="availabilityFilter">${availabilityFilterValue}</s:param>
												<s:param name="pValue">${pager.page+1}</s:param>
												<c:if test="${not empty filterType}">
													<s:param name="filterType">${filterType}</s:param>
													<s:param name="filterValue">${filterValue}</s:param>
												</c:if>	
											</s:url>
											<li style="padding-right:1px"><a href="${next}">&raquo;</a></li>
										</c:if>
										
									</c:when>

									<c:otherwise></c:otherwise>
								</c:choose>
							</ul>
						</div>
					</c:if>
					
			</c:when>
			<c:otherwise>
				<p style="margin-bottom: 570px"><strong>There are no results to show</strong></p>
			</c:otherwise>
		</c:choose>
		</div>

		
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Images found in documents for '${basicForm.basicquery}'</h4>
      </div>
      <div class="modal-body">
      	   	    	
       		
       			<div class="row">
       				<c:forEach items="${listOfImages}" var="imageItem" begin="" varStatus="count">
				  <div class="col-xs-6 col-md-3">
				    <div  class="thumbnail" style="border: transparent">
				  		<a class="btn btn-lg imgClass" tabindex="0" role="button" id="img_${count.index}">
				      		<img src="data:image/jpeg;base64,${imageItem.imageBinary}"  height="3850" width="3850" alt="Image Category - ${imageItem.docCategory}, Owner - ${imageItem.owner}, Author - ${imageItem.authors}">
				      	</a>
				     
				      	<div class="caption">
				      		<p class="hidePara" id="showPara_${count.index}" style="position:static"><small>${imageItem.docTitle}</small></p>
				        	<%--<p><small>${imageItem.docTitle} <br /><em>${imageItem.authors}, ${imageItem.owner}</em></small></p> --%>
				        	<%--<hr />
						      <p>Category - ${imageItem.docCategory}</p>
						       <p>Owner - ${imageItem.owner}</p>
						       <p>Author - ${imageItem.authors}</p> --%>
						</div>
				    </div>
				  </div>
				  </c:forEach>
				 </div>
       		
       	
	
				
				
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <%--button type="button" class="btn btn-primary">Save changes</button> --%>
      </div>
    </div>
  </div>
</div>

<script>	
    $(document).ready(function() {
        $('.hidePara').hide();
    });

</script>

<!-- Script -->
<script>
	$(document).ready(function(){
		$('.imgClass').mouseover(function(e)
		{
			var id = e.currentTarget.id.split('_')[1];

			
			$('#showPara_'+id).show();
	
			
		});
		$('.imgClass').mouseout(function(e)
		{
			var id = e.currentTarget.id.split('_')[1];
			
			$('#showPara_'+id).hide();
				
		});	
		
	});
</script>

