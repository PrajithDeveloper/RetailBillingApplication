
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
			$('.main-div').show();
			$('.business').hide();
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
			$('.main-div').hide();
			$('.business').show();
		});
		
		$("#add").click(function(){
			
			$('#update-item').hide();
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
			type : 'PUT',
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
			type : 'DELETE',
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

	$("#salesBill").click(function(){
			//alert("salesBill clicked");
			$('.main-div').show();
			$('.business').hide();
			$("#productView").hide();
			$('#view-bill').hide();
			$("#sale-bill-view").show();
		});
	
	
	//enter barcode number in textfield and search for barcodes
	$("#barcode-sale").on("keyup", function() {
		var qtyValue = $('#qty').val();
		if( qtyValue === undefined || qtyValue>0){
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
		}
		else 
		{
			alert("quantity is invalid");
			$('#barcode-sale').val('');
		}
	
	
});

	//enter barcode number in textfield and search for barcodes
	$("#name-search").on("keyup", function() {
		var qtyValue = $('#qty').val();
		if( qtyValue === undefined || qtyValue>0){
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
		}else{
			alert("quantity is invalid");
			$('#name-search').val('');
		}
	
	
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
			var qty = $('#qty').val();
			if(qty>0 || qty ===undefined ){
				//setting total items
				$('#total-items').val(rowCount);
				//getting table values
				var total=0;
				$('#sale-bill-table tr').find('td input.total-amt').each(function(){
						total+=parseFloat($(this).val());
				});
				$('#total-payable').val(total);
				
				//setting delete last button inactive
				$('#delete-last').prop("disabled", true);
			}
			else{
				alert("please add valid quantity");
			}
			
		}
		else{
			alert("please add items..");
		}
		
	});
	//delete last item added to table
	$('#delete-last').click(function(){
		var rowCount = ($('#sale-bill-table tr').length) - 1;
		if(rowCount>0){
			$('#sale-bill-table tr:last').remove();
			
		}else{
			alert("no item added!");
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
		//setting delete last button active
		$('#delete-last').prop("disabled", false);
	}
	
	//displaying view bill window
	$('#viewBill').click(function(){
		$('.main-div').show();
		$('.business').hide();
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
