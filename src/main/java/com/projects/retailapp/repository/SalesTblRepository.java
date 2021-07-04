package com.projects.retailapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projects.retailapp.entity.SalesTbl;

public interface SalesTblRepository extends JpaRepository<SalesTbl, Long> {
	
	@Query("SELECT salesTbl "
			+ " FROM SalesTbl salesTbl "
			+ "WHERE salesTbl.saleID = (SELECT MAX (salesTbl.saleID) FROM SalesTbl salesTbl) ")
	SalesTbl findMaxSaleTbl();
	
}
