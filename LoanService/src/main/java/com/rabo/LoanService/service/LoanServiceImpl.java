package com.rabo.LoanService.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.rabo.LoanService.exception.LoanNotFoundException;
import com.rabo.LoanService.model.Loan;
import com.rabo.LoanService.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	private LoanRepository rep;

	LoanServiceImpl(LoanRepository rep) {
		this.rep = rep;
	}

	@Override
	public boolean addLoan(Loan loan) {

		rep.save(loan);

		return true;
	}

	@Override
	public boolean deleteLoan(int loanNo) throws LoanNotFoundException {
		Optional<Loan> opt = rep.findById(loanNo);
		if (opt.isPresent()) {
			rep.deleteById(loanNo);
			return true;

		} else {
			throw new LoanNotFoundException("NOT FOUND");
		}

	}

	@Override
	public Loan updateLoan(Loan loan, int loanNo) throws LoanNotFoundException {

		Optional<Loan> opt = rep.findById(loanNo);
		if (opt.isPresent()) {
			return rep.save(loan);
		} else {
			throw new LoanNotFoundException("NOT FOUND");
		}

	}

	@Override
	public Loan getLoanByLoanNo(int loanNo) throws LoanNotFoundException {
		Optional<Loan> opt = rep.findById(loanNo);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new LoanNotFoundException("NOT FOUND");
		}
	}

	@Override
	public List<Loan> searchByLoanNo(int loanNo) throws LoanNotFoundException {

		return rep.findAll().stream().filter(l -> l.getLoanNo() == loanNo).collect(Collectors.toList());
	}

	@Override
	public List<Loan> getAllLoanByUser(String userId) {
		return rep.findAll().stream().filter(l -> l.getUserId().equals(userId)).collect(Collectors.toList());
	}

	@Override
	public List<Loan> getAllLoan() {

		return rep.findAll();
	}

	@Override
	public List<Loan> searchLoanOfUser(String userId, int loanNo) throws LoanNotFoundException

	{
		return rep.findAll().stream().filter(u -> u.getUserId().equalsIgnoreCase(userId))
				.filter(l -> l.getLoanNo() == loanNo).collect(Collectors.toList());
	}

}
