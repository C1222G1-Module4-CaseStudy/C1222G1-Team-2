package com.example.codegymfoods.repository.bill;

import com.example.codegymfoods.model.bill.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillDetailRepository extends JpaRepository<BillDetail,Integer> {
}
