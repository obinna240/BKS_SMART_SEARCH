<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="container">
	
	<div class="row">
		
		<div class="col-md-4">
		
			
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title text-center"><strong>Recently Added Documents</strong></h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed table-hover">
							<c:forEach  items="${recentlyUploaded}" var="recentlyUploadedDocs" varStatus="count" begin="0" end="3">
								<c:choose>
									<c:when test="${count.index eq 0 }">
										<tr class="active">
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:forEach  items="${recentlyUploaded}" var="recentlyUploadedDocs" varStatus="count" begin="4">
								
										<tr class="hideTable1">
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									
							</c:forEach>
							
						</table>
							
					</div>
					<div class="text-center">
						<button type="button" class="btn btn-default recentUpload1" style="background-color: purple; color: white;" aria-haspopup="true" aria-expanded="false">
	    							View more <span class="caret"></span>
	  					</button>
  					</div>
				</div>
			</div>	
			 		


		</div>
		<div class="col-md-4">
		
			
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title text-center"><strong>Most Recently Uploaded Documents</strong></h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed table-hover">
							<c:forEach  items="${recentlyUploaded}" var="recentlyUploadedDocs" varStatus="count" begin="0" end="3">
								<c:choose>
									<c:when test="${count.index eq 0 }">
										<tr class="active">
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:forEach  items="${recentlyUploaded}" var="recentlyUploadedDocs" varStatus="count" begin="4">
								
										<tr class="hideTable2">
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									
							</c:forEach>
							
						</table>
							
					</div>
					<div class="text-center">
						<button type="button" class="btn btn-default recentUpload2" style="background-color: purple; color: white;" aria-haspopup="true" aria-expanded="false">
	    							View more <span class="caret"></span>
	  					</button>
  					</div>
				</div>
			</div>	
			 		


		</div>
		<div class="col-md-4">
		
			
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title text-center"><strong>Your Bookmarks and Saved Searches</strong></h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed table-hover">
							<c:forEach  items="${recentlyUploaded}" var="recentlyUploadedDocs" varStatus="count" begin="0" end="3">
								<c:choose>
									<c:when test="${count.index eq 0 }">
										<tr class="active">
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:forEach  items="${recentlyUploaded}" var="recentlyUploadedDocs" varStatus="count" begin="4">
								
										<tr class="hideTable3">
											<td>
												<s:url var="dUrl" value="/fileDownload">
													<s:param name="documentName">${recentlyUploadedDocs.documentUrl}</s:param>
												</s:url>
												<p><strong>${recentlyUploadedDocs.title}</strong></p>
												<p><small>${recentlyUploadedDocs.author} | ${recentlyUploadedDocs.dateOfPublicationString} | Version: ${recentlyUploadedDocs.docVersion} | ${recentlyUploadedDocs.docGroup}</small></p>
												<a href="${dUrl}" class="pull-right"><small>download</small></a>
											</td>
										</tr>
									
							</c:forEach>
							
						</table>
							
					</div>
					<div class="text-center" >
						<button type="button" class="btn btn-default recentUpload3"  style="background-color: purple; color: white;" aria-haspopup="true" aria-expanded="false">
	    							View more <span class="caret"></span>
	  					</button>
  					</div>
				</div>
			</div>	
			 		


		</div>
	</div>
</div>


<script>	
    $(document).ready(function() {
        $('.hideTable1').hide();
        $('.hideTable2').hide();
        $('.hideTable3').hide();
    });

</script>

<script>
$(".recentUpload1").click(function(){
    $(".hideTable1").toggle();

});
</script>

<script>
$(".recentUpload2").click(function(){
    $(".hideTable2").toggle();

});
</script>

<script>
$(".recentUpload3").click(function(){
    $(".hideTable3").toggle();

});
</script>