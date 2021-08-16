package com.projects.retailapp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.projects.retailapp.entity.InvoicePDFExporter;
import com.projects.retailapp.entity.ItemTbl;
import com.projects.retailapp.entity.SaleDetailsTbl;
import com.projects.retailapp.entity.SalesTbl;
import com.projects.retailapp.service.RetailAppService;

@RestController
@RequestMapping("/retailapp")
public class RetailAppController {

	@Autowired
	private RetailAppService retailAppService;

	private String MODE = null;
	
	/* Returning home page
	 */
	@RequestMapping("home")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView("HomePage");
		return mav;
	}
	
	/* Returns all the items
	 */
	@GetMapping("getItem")
	public List<ItemTbl> getItem() {
		List<ItemTbl> itemTbl = retailAppService.getItem();
		return itemTbl;
	}
	
	/* Saves the new item
	 */
	@PostMapping("save")
	public String saveItem(ItemTbl itemTbl, BindingResult bindingResult) {
		MODE = "INSERT";
		return retailAppService.saveItem(itemTbl, MODE);

	}
	
	/* Updates item
	 */
	@PostMapping("update")
	public String updateItem(ItemTbl itemTbl, BindingResult bindingResult) {
		MODE = "UPDATE";
		return retailAppService.saveItem(itemTbl, MODE);

	}
	
	/* Finds the Item by itemID
	 */
	@GetMapping("find")
	public Optional<ItemTbl> findItem(Long itemID) {
		Optional<ItemTbl> itemTbl = retailAppService.findItem(itemID);
		return itemTbl;
	}
	
	
	/* Deletes item by itemID
	 */
	@PostMapping("delete")
	public String deleteItem(Long itemID) {
		return retailAppService.deleteItem(itemID);
	}
	
	/* Returns item by Barcode
	 */
	@GetMapping("getItems")
	public List<ItemTbl> getItemByBarcode(String barcode) {
		List<ItemTbl> barcodes = retailAppService.getItemByBarcode(barcode);
		return barcodes;
	}
	
	/* Save new sale
	 */
	@PostMapping("saveSale")
	public String saveSale(@RequestBody SalesTbl salesTbl) throws Exception {
		return retailAppService.saveSale(salesTbl);
	}
	
	/*  Returns item by name
	 */
	@GetMapping("getItemByName")
	public List<ItemTbl> getItemByName(String name) {
		List<ItemTbl> itemTbl = retailAppService.getItemByName(name);
		return itemTbl;
	}
	
	/*  To get all sales details
	 */
	@GetMapping("getAllSalesTbl")
	public List<SalesTbl> getSalesTbl() {
		List<SalesTbl> salesTbl = retailAppService.findAllSalesTbl();
		return salesTbl;
	}
	
	/*  to generate invoice and pdf file 
	 */
	@GetMapping("exportPdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		SalesTbl salesTbl = retailAppService.getLastSalesTbl();
		List<SaleDetailsTbl> listSaleDetails = retailAppService.listAllRows(salesTbl.getSaleID());
		InvoicePDFExporter exporter = new InvoicePDFExporter(listSaleDetails);

		exporter.export(response, salesTbl);

	}
}