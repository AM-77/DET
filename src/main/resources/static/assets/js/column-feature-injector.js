$(document).ready(function () {
	
				//add column
				$("#addrowCol").on("click", function () {
			        var source = $(this).parent();
			        var colCount = source.prevAll(".column").length;
			        console.log("column count = " + colCount);
			        
			        console.log("sending get request to /admin/addFeature")
			        $.get("/admin/addColumn", 
				        		{colCount : colCount},
							        function (data){
				        			 	 console.log(data);
							   		     $(data.toString()).insertBefore(source);
							       		});
			        
			    });
	
				//delete Column
			    $("#ibtnDelCol").on("click", function (event) {
			    	$(this).parent().prev(".column").remove(); 
			    	$(this).parent().prev("li").remove(); 
			    });
			    
			    //add feature
			    $(".columnList").on("click",".addrow", function () {
			        var source = $(this).parent();
			        var featCount = source.prevAll("li").length;
			        var colCount = source.closest(".column").prevAll(".column").length;
			        
			        console.log("feature count = " + featCount);
			        console.log("column count = " + colCount);
			        
			        console.log("sending get request to /admin/addFeature")
			        $.get("/admin/addFeature", 
				        		{featCount : featCount , colCount : colCount},
							        function (data){
				        			 	 console.log(data);
							   		     $(data.toString()).insertBefore(source);
							       		});
			    });
			    
			    //delete feature
			    $(".columnList").on("click",".ibtnDel", function (event) {
			        $(this).parent().prev(".feature").remove();
			    });
			    
			    //delete machine
			    $(".machineDel").on("click", function(){
			    	var sourceRow = $(this).closest(".row");
			    	var mapName = $(this).prevAll(".mapName").html();
			    	console.log(mapName);
			    	
			    	console.log("sending get request to /admin/deleteMachine")
			        $.get("/admin/deleteMachine", 
				        		{mapName : mapName},
							        function (success){
				        			 	 if(success)
				        			 		 {
				        			 		 	sourceRow.remove();
				        			 		 	alert( mapName + " removed successfully !");
				        			 		 }
				        			 	 else
				        			 		 alert("WARNING! " + mapName + " could not be removed !");
							       		});
			    });
			});