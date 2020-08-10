package com.rabo.LoanService.service;

import java.util.List;

import com.rabo.LoanService.exception.LoanNotFoundException;
import com.rabo.LoanService.model.Loan;

public interface LoanService {

	boolean addLoan(Loan loan);

	boolean deleteLoan(int loanNo) throws LoanNotFoundException;

	Loan updateLoan(Loan loan, int loanNo) throws LoanNotFoundException;

	Loan getLoanByLoanNo(int loanNo) throws LoanNotFoundException;

	List<Loan> searchLoanOfUser(String userId, int loanNo) throws LoanNotFoundException;;

	List<Loan> getAllLoanByUser(String userId);

	List<Loan> searchByLoanNo(int loanNo) throws LoanNotFoundException;

	List<Loan> getAllLoan();

}
