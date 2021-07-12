<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style type="text/css">
body {
	background-color: #f3f3f3;
}
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: lightgrey;
	width: 100%;
}
li {
	float: left;
}
li a, .dropbtn, .user, .date-value {
	display: inline-block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
li a:hover, .dropdown:hover .dropbtn {
	background-color: red;
}
li.dropdown {
	display: inline-block;
}
.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}
.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}
.dropdown-content a:hover {
	background-color: #f1f1f1
}
.dropdown:hover .dropdown-content {
	display: block;
}
li.user {
	color: black;
	width: 800px;
}
.add-product {
	width: 100%;
	text-align: center;
	position: fixed;
}
table {
	border: 1px solid black;
	text-align: center;
	width: 700px;
	border-collapse: collapse;
	/*background-color: #85adad;*/
	background-color: #f3f3f3;
	margin: 0 auto;
}
th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px;
	text-align: left;
}
tr:nth-child(even) {
	background-color: #dddddd;
}
#add {
	background-color: #85adad;
	border: none;
	float: right;
	margin-right: 10%;
	height: 30px;
	width: 150px;
	padding: 5px;
	border-radius: 5px;
	cursor: pointer;
	color: #fff;
	background-color: #33bbff;
	box-shadow: 0 5px #999;
}
#add:hover {
	background: #0099e6;
}
#add:active {
	background-color: #0099e6;
	box-shadow: 0 2px #666;
	transform: translateY(2px);
}
#addItemForm {
	max-width: 300px;
	padding: 10px;
	background-color: white;
	position: fixed;
	text-align: center;
	right: 15px;
	border: 3px solid #f1f1f1;
	z-index: 90;
	display: none;
}
.addItemForm-btn {
	width: 100px;
	height: 30px;
	border-radius: 5px;
	border: 1px solid #0099e6;
	background-color: white;
	color: #0099e6;
	margin: 5px;
}
input{
font-family: 'Open Sans', sans-serif;
}
.addItemForm-btn:hover {
	background-color: #0099e6;
	color: white;
}
input.addItemForm-input{
	font-family: 'Open Sans', sans-serif;
	font-size : 14px;
	width : 150px;
	height : 20px;
	border-radius: 10px;
	text-align: center;
	border :1px solid black;
	 margin: 0 0 5px;
	 outline: none;
}
#productView {
	display: none;
}

#update-item {
	display: none;
	padding: 70px 70px;
	text-align: center;
	background-color: white;
	border: 3px solid #f1f1f1;
	z-index: 90;
	position: fixed;
	margin-left: 25%;
}

#delete-all-form {
	display: none;
	padding: 35px 70px;
	text-align: center;
	background-color: white;
	border: 3px solid #f1f1f1;
	z-index: 90;
	position: fixed;
}
#sale-bill-view {
	display: none;
}
#enter-menu {
	padding: 10px;
	width: 75%;
	float: left;
}
#total-menu {
	padding: 10px;
	float: right;
	height: 300px;
}
#view-bill {
	display: none;
	text-align: center;
	overflow: scroll;
	height: 450px;
}
table.sale-bill-table {
	width: 100%;
}
.date-value {
	background-color: lightgrey;
	float: right;
}
.main-div {
	margin: auto;
	border: 1px grey;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	background-color: white;
	width: 1100px;
	height: 500px;
	text-align: center;
}
input.total-amt {
	border: none;
	font-weight: bold;
	width: 100px;
}
input.total-qty {
	width: 100px;
}
.scrollit {
	overflow: scroll;
	height: 300px;
}
button.save-new {
	z-index: 0;
}
input.sale {
	width: 150px;
	height: 30px;
	font: bold 20px solid black;
}
input.sale-input{
	width: 200px;
	height: 25px;
	border-radius : 5px;
	border : 1px solid grey;
	outline : none;
}
input.value-search {
	width: 250px;
	height: 25px;
	border-radius : 5px;
	border : 1px solid grey;
	outline : none;
	margin:10px;
}

button.btns {
	border-radius: 5px;
	border: none;
	height: 30px;
	width: 150px;
	display: inline-block;
	padding: 5px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #33bbff;
	box-shadow: 0 5px #999;
}

button.btns:hover {
	background-color: #0099e6;
}

button.btns:active {
	background-color: #0099e6;
	box-shadow: 0 2px #666;
	transform: translateY(4px);
}
</style>
<!--  <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>   -->
<script src="/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var tid=null;
		var tblBarcode, tblItemName, tMrp, tRate, tStock;
		var updateFlag=false;
		var barcode=null;
		var saveType =null;
	//datalist for barcode entering
	let datalist =$('#barcode-list');
	let datalistName = $('#name-list');
	var nameSearchValue = null;
	
	//set date
	setDate();
	//set date function
	function setDate(){
		var today =new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();

		today = dd + '-' + mm + '-' + yyyy;
		var dateLabel =$('.date-value').val();
		$('.date-value').val(dateLabel+today);
	}
	
		$("#stock").click(function(){
			//hiding sale bill view 
			$("#sale-bill-view").hide();
				$('#view-bill').hide();
				$("#productView").show();
			$.ajax({
					type : "GET",
					url : "http://localhost:8080/retailapp/getItem",
					success : function(result){
						var slno=1;
						$("#view-stock").empty();
						$("#view-stock").append('<tr><th>SlNo</th><th>Barcode</th><th>Item Name</th><th>MRP</th><th>Rate</th><th>Stock Qty</th></tr>');
					$.each(result, function(key, value){
				
						var itemID=value.itemID;
						var barcode=value.barcode;
						var name=value.name;
						var mrp=value.mrp;
						var rate=value.rate;
						var stock=value.stock;
						
				var row=$('<tr>');
					row.append($('<td>').html(slno));
					row.append($('<td>').html(barcode));
					row.append($('<td>').html(name));
					row.append($('<td>').html(mrp));
					row.append($('<td>').html(rate));
					row.append($('<td>').html(stock));
					$(row).attr('id',itemID);
						$("#view-stock").append(row);
						slno=slno+1;
					});
					}

			});

		});

		

		$("#home").click(function(){
			alert("home clicked");
		});
		
		$("#add").click(function(){
			
			$('#update-item').hide();
			$('#delete-all-form').hide();
			$("#addItemForm").show();
			
		});
		$("#addItem").click(function(){
				var name = $("#itemName").val();
				var barcode = $("#barcode").val();
				var mrp = $("#MRP").val();
				var rate =$("#rate").val();
				var stock = $("#stockqty").val();
		
				$.ajax({
				type : 'POST',
				url :"http://localhost:8080/retailapp/save",
				data :{
					barcode : barcode,
					name : name,
					mrp : mrp,
					rate : rate,
					stock : stock
				},
				success:function(res){
				alert(res);
					if(res===("Success")){
					$("#view-stock").empty();
					$("#view-stock").append('<tr><th>SlNo</th><th>Barcode</th><th>Item Name</th><th>MRP</th><th>Rate</th><th>Stock Qty</th></tr>');
					$("#stock").click();
					 $("#reset").click();
					$("#cancel").click();
					}	
			}
			
			});
		});
		$("#cancel").click(function(){
			$("#addItemForm").hide();
		});

	$('#view-stock').dblclick(function(e){
		tid=$(e.target).closest('tr').attr('id');
		
		if(tid!=null){
			$('#addItemForm').hide();
			$('#delete-all-form').hide();
		$("#update-item").show();
		$.ajax({
			type:'GET',
			url:'find',
			data:{
				itemID:tid
			},
			success:function(res){
				//alert(res.itemID+" "+res.barcode);
				$("#uf-id").val(res.itemID);
				$("#uf-name").val(res.name);
				$("#uf-barcode").val(res.barcode);
				$("#uf-mrp").val(res.mrp);
				$("#uf-rate").val(res.rate);
				$("#uf-stock").val(res.stock);
			}
		});
	}	
	});
	$("#uf-edit").click(function(){
		$.ajax({
			type : 'POST',
			url :"http://localhost:8080/retailapp/update",
			data :{
				itemID:tid,
				barcode : $("#uf-barcode").val(),
				name : $("#uf-name").val(),
				mrp :$("#uf-mrp").val(),
				rate : $("#uf-rate").val(),
				stock : $("#uf-stock").val()
			},
			success:function(res){
			alert(res);
				if(res===("Success")){
				$("#view-stock").empty();
				$("#view-stock").append('<tr><th>SlNo</th><th>Barcode</th><th>Item Name</th><th>MRP</th><th>Rate</th><th>Stock Qty</th></tr>');
				$("#stock").click();
				$("#update-item").hide();
				}	
		}
		
		});
	});
	
	$("#uf-delete").click(function(){
		$.ajax({
			type : 'POST',
			url :"delete",
			data :{
				itemID:tid,
			},
			success:function(res){
			alert(res);
			
				$("#view-stock").empty();
				$("#view-stock").append('<tr><th>SlNo</th><th>Barcode</th><th>Item Name</th><th>MRP</th><th>Rate</th><th>Stock Qty</th></tr>');
				$("#stock").click();
				$("#update-item").hide();
				
		},
		error:function(){
			alert("Error");
		}
		
		});
	});
	$("#uf-cancel").click(function(){
		$("#update-item").hide();
	});
	$('#delete-all').click(function(){
		$('#addItemForm').hide();
		$('#update-item').hide();
		$('#delete-all-form').show();
	});
	$("#delete-all-ok").click(function(){
		$.ajax({
			url:"deleteAll",
			success:function(res){
				alert(res);
				$("#view-stock").empty();
				$("#view-stock").append('<tr><th>SlNo</th><th>Barcode</th><th>Item Name</th><th>MRP</th><th>Rate</th><th>Stock Qty</th></tr>');
				$("#stock").click();
				$('#delete-all-form').hide();
			},
		error:function(){
			alert("Error");
		}
		});
	});
	$("#delete-all-cancel").click(function(){
		$('#delete-all-form').hide();
	});

	$("#salesBill").click(function(){
			//alert("salesBill clicked");
			$("#productView").hide();
			$('#view-bill').hide();
			$("#sale-bill-view").show();
		});
	
	//enter barcode number in textfield and search for barcodes
	$("#barcode-sale").on("keyup", function() {
		datalist.empty();
   console.log($(this).val());
  barcode = $(this).val();
   
   $.ajax({
   	type:'GET',
   	url:'getItems',
   	data:{
   		barcode:barcode
   	},
   	success:function(result){
   		if(result.length == 0){
   			alert("Invalid Entry");
   			$('#barcode-sale').val('');
   		 	return;
   		}
   		datalist.empty();
   			$.each(result, function(key, entry){
   	   			console.log(entry);
   	   			datalist.append($('<option></option>')
   						.attr('id', entry.itemID)
   						.text(entry.barcode));
   	   			
   	   			if(barcode === entry.barcode){
   	   				//alert("barcode matched");
   	   				
   	   				$.ajax({
   	   					type:'GET',
   	   					url:'find',
   	   					data:{
   	   						itemID:entry.itemID
   	   					},
   	   					success:function(entry){
   	   						//to set data to table
   	   						setTableData(entry);
   	   					}
   	   				});
   	   			}
   	   			
   	   		});
   	}
   });
	
});

	//enter barcode number in textfield and search for barcodes
	$("#name-search").on("keyup", function() {
		datalistName.empty();
   console.log($(this).val());
  nameSearchValue = $(this).val();
   
   $.ajax({
   	type:'GET',
   	url:'getItemByName',
   	data:{
   		name:nameSearchValue
   	},
   	success:function(result){
   		if(result.length == 0){
   			alert("Invalid Entry");
   			$('#name-search').val('');
   		 	return;
   		}
   		datalistName.empty();
   			$.each(result, function(key, entry){
   	   			console.log(entry);
   	   			datalistName.append($('<option></option>')
   						.attr('id', entry.itemID)
   						.text(entry.name));
   	   			
   	   			if(nameSearchValue === entry.name){
   	   				//alert("barcode matched");
   	   				
   	   				$.ajax({
   	   					type:'GET',
   	   					url:'find',
   	   					data:{
   	   						itemID:entry.itemID
   	   					},
   	   					success:function(entry){
   	   						//to set data to table
   	   						setTableData(entry);
   	   				}
   	   				});
   	   			}
   	   			
   	   		});
   	}
   });
	
});
	
	//function to set data to table after success function
	function setTableData(entry){
		if(!entry.stock<=0){
					var rate=entry.rate;
					//to make qty field disabled..we can also use attr()
					$('input#qty').prop('disabled', true);
					
					//to remove id attribute from input 
					$('input#qty').removeAttr('id');
					
					//to remove id attribute of input field of total value.
					$('input#total').removeAttr('id');
				
					
				var row=$('<tr>');
					row.append($('<td>').attr('class', 'tdid').html(entry.itemID));
				row.append($('<td>').html(entry.name));
				row.append($('<td>').html(entry.barcode));
				row.append($('<td>').html(entry.stock));
				row.append($('<td>').html(entry.mrp));
				row.append($('<td>').html(entry.rate));
				
				//row.append($('<td>').html($('<input>').attr('id', "qty").val("1")));
				row.append($('<td>').html($('<input>').attr({id:"qty", disabled:false, class:"total-qty"}).val("1")));
				
				//adding a new td with input , having multiple attributes(id and disabled).
				row.append($('<td>').html($('<input>').attr({id:"total", disabled:"disabled", class:"total-amt"}).val(entry.rate)));

				//setting id to <tr>
				$(row).attr('id',entry.itemID);
				
				//appending <tr> to <table>
				$("#sale-bill-table").append(row);
					
					//clearing barcode textfield value .
					$('#barcode-sale').val("");
					datalist.empty();
					
					//clearing name search textfield value
					$('#name-search').val('');
					datalistName.empty();
					
						   						
					//when we change qty value we need to calculate corresponding total
					$('#qty').on('keyup', function(){
							var rowID=$('#sale-bill-table tr:last').attr('id');
							
							//checking for entry itemID matches rowID 
							if(rowID == entry.itemID){
						var qtyValue=$('#qty').val();
						if(qtyValue<=entry.stock){
							$('#total').val(qtyValue*rate);	
						}
						else{
							alert("please enter valid quantity");
							$('#qty').val(1);
							$('#total').val(rate);	
						}
							}
						   	   							
						});
				}
					else{
						alert("not in stock");
						$('#barcode-sale').val('');
						$('#name-search').val('');
					}
	}
	
	$('#proceed').click(function(){
		
		var rowCount =($('#sale-bill-table tr').length)-1;
		if(rowCount>0){
			if($('#qty').val()>0){
				//setting total items
				$('#total-items').val(rowCount);
				//getting table values
				var total=0;
				$('#sale-bill-table tr').find('td input.total-amt').each(function(){
						total+=parseFloat($(this).val());
				});
				$('#total-payable').val(total);
			}
			else{
				alert("please add valid quantity");
			}
			
		}
		else{
			alert("please add items..");
		}
		
	});
	
	//save and refresh the sale bill page
	$('#save-new').click(function(){
		var phone =$('#phone').val();
			var pattern = $('#phone').attr('pattern');
			var re = new RegExp(pattern);
		
		
		if($('#total-items').val()===''){
			alert("Error");
		}
		else if(!re.test(phone) || phone.length>10){
			alert("please enter valid phone number");
		}
		else{
			//processing api call to save
			var mainArray = new Array();
			var customerName = $('#customerName').val();
			var phoneNo = $('#phone').val();
			var totalItems = parseInt($('#total-items').val());
			var totalPayable = parseFloat($('#total-payable').val());
			//const sale = {customerName:customerName, phone:phoneNo, totalItems:totalItems, totalAmount:totalPayable};
			//mainArray.push(sale);
			var items = new Array();
			$('#sale-bill-table tr').each(function(){
				
				if($(this).attr('id')!=undefined){
					var id=parseFloat($(this).attr('id'));
					var totalQty=parseInt($(this).find('.total-qty').val());
					var totalAmt=parseInt($(this).find('.total-amt').val());
					//assigning the variables as object properties
					const item = {itemTbl:id, qty:totalQty, total:totalAmt};
					items.push(item);
					
				}
			});
		
			const sale = {customerName:customerName, phone:phoneNo, totalItems:totalItems, totalAmount:totalPayable, saleDetailsTbls:items};
			var salesTbl = JSON.stringify(sale);
			
			$.ajax({
				type : 'POST',
				url : 'saveSale',
				contentType:"application/json; charset=utf-8",
				data :salesTbl,
				
				success : function(res){
					alert(res);
					if(res=='Success'){
						refreshSaveBillPage();
							var url = "http://localhost:8080/retailapp/exportPdf";
							$(location).attr('href',url);
					}
				}
			});
		}
	});
	
	function refreshSaveBillPage(){
		$("#sale-bill-table").empty();
		$("#sale-bill-table").append('<tr><th>ID</th><th>Item Name</th><th>Barcode</th><th>Stock</th><th>MRP</th><th>Rate</th><th>Qty</th><th>Total</th></tr>');
		$('#total-items').val('');
		$('#total-payable').val('');
		$('#customerName').val('');
		$('#phone').val('');
	}
	
	//displaying view bill window
	$('#viewBill').click(function(){
		$("#productView").hide();
		$("#sale-bill-view").hide();
		$('#view-bill').show();
		$('#sales-table').empty();
		$.ajax({
			type : 'GET',
			url : 'getAllSalesTbl',
			success : function(res){
				$('#sales-table').append('<tr><th>SLNO</th><th>Date</th><th>Customer</th><th>Phone</th><th>Items</th><th>Total</th></tr>');
				var slno = 1;
				$.each(res, function(key, value){
					var row = $('<tr>');
						row.append($('<td>').html(slno));
						row.append($('<td>').html(value.wefDate));
						row.append($('<td>').html(value.customerName));
						row.append($('<td>').html(value.phone));
						row.append($('<td>').html(value.totalItems));
						row.append($('<td>').html(value.totalAmount));
					$('#sales-table').append(row);
					slno = slno+1;
				});
			},
			error : function(){
				alert("Error");
			}
		});
		
	});
	$('#cancel-bill').click(function(){
		refreshSaveBillPage();
	});
});
</script>
</head>
<body>

	<ul>
		<li><a href="#" id="home">Home</a></li>
		<li class="dropdown"><a href="#" class="dropbtn">Sales</a>
			<div class="dropdown-content">
				<a href="#" id="salesBill">Sales Bill</a> <a href="#" id="viewBill">View
					Bill</a>
			</div></li>
		<li class="dropdown"><a href="#" id="stock">Stock</a></li>
		<li class="user">User: Administrator</li>

		<li><input type="text" class='date-value' disabled="disabled"
			value='Date : ' /></li>
	</ul>
	<div class='main-div'>
		<div id="productView">
			<br>
			<button type="button" id="add">Add New Item</button>
			<br> <br>
			<div id="addItemForm" class="addItemForm">
				<form id="add-new">
					<h4>Add New Item</h4>
					<input type="text" title="Must be characters" name="itemName" id="itemName" class="addItemForm-input"
						placeholder="itemName" autocomplete="off" required> <input  title="Must be Number with length 10"
						type="text" id="barcode" name="barcode"  class="addItemForm-input" placeholder="barcode"
						autocomplete="off" required="required">
						 <input
						type="text" title="Must be a number" id="MRP" name="MRP" placeholder="MRP"  class="addItemForm-input"
						required="required"> <input type="text" id="rate" title="Rate must be less than MRP"
						name="rate" placeholder="Rate" required="required"  class="addItemForm-input"> <input
						type="text" id="stockqty" name="stockqty" placeholder="Stock Qty"  class="addItemForm-input"
						required="required"> <br>
					<button type="button" class="addItemForm-btn" id="addItem">ADD</button>
					<button type="reset" class="addItemForm-btn" id="reset">RESET</button>
					<button type="button" class="addItemForm-btn" id="cancel">CANCEL</button>
				</form>
			</div>
			<div id="update-item" class="update-item">
				<label>ID</label> <input type="text" id="uf-id" disabled="disabled">
				<br> <label>Name</label> <input type="text" id="uf-name">
				<br> <label>Barcode</label> <input type="text" id="uf-barcode"
					disabled="disabled"> <br> <label>MRP</label> <input
					type="text" id="uf-mrp"> <br> <label>Rate</label> <input
					type="text" id="uf-rate"> <br> <label>Stock</label> <input
					type="text" id="uf-stock"> <br>
				<button id="uf-edit">UPDATE</button>
				<button id="uf-delete">DELETE</button>
				<button id="uf-cancel">CANCEL</button>

			</div>

			<div id="delete-all-form">
				<label>Are you sure want to delete all items?</label> <br>
				<button id="delete-all-ok">Proceed</button>
				<button id="delete-all-cancel">Cancel</button>
			</div>

			<div class="scrollit">
				<table id="view-stock" class="view-stock">
					<tr>
						<th>SlNo.</th>
						<th>Barcode</th>
						<th>Product Name</th>
						<th>MRP</th>
						<th>Rate</th>
						<th>Stock Qty</th>
					</tr>
				</table>
			</div>
			<br> <br>
			<button type="button" id="delete-all" class="btns">Delete
				All</button>


		</div>
		<div id="sale-bill-view">
			<br> <label>Customer Name :</label> <input type="text"
				name="customerName" id="customerName" placeholder="customer name"
				class="sale-input"> <label>Phone :</label> <input type="text"
				name="phone" id="phone" placeholder="phone number" class="sale-input"
				 pattern="[0-9]{10}" title="enter 10 digit number"> <br> <br> <input
				list="barcode-list" type="text" name="barcode-value"
				id="barcode-sale" placeholder="Barcode search" class="value-search"
				autocomplete="off" />
			<datalist id="barcode-list"></datalist>
			<input type="text" name="name-search" id="name-search"
				placeholder="Name search" list="name-list" class="value-search"
				autocomplete="off">
			<datalist id="name-list"></datalist>
			<br> <br>
			<div id="enter-menu">
				<div class="scrollit">
					<table id="sale-bill-table" class="sale-bill-table">
						<tr>
							<th>ID</th>
							<th>Item Name</th>
							<th>Barcode</th>
							<th>Stock</th>
							<th>MRP</th>
							<th>Rate</th>
							<th>Qty</th>
							<th>Total</th>
						</tr>
					</table>
				</div>
				<br>
				<button id="proceed" class="btns">PROCEED</button>
			</div>
			<div id="total-menu">
				<label>Total Items</label><br> <input type="text"
					id="total-items" disabled="disabled" class="sale"> <br>
				<br> <label>Total Payable</label> <br> <input type="text"
					id="total-payable" disabled="disabled" class="sale"> <br>
				<br> <br>

				<button id="save-new" class="btns">SAVE</button>
				<br> <br>
				<button id="cancel-bill" class="btns">CANCEL</button>
			</div>

		</div>
		<div id='view-bill' class='view-bill'>
			<label>PREVIOUS BILLS</label> <br>
			<table id='sales-table' class="scrollit">
				<tr>
					<th>SLNO</th>
					<th>Date</th>
					<th>Customer</th>
					<th>Phone</th>
					<th>Items</th>
					<th>Total</th>
				</tr>
			</table>
		</div>
	</div>
</body>

</html>