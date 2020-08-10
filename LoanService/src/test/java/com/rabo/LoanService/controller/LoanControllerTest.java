package com.rabo.LoanService.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabo.LoanService.exception.LoanAlreadyExistsException;
import com.rabo.LoanService.exception.LoanNotFoundException;
import com.rabo.LoanService.model.Loan;
import com.rabo.LoanService.service.LoanService;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private Loan loan;
	@MockBean
	private LoanService loanService;

	@InjectMocks
	private loanController LoanController;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(LoanController).build();
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

	}

	@Test
	public void addLoanSuccess() throws Exception {

		when(loanService.addLoan(loan)).thenReturn(true);

		mockMvc.perform(post("/api/v1/loan").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	}
//	@Ignore
//	@Test
//	public void addLoanFailure() throws Exception {
//		loanService.addLoan(loan);
//		loan.setLoanNo(111);
//	
//		when(loanService.addLoan(loan)).thenReturn(false);
//		mockMvc.perform(post("/api/v1/loan").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
//				.andExpect(MockMvcResultMatchers.status().isConflict()).andDo(MockMvcResultHandlers.print());
//	}

	@Test
	public void updateLoanSuccess() throws Exception {
		loan.setLoanHandlingFees(5000);
		when(loanService.updateLoan(any(), eq(loan.getLoanNo()))).thenReturn(loan);
		mockMvc.perform(put("/api/v1/loan/111").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void updateLoanFailure() throws Exception {
		loan.setLoanNo(111);
		when(loanService.updateLoan(any(), eq(loan.getLoanNo()))).thenThrow(LoanNotFoundException.class);
		mockMvc.perform(put("/api/v1/loan/111").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteLoanSuccess() throws Exception {
		when(loanService.deleteLoan(111)).thenReturn(true);
		mockMvc.perform(delete("/api/v1/loan/111").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteLoanFailure() throws Exception {
		when(loanService.deleteLoan(111)).thenReturn(false);
		mockMvc.perform(delete("/api/v1/loan/111").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void getByLoanNoSuccess() throws Exception {

		when(loanService.getLoanByLoanNo(111)).thenReturn(loan);
		mockMvc.perform(get("/api/v1/loan/111").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getByLoanNoFAilure() throws Exception {

		when(loanService.getLoanByLoanNo(111)).thenThrow(LoanNotFoundException.class);
		mockMvc.perform(get("/api/v1/loan/111").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper objmapper = new ObjectMapper();
			objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objmapper.registerModule(new JavaTimeModule());
			return objmapper.writeValueAsString(obj);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}