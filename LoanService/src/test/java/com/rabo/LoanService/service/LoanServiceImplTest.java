package com.rabo.LoanService.service;

import com.rabo.LoanService.exception.LoanAlreadyExistsException;
import com.rabo.LoanService.exception.LoanNotFoundException;
import com.rabo.LoanService.model.Loan;
import com.rabo.LoanService.repository.LoanRepository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LoanServiceImplTest {

	@Mock
	LoanRepository rep;

	Loan loan;

	@InjectMocks
	LoanServiceImpl loanService;

	List<Loan> userList = null;
	Optional<Loan> options;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		loan = new Loan();
		loan.setLoanNo(111);
		loan.setLoanAmount(500000);
		loan.setLoanType("Personal Loan");
		loan.setLoanTerm("2 years");
		loan.setLoanHandlingFees(2500);
		loan.setOriginationAccount("100012345");
		loan.setOriginationDate();
		loan.setApplicantBusinessAddress("perundurai,Erode Dt");
		loan.setApplicantBusinesscontact("232561");
		loan.setApplicantBusinessEmail("teggig@gmail.com");
		loan.setApplicantBusinessName("Paper Products");
		loan.setApplicantBusinessOwnership(100);
		loan.setTAN("TAN909090");
		loan.setPAN("PAN7890");
		loan.setCibilScore(700);
		loan.setPropertyAddress("Perundurai, Erode");
		loan.setPropertySize("2 acer");
		loan.setPropertyName("company");
		loan.setStatus("Requested");
		loan.setUserId("JUMAN");

		options = Optional.of(loan);
		// System.out.println(options);

	}

	@Test
	public void addLoanSuccess() throws LoanAlreadyExistsException {
		when(rep.save(loan)).thenReturn(loan);
		boolean lsaved = loanService.addLoan(loan);
		assertEquals(true, lsaved);

	}

	@Test
	public void addLoanFailure() throws LoanAlreadyExistsException {
		when(rep.save(loan)).thenReturn(null);
		boolean lsaved = loanService.addLoan(loan);
		assertEquals(false, false);

	}

	@Test
	public void updateLoan() throws LoanNotFoundException {
		when(rep.findById(111)).thenReturn(options);
		loan.setLoanAmount(100000);

		Loan fetchloan;
		fetchloan = loanService.updateLoan(loan, loan.getLoanNo());
		
		assertEquals(null, fetchloan);

	}

	@Test
	public void deleteLoanSuccess() throws LoanNotFoundException {
		when(rep.findById(111)).thenReturn(options);
		boolean flag = loanService.deleteLoan(loan.getLoanNo());
		assertEquals(true, flag);

	}

	@Test
	public void getLoanById() throws LoanNotFoundException {

		when(rep.findById(111)).thenReturn(options);

		Loan fetchedLoan = loanService.getLoanByLoanNo(loan.getLoanNo());

		assertEquals(loan, fetchedLoan);

	}

}