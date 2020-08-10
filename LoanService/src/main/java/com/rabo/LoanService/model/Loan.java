package com.rabo.LoanService.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanNo;
	private int loanAmount;
	private String loanType;
	private String loanTerm;
	private int loanHandlingFees;
	private String originationAccount;
	private String status;
	private String applicantBusinessName;
	private String applicantBusinessAddress;
	private String applicantBusinesscontact;
	private String applicantBusinessEmail;
	private int applicantBusinessOwnership;
	private String propertyName;
	private String propertyAddress;
	private String typeOfProperty;
	private String propertySize;
	private String TAN;
	private String PAN;
	private int cibilScore;
	private LocalDateTime originationDate;
	private String userId;


	public Loan(int loanNo, int loanAmount, String loanType, String loanTerm, int loanHandlingFees,
			String originationAccount, String status, String applicantBusinessName, String applicantBusinessAddress,
			String applicantBusinesscontact, String applicantBusinessEmail, int applicantBusinessOwnership,
			String propertyName, String propertyAddress, String typeOfProperty, String propertySize, String tAN,
			String pAN, int cibilScore, LocalDateTime originationDate, String userId) {
		super();
		this.loanNo = loanNo;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.loanTerm = loanTerm;
		this.loanHandlingFees = loanHandlingFees;
		this.originationAccount = originationAccount;
		this.status = status;
		this.applicantBusinessName = applicantBusinessName;
		this.applicantBusinessAddress = applicantBusinessAddress;
		this.applicantBusinesscontact = applicantBusinesscontact;
		this.applicantBusinessEmail = applicantBusinessEmail;
		this.applicantBusinessOwnership = applicantBusinessOwnership;
		this.propertyName = propertyName;
		this.propertyAddress = propertyAddress;
		this.typeOfProperty = typeOfProperty;
		this.propertySize = propertySize;
		TAN = tAN;
		PAN = pAN;
		this.cibilScore = cibilScore;
		this.originationDate = LocalDateTime.now();
		this.userId = userId;
	}

	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Loan() {

	}

	public int getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(int loanNo) {
		this.loanNo = loanNo;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public int getLoanHandlingFees() {
		return loanHandlingFees;
	}

	public void setLoanHandlingFees(int loanHandlingFees) {
		this.loanHandlingFees = loanHandlingFees;
	}

	public String getOriginationAccount() {
		return originationAccount;
	}

	public void setOriginationAccount(String originationAccount) {
		this.originationAccount = originationAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplicantBusinessName() {
		return applicantBusinessName;
	}

	public void setApplicantBusinessName(String applicantBusinessName) {
		this.applicantBusinessName = applicantBusinessName;
	}

	public String getApplicantBusinessAddress() {
		return applicantBusinessAddress;
	}

	public void setApplicantBusinessAddress(String applicantBusinessAddress) {
		this.applicantBusinessAddress = applicantBusinessAddress;
	}

	public String getApplicantBusinesscontact() {
		return applicantBusinesscontact;
	}

	public void setApplicantBusinesscontact(String applicantBusinesscontact) {
		this.applicantBusinesscontact = applicantBusinesscontact;
	}

	public String getApplicantBusinessEmail() {
		return applicantBusinessEmail;
	}

	public void setApplicantBusinessEmail(String applicantBusinessEmail) {
		this.applicantBusinessEmail = applicantBusinessEmail;
	}

	public int getApplicantBusinessOwnership() {
		return applicantBusinessOwnership;
	}

	public void setApplicantBusinessOwnership(int applicantBusinessOwnership) {
		this.applicantBusinessOwnership = applicantBusinessOwnership;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getTypeOfProperty() {
		return typeOfProperty;
	}

	public void setTypeOfProperty(String typeOfProperty) {
		this.typeOfProperty = typeOfProperty;
	}

	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}

	public String getTAN() {
		return TAN;
	}

	public void setTAN(String tAN) {
		TAN = tAN;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public LocalDateTime getOriginationDate() {
		return originationDate;
	}

	public void setOriginationDate() {
		this.originationDate = LocalDateTime.now();
	}

}