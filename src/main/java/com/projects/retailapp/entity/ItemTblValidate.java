package com.projects.retailapp.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.retailapp.repository.RetailAppRepository;

@Service
public class ItemTblValidate {
	
	@Autowired
	private RetailAppRepository retailAppRepository;
	
	/*
	 * Validating the entity
	 * */
	public String validateEntity(ItemTbl itemTbl, String MODE) {
		
		if (!(validateName(itemTbl.getName())))
			return "Invalid Name";

		else if (!validateBarcode(itemTbl, MODE))
			return "Invalid Barcode";

		else if (itemTbl.getMrp() == (null) || itemTbl.getMrp()<=0)
			return "Invalid Mrp";

		else if (itemTbl.getRate() == (null) || (itemTbl.getRate() > itemTbl.getMrp()) || (itemTbl.getRate()<=0))
			return "Invalid Rate";

		else if (itemTbl.getStock() == (null) || (itemTbl.getStock()<0))
			return "Invalid Stock";
		return "Success";
	}
	//checking name is empty,blank,matches only string
		private boolean validateName(String name) {
			if (name.isEmpty() || name.isBlank() || !(name.matches("^[a-zA-Z ]*$")))
				return false;
			return true;
		}

		private boolean validateBarcode(ItemTbl itemTbl, String MODE) {
			String barcode = itemTbl.getBarcode();
			if (barcode.isEmpty() || barcode.isBlank() || !(barcode.matches("^[0-9]*$")) || barcode.length() != 10)
				return false;
			if (MODE.equals("INSERT")) {
				if (!retailAppRepository.findItemByBarcodes(barcode).isEmpty())
					return false;
			}

			return true;
		}

}
