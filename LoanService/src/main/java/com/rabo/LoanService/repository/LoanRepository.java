package com.rabo.LoanService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabo.LoanService.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

//	List<Loan> findAllById(String userId);
	//List<Loan> findLoanByUser(String userId);
}