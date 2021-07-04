package com.projects.retailapp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.projects.retailapp.entity.InvoicePDFExporter;
import com.projects.retailapp.entity.ItemTbl;
import com.projects.retailapp.entity.SaleDetailsTbl;
import com.projects.retailapp.entity.SalesTbl;
import com.projects.retailapp.service.RetailAppService;


@Controller
@RequestMapping("retailapp")
public class RetailAppController {
	@Autowired
	private RetailAppService retailAppService;
	
	private String MODE=null;

	@RequestMapping("login")
	public String loginPage() {
		return "loginPage";
	}
	@RequestMapping("main")
	public String mainPage() {
		return "MainPage";
	}
	
	@RequestMapping("getItem")
	@ResponseBody
	public List<ItemTbl> getItem() {
		List<ItemTbl> itemTbl = retailAppService.getItem();
		return itemTbl;
	}
	
	@PostMapping("save")
	@ResponseBody
	public String saveItem(ItemTbl itemTbl,BindingResult bindingResult){
		MODE = "INSERT";
		return  retailAppService.saveItem(itemTbl, MODE);
		
	}
	
	@PostMapping("update")
	@ResponseBody
	public String updateItem(ItemTbl itemTbl, BindingResult bindingResult) {
		MODE = "UPDATE";
		return retailAppService.saveItem(itemTbl, MODE);
	
	}
	
	@GetMapping("find")
	@ResponseBody
	public Optional<ItemTbl> findItem(Long itemID) {
		Optional<ItemTbl> itemTbl = retailAppService.findItem(itemID);
		return itemTbl;
	}
	
	@RequestMapping("deleteAll")
	@ResponseBody
	public String deleteTbl() {
		return retailAppService.deleteTbl();
		
	}
	
	@PostMapping("delete")
	@ResponseBody
	public String deleteItem(Long itemID) {
		return retailAppService.deleteItem(itemID);
	}
	
	@GetMapping("getItems")
	@ResponseBody
	public List<ItemTbl> getItemByBarcode(String barcode){
		List<ItemTbl> barcodes = retailAppService.getItemByBarcode(barcode);
		return barcodes;
	}
	
	@PostMapping("saveSale")
	@ResponseBody
	public String saveSale(@RequestBody SalesTbl salesTbl)throws Exception {
		return retailAppService.saveSale(salesTbl);
	}
	
	@GetMapping("getItemByName")
	@ResponseBody
	public List<ItemTbl> getItemByName(String name){
		List<ItemTbl> itemTbl = retailAppService.getItemByName(name);
		return itemTbl;
	}
	
	@GetMapping("getAllSalesTbl")
	@ResponseBody
	public List<SalesTbl> getSalesTbl(){
		List<SalesTbl> salesTbl = retailAppService.findAllSalesTbl();
		return salesTbl;
	}
	
	@GetMapping("exportPdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        
	        SalesTbl salesTbl =retailAppService.getLastSalesTbl();      
	        List<SaleDetailsTbl> listSaleDetails = retailAppService.listAllRows(salesTbl.getSaleID());
	        InvoicePDFExporter exporter = new InvoicePDFExporter(listSaleDetails);
	       
	        exporter.export(response, salesTbl);
	          
	    } 
}