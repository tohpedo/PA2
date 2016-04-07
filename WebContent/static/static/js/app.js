
$("#createTodo").click(function(){
	var input_id = $("#todoId").val();
	var input_message = $("#todoMessage").val();
	var data = {
			id:input_id,
			message:input_message
			};
	$.ajax({
		url:"/LAB4/create",
		method:"POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "json",
		success: function(data,status,xhr){
			if(data.code == "Success"){
				alert("Success!");
			}else{
				alert("Error! Message is " + data.message);
			}
		},
		error: function(data,status,xhr){
			alert(JSON.stringify(data));
		},
		data: data
	});
});

$("#getJSON").click(
			function() {
				var input_id = $("#todoId_get").val();
				$.ajax({
					type : 'GET',
					url : "/LAB4/json/" + input_id,
					dataType: "json",
					success : function(data,status,xhr) {
						alert("Your message is: " + data.message);
					},
					error : function(data,status,xhr) {
						alert(JSON.stringify(data));
					}
				});
			});	

$("#getXML").click(
		function() {
			var input_id = $("#todoId_get").val();
			$.ajax({
				type : 'GET',
				url : "/LAB4/xml/" + input_id,
				dataType: "xml",
				success : function(data,status,xhr) {
					alert("Your message is: " + data.message);
				},
				error : function(data,status,xhr) {
					alert(JSON.stringify(data));
				}
			});
		});	

$("#getAllJSON").click(
		function() {		
			$.ajax({
				type : 'GET',
				url : "/LAB4/all/json",
				success : function(data,status,xhr) {
					alert(JSON.stringify(data));
				},
				error : function(data,status,xhr) {
					alert(JSON.stringify(data));
				}
			});
		});		
$("#getAllXML").click(
		function() {		
			$.ajax({
				type : 'GET',
				url : "/LAB4/all/xml",
				success : function(data,status,xhr) {
					alert(data);
				},
				error : function(data,status,xhr) {
					alert(data);
				}
			});
		});		
$("#deleteTodo").click(
	function() {
		var input_id = $("#todoId_delete").val();
		$.ajax({
			url : "/LAB4/delete/" + input_id,
			method : "DELETE",
			contentType : 'application/octet-stream; charset=utf-8',
			success: function(data,status,xhr){
				if(data.code == "Success"){
					alert("Success!");
				}else{
					alert("Error! Message is " + data.message);
				}
			},
			error: function(data,status,xhr){
				alert(JSON.stringify(data));
			},
		});
	});