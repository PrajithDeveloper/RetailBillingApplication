package com.projects.retailapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.retailapp.entity.SaleDetailsTbl;

public interface SaleDetailsTblRepository extends JpaRepository<SaleDetailsTbl, Long> {
	
	@Query("SELECT saleDetailsTbl FROM SaleDetailsTbl saleDetailsTbl "
			+ "WHERE saleDetailsTbl.salesTbl.saleID =:saleID " )
	List<SaleDetailsTbl> findAllItemsBySaleID(@Param("saleID") Long saleID);

}
