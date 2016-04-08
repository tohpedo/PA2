

$("#createNewCustomer").click(function(){
	var ifname = $("#newCustomerFirstName").val();
	var ilname = $("#newCustomerLastName").val();
	var iphone_num = $("#newCustomerPhoneNumber").val(); 
	var iaddress = $("#newCustomerBillingAddress").val();
	var icity = $("#newCustomerBillingCity").val();
	var istate = $("#newCustomerBillingState").val();
	var izip = $("#newCustomerBillingZip").val();
	var icheckin = $("#customerCheckIn").val();
	var icheckout = $("#customerCheckOut").val();
	var data = {
			fname:ifname,
			lname:ilname,
			phone_num:iphone_num,
			address:iaddress,
			city:icity,
			state:istate,
			zip:izip,
			checkin:icheckin,
			checkout:icheckout
			};
	$.ajax({
		type: 'POST',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "text/html",
		url:"/PA2/create",
        data: data,
        success: function(data){
			$("#responseHeader").html(data.responseText);
        	
		},
		error: function(data,status,xhr){
			$("#responseHeader").html(data.responseText);
		},
	});
	$("#Registration").toggle();
	
});
$("#makeReservation").click(function(){
	var icustid = $("#reserveCustId").val();
	var iroom_num = $("#reserveRoomNum").val();
	var data = {
			cust_id: icustid,
			room_num: iroom_num
			};
	$.ajax({
		type: 'POST',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "text/html",
		url:"/PA2/reserve",
        data: data,
        success: function(data){
			$("#responseHeader").html(data.responseText);
        	
		},
		error: function(data,status,xhr){
			$("#responseHeader").html(data.responseText);
		},
	});
	$("#RoomReservation").toggle();
	
});

$("#searchCustomerID").click(function(){
	var icustid = $("#findCustomerID").val();
	var data = {
			cust_id: icustid,
			};
	$.ajax({
		type: 'POST',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "text/html",
		url:"/PA2/findId",
        data: data,
        success: function(data){
			$("#responseHeader").html(data.responseText);
        	
		},
		error: function(data,status,xhr){
			$("#responseHeader").html(data.responseText);
		},
	});
});

$("#searchCustomerName").click(function(){
	var icustname = $("#findCustomerName").val();
	var data = {
			cust_name: icustname,
			};
	$.ajax({
		type: 'POST',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "text/html",
		url:"/PA2/findName",
        data: data,
        success: function(data){
			$("#responseHeader").html(data.responseText);
        	
		},
		error: function(data,status,xhr){
			$("#responseHeader").html(data.responseText);
		},
	});
});

$("#seeAllCustomers").click(function(){
	$.ajax({
		type: 'GET',
		dataType: "text/html",
		url:"/PA2/all",
        success: function(data){
			$("#responseHeader").html(data.responseText);
        	
		},
		error: function(data,status,xhr){
			$("#responseHeader").html(data.responseText);
		},
	});
});
$("#submitPayment").click(function(){
	var icustid = $("#paymentCustId").val();
	var iroom_num = $("#paymentRoomNum").val();
	var icard_num = $("#paymentCardNum").val(); 
	var iexpdate = $("#paymentExpiration").val();
	var iamount = $("#paymentAmount").val();
	var data = {
			cust_id:icustid,
			room_num:iroom_num,
			card_num:icard_num,
			exp_date:iexpdate,
			amount:iamount
			};
	$.ajax({
		type: 'POST',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "text/html",
		url:"/PA2/pay",
        data: data,
        success: function(data){
			$("#responseHeader").html(data.responseText);
        	
		},
		error: function(data,status,xhr){
			$("#responseHeader").html(data.responseText);
		},
	});
	$("#PaymentInformation").toggle();
	
});
$("#newCustomer").click(function(){
	$("#Registration").toggle();
});
$("#reserveRoom").click(function(){
	$("#RoomReservation").toggle();
});
$("#makePayment").click(function(){
	$("#PaymentInformation").toggle();
});

