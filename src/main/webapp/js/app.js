(function(){
	/* global $ */
	'use strict';
	
	

	var App = {
		init: function() {
			this.adjustDom();
			this.bindUIActions();


		},

		adjustDom: function() {
			
			// Set the height of the sidebars to the height of the parent container
			setTimeout(function(){
				var current_width = $(window).width();
				if ( current_width > 992 ) {
					$('.search-result-item').each(function() {
						var height = $(this).height();
						// console.log(height);
						$(this).find('.js-full-height').height(height);
					});
				}
				
			}, 2000);
			
			$('.js-collapse-body').hide();
			
			
			// Create the icons in search results
			$('.document-group').each(function() {
				
				var docList = $(this).text();
					docList = docList.replace('[','').replace(']','');
					docList = docList.split(',');
					
				$(this).html('');
					
				for ( var i = 0; i < docList.length; i++ ){
					$(this).append('<p class="document-group-icon">' + docList[i] + '</p>');
				}
					
				// console.log(docList);
				
			});
			
			
//			var searchBarWidth = $('#my-input').width();
//			$('#my-input-suggestions').width(searchBarWidth);
//			console.log('Search bar width is: ' + searchBarWidth);
			
			
		},

		bindUIActions: function() {
			
			$(".Summary").on('change',function(){
				var summary = $(this).val();
				if(summary == 'ShowAll')
					{
						$('Owner').show();
						$('isType').show();
						$('otherTitles').show();
						$('chapterTitle').show();
						$('PartOf').show();
					}
				else
					{
					$('.'+summary).hide();
					}
				return false;
			});
			
			
			$("#dynamicSelect").on('change',function(){
				
				var link = $(this).val();
				if(link != 'Text Availability')
					{
						window.location = link;
					}
				return false;
			});
			
			// Left hand menu
			$('.js-collapse-head').click(function(e){
				e.preventDefault();
				$(this).next().slideToggle();
				
				$(this).find('.arrow').toggleClass('fa-caret-right').toggleClass('fa-caret-down');
			});
			
		
		}
	};

	App.init();

})();