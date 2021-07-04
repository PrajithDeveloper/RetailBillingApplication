package com.projects.retailapp.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.DocumentException;
import com.projects.retailapp.entity.InvoicePDFExporter;
import com.projects.retailapp.entity.ItemTbl;
import com.projects.retailapp.entity.ItemTblValidate;
import com.projects.retailapp.entity.SaleDetailsTbl;
import com.projects.retailapp.entity.SalesTbl;
import com.projects.retailapp.repository.RetailAppRepository;
import com.projects.retailapp.repository.SaleDetailsTblRepository;
import com.projects.retailapp.repository.SalesTblRepository;

@Service
public class RetailAppService {

	@Autowired
	private RetailAppRepository retailAppRepository;

	@Autowired
	private SalesTblRepository salesTblRepository;

	@Autowired
	private SaleDetailsTblRepository saleDetailsTblRepository;
	
	@Autowired
	private ItemTblValidate itemTblValidate;
	
	public List<ItemTbl> getItem() {
		List<ItemTbl> item = retailAppRepository.findAll();
		return item;
	}

	public String saveItem(ItemTbl itemTbl, String MODE) {
		//validating entity
		String validationResult = itemTblValidate.validateEntity(itemTbl, MODE); 
		if(!(validationResult.equals("Success"))) {
			return validationResult;
		}
		//set dateTime
		itemTbl.setWefdate(getDateTime());
		
		retailAppRepository.save(itemTbl);
		return "Success";
	}


	public Optional<ItemTbl> findItem(Long itemID) {
		Optional<ItemTbl> itemTbl = retailAppRepository.findById(itemID);
		return itemTbl;
	}

	public String deleteTbl() {
		if (retailAppRepository.count() == 0) {
			return "no data found";
		}
		retailAppRepository.deleteAll();
		return "deleted table successfully";
	}

	public String deleteItem(Long itemID) {
		retailAppRepository.deleteById(itemID);
		return "item deleted successfully";
	}

	public List<ItemTbl> getItemByBarcode(String barcode) {
		List<ItemTbl> barcodes = retailAppRepository.findItemByBarcodes(barcode);
		return barcodes;
	}
	
	public void saveSaleDetailsTbl(List<SaleDetailsTbl> saleDetailsTbls) {
		
		for(SaleDetailsTbl entity : saleDetailsTbls) {
			// setting salesTbl to saleDetailsTbl
			entity.setSalesTbl(getLastSalesTbl());

			Integer quantity = entity.getQty();
			Long itemID = entity.getItemTbl().getItemID();
			updateStock(quantity, itemID);
			
			//set dateTime
			entity.setWefDate(getDateTime());
			
			saleDetailsTblRepository.save(entity);	
		}
	}

	private void updateStock(Integer quantity, Long itemID) {
		Optional<ItemTbl> result = findItem(itemID);
		ItemTbl itemTbl = result.get();
		Integer newStock = (itemTbl.getStock()) - quantity;
		itemTbl.setStock(newStock);
		retailAppRepository.save(itemTbl);

	}

	public SalesTbl getLastSalesTbl() {
		SalesTbl salesTbl = salesTblRepository.findMaxSaleTbl();
		return salesTbl;
	}

	public List<ItemTbl> getItemByName(String name) {
		List<ItemTbl> itemTbl = retailAppRepository.findItemByName(name);
		return itemTbl;
	}
	
	public LocalDateTime getDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		//String formatDateTime = localDateTime.format(format); 
		return localDateTime;
	}

	public List<SalesTbl> findAllSalesTbl() {
		
		List<SalesTbl> salesTbl = salesTblRepository.findAll();
		return salesTbl;
	}
	
	public List<SaleDetailsTbl> listAllRows(Long saleID){
		return saleDetailsTblRepository.findAllItemsBySaleID(saleID);
	}
	//processing save in one
	public String saveSale(SalesTbl entity) {
		if (entity.getTotalAmount() == null)
			return "Error";
		else if (entity.getTotalAmount() == 0) {
			return "Error";
		}
		//setting datetime to entity
		entity.setWefDate(getDateTime());
		//entering data to salesTbl
		salesTblRepository.save(entity);
		
		//saving saledetailTbls 
		List<SaleDetailsTbl> saleDetailsTbls = entity.getSaleDetailsTbls();
		saveSaleDetailsTbl(saleDetailsTbls);
		return "Success";
		
	}
	
	
}
