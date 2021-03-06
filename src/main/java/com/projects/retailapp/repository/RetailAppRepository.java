package com.projects.retailapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.projects.retailapp.entity.ItemTbl;

public interface RetailAppRepository extends JpaRepository<ItemTbl, Long> {	

	/*
	 * To get itemTbl by barcode
	 * 
	 * */
	@Query("SELECT itemTbl "
			+"FROM ItemTbl itemTbl "
			+"WHERE itemTbl.barcode like %:barcode% ")
	List<ItemTbl> findItemByBarcodes(@Param("barcode") String barcode);
		
	/*
	 * To get itemTbl by name
	 * 
	 * */
	@Query("SELECT itemTbl FROM ItemTbl itemTbl "
			+ "WHERE itemTbl.name like %:name% ")
	List<ItemTbl> findItemByName(@Param("name") String name);
	
}
