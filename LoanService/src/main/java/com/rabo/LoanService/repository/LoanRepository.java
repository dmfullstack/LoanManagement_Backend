package com.rabo.LoanService.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabo.LoanService.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {


}