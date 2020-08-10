package com.rabo.LoanService.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.LoanService.exception.LoanAlreadyExistsException;
import com.rabo.LoanService.exception.LoanNotFoundException;
import com.rabo.LoanService.model.Loan;
import com.rabo.LoanService.service.LoanService;

@RestController
public class loanController {

	private LoanService service;

	@Autowired
	public loanController(LoanService service) {
		this.service = service;
	}

	@PostMapping("/api/v1/loan")
	public ResponseEntity<?> saveLoan(@RequestBody Loan loan) {
		try {
			loan.setOriginationDate();

			return new ResponseEntity<>(service.addLoan(loan), HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/api/v1/loan/{loanNo}")
	public ResponseEntity<?> deleteLoan(@PathVariable("loanNo") int loanNo) throws LoanNotFoundException {

		ResponseEntity<?> entity;

		Boolean flag = service.deleteLoan(loanNo);

		if (flag) {

			entity = new ResponseEntity<>("Deleted Successfully ", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>("Loan not found for this LoanNo", HttpStatus.NOT_FOUND);
		}

		return entity;
	}

	@PutMapping("/api/v1/loan/{loanNo}")
	public ResponseEntity<?> updateLoan(@RequestBody Loan loan, @PathVariable("loanNo") int loanNo) {

		try {

			return new ResponseEntity<>(service.updateLoan(loan, loanNo), HttpStatus.OK);

		} catch (LoanNotFoundException e) {

			return new ResponseEntity<>("Loan not found...", HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/api/v1/loan/search/{loanNo}")
	public ResponseEntity<?> searchLoan(@PathVariable("loanNo") int loanNo) throws LoanNotFoundException {

		List<Loan> listLoan = service.searchByLoanNo(loanNo);
		return new ResponseEntity<>(listLoan, HttpStatus.OK);

	}

	@GetMapping("/api/v1/loan/{loanNo}")
	public ResponseEntity<?> getLoan(@PathVariable("loanNo") int loanNo) {
		ResponseEntity<?> entity;
		HashMap<String, List<Loan>> map = new HashMap<>();

		List<Loan> listLoan = new LinkedList<>();
		Loan loan = null;
		try {
			loan = service.getLoanByLoanNo(loanNo);

			listLoan.add(loan);
			map.put("getloan", listLoan);
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (LoanNotFoundException e) {
			listLoan.add(loan);
			map.put("getloan", listLoan);
			entity = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
		return entity;
	}

	@GetMapping("/api/v1/loan/user/{userId}")
	public ResponseEntity<?> getAllLoanOfUser(@PathVariable("userId") String userId) {

		List<Loan> allloan = service.getAllLoanByUser(userId);
		return new ResponseEntity<>(allloan, HttpStatus.OK);

	}

	@GetMapping("/api/v1/loan/{userId}/{loanNo}")
	public ResponseEntity<?> searchLoanOfUser(@PathVariable("userId") String userId, @PathVariable("loanNo") int loanNo)
			throws LoanNotFoundException {

		List<Loan> allloan = service.searchLoanOfUser(userId, loanNo);
		return new ResponseEntity<>(allloan, HttpStatus.OK);

	}

	@GetMapping("/api/v1/loan")
	public ResponseEntity<?> getAllLoan() {
		List<Loan> allloan = service.getAllLoan();
		return new ResponseEntity<>(allloan, HttpStatus.OK);

	}
}
