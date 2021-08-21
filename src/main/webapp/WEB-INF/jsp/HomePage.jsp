<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link rel="stylesheet" href="/css/mystyle.css">
<!--  <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>   -->
<script src="/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/js/script.js"></script>
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
	<div class="business">
	<h1>MEN'S CORNER</h1>
	</div>
	
	<div class='main-div'>
		<div id="productView">
			<br>
			<button type="button" id="add">Add New Item</button>
			<br> <br>
			<div id="addItemForm" class="addItemForm">
				<form id="add-new">
					<h4>Add New Item</h4>
					<input type="text" title="Must be characters" name="itemName"
						id="itemName" class="addItemForm-input" placeholder="itemName"
						autocomplete="off" required> <input
						title="Must be Number with length 10" type="text" id="barcode"
						name="barcode" class="addItemForm-input" placeholder="barcode"
						autocomplete="off" required="required"> <input type="text"
						title="Must be a number" id="MRP" name="MRP" placeholder="MRP"
						class="addItemForm-input" required="required"> <input
						type="text" id="rate" title="Rate must be less than MRP"
						name="rate" placeholder="Rate" required="required"
						class="addItemForm-input"> <input type="text"
						id="stockqty" name="stockqty" placeholder="Stock Qty"
						class="addItemForm-input" required="required"> <br>
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
			<div class="view-stock-div">
				<table id="view-stock" class="scrollit">
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
			</div>
		<div id="sale-bill-view">
			<br> <label>Customer Name :</label> <input type="text"
				name="customerName" id="customerName" class="sale-input"> <label>Phone :</label> <input
				type="text" name="phone" id="phone" class="sale-input" pattern="[0-9]{10}" title="enter 10 digit number">
			<br> <br> <input list="barcode-list" type="text"
				name="barcode-value" id="barcode-sale" placeholder="Barcode search"
				class="value-search" autocomplete="off" />
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
				<button id="delete-last" class="btns">DELETE LAST ITEM</button>
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