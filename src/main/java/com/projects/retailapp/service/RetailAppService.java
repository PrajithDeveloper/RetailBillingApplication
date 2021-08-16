package com.projects.retailapp.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	/*
	 * Find all items
	 */
	public List<ItemTbl> getItem() {
		List<ItemTbl> item = retailAppRepository.findAll();
		return item;
	}

	/*
	 * Save new Item
	 */
	public String saveItem(ItemTbl itemTbl, String MODE) {
		// validating entity
		String validationResult = itemTblValidate.validateEntity(itemTbl, MODE);
		if (!(validationResult.equals("Success"))) {
			return validationResult;
		}
		// set dateTime
		itemTbl.setWefdate(getDateTime());

		retailAppRepository.save(itemTbl);
		return "Success";
	}

	/*
	 * Finds the Item by itemID
	 */
	public Optional<ItemTbl> findItem(Long itemID) {
		Optional<ItemTbl> itemTbl = retailAppRepository.findById(itemID);
		return itemTbl;
	}


	/*
	 * Delete Item by itemID
	 */
	public String deleteItem(Long itemID) {
		retailAppRepository.deleteById(itemID);
		return "item deleted successfully";
	}

	/*
	 * Find item by barcode
	 */
	public List<ItemTbl> getItemByBarcode(String barcode) {
		List<ItemTbl> barcodes = retailAppRepository.findItemByBarcodes(barcode);
		return barcodes;
	}

	/*
	 * adding sale details to SaleDetailsTbl
	 */
	public void saveSaleDetailsTbl(List<SaleDetailsTbl> saleDetailsTbls) {

		for (SaleDetailsTbl entity : saleDetailsTbls) {
			// setting salesTbl to saleDetailsTbl
			entity.setSalesTbl(getLastSalesTbl());

			Integer quantity = entity.getQty();
			Long itemID = entity.getItemTbl().getItemID();
			updateStock(quantity, itemID);

			// set dateTime
			entity.setWefDate(getDateTime());

			saleDetailsTblRepository.save(entity);
		}
	}

	/*
	 * Updating stock after sale
	 */
	private void updateStock(Integer quantity, Long itemID) {
		Optional<ItemTbl> result = findItem(itemID);
		ItemTbl itemTbl = result.get();
		Integer newStock = (itemTbl.getStock()) - quantity;
		itemTbl.setStock(newStock);
		retailAppRepository.save(itemTbl);

	}

	/*
	 * Find last added sales from SalesTbl
	 */
	public SalesTbl getLastSalesTbl() {
		SalesTbl salesTbl = salesTblRepository.findMaxSaleTbl();
		return salesTbl;
	}

	/*
	 * Find item by name
	 */
	public List<ItemTbl> getItemByName(String name) {
		List<ItemTbl> itemTbl = retailAppRepository.findItemByName(name);
		return itemTbl;
	}

	/*
	 * to get current DateTime
	 */
	public LocalDateTime getDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}

	/*
	 * to get all salestbls
	 */
	public List<SalesTbl> findAllSalesTbl() {
		List<SalesTbl> salesTbl = salesTblRepository.findAll();
		return salesTbl;
	}

	/*
	 * to get all saleDetails by saleID
	 */
	public List<SaleDetailsTbl> listAllRows(Long saleID) {
		return saleDetailsTblRepository.findAllItemsBySaleID(saleID);
	}

	/*
	 * Save new Sale
	 */
	public String saveSale(SalesTbl entity) {
		if (entity.getTotalAmount() == null)
			return "Error";
		else if (entity.getTotalAmount() == 0) {
			return "Error";
		}
		// setting datetime to entity
		entity.setWefDate(getDateTime());
		// entering data to salesTbl
		salesTblRepository.save(entity);

		// saving saledetailTbls
		List<SaleDetailsTbl> saleDetailsTbls = entity.getSaleDetailsTbls();
		saveSaleDetailsTbl(saleDetailsTbls);
		return "Success";
	}
}
