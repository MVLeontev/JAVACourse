package ru.vtb.course.lesson5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vtb.course.lesson5.dto.*;
import ru.vtb.course.lesson5.exceptions.DuplicateException;
import ru.vtb.course.lesson5.exceptions.NotFoundException;
import ru.vtb.course.lesson5.repositories.*;
import ru.vtb.course.lesson5.services.AccountServiceable;
import ru.vtb.course.lesson5.services.ProductServiceable;
import ru.vtb.course.lesson5.services.common.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Lesson5ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	TppProductRepo productRepo;
	@Autowired
	TppProductRegisterRepo accountRepo;
	@Autowired
	TppRefProductClassRepo refProductClassRepo;
	@Autowired
	GenerateClientIdByMdmServiceable generateClientIdByMdmServisable;
	@Autowired
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	ProductServiceable productServiceable;
	@Autowired
	AccountServiceable accountServiceable;


	AccountRequest testAccount(){
		AccountRequest acc = new AccountRequest();
		acc.setInstanceId(1L);
		acc.setRegistryTypeCode("03.012.002_47533_ComSoLd");
		acc.setAccountType("Клиентский");
		acc.setCurrencyCode("800");
		acc.setBranchCode("0022");
		acc.setPriorityCode("00");
		acc.setMdmCode("15");
		return acc;
	}

	ProductRequest testProduct(){
		ArrayList<ProductInstanceArrangement> instanceArrangements = new ArrayList<>();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ProductInstanceArrangement pia1 = new ProductInstanceArrangement();
		pia1.setNumber("100/1");
		pia1.setOpeningDate(LocalDate.parse("01-01-2024", dateFormat));
		pia1.setArrangementType(EnumArrangementType.NSO);
		pia1.setCoefficientAction(EnumCoefficientAction.PLUS);
		ProductInstanceArrangement pia2 = new ProductInstanceArrangement();
		pia2.setNumber("100/2");
		pia2.setOpeningDate(LocalDate.parse("02-01-2024", dateFormat));
		pia2.setArrangementType(EnumArrangementType.EZHO);
		pia2.setCoefficientAction(EnumCoefficientAction.PLUS);
		instanceArrangements.add(pia1);
		instanceArrangements.add(pia2);
		ProductRequest pr = new ProductRequest();
		pr.setProductType(EnumProductType.EZHO);
		pr.setProductCode("03.012.002");
		pr.setRegisterType("1");
		pr.setMdmCode("15");
		pr.setContractNumber("001");
		pr.setContractDate(LocalDate.parse("19-01-2024", dateFormat));
		pr.setPriority(1);
		pr.setInterestRatePenalty(BigDecimal.valueOf(10.5));
		pr.setMinimalBalance(BigDecimal.valueOf(100));
		pr.setThresholdAmount(BigDecimal.valueOf(10000));
		pr.setAccountingDetails("Some payment");
		pr.setRateType("0");
		pr.setTaxPercentageRate(BigDecimal.valueOf(20));
		pr.setTechnicalOverdraftLimitAmount(BigDecimal.valueOf(10000));
		pr.setContractId(1111L);
		pr.setBranchCode("0022");
		pr.setIsoCurrencyCode("800");
		pr.setUrgencyCode("00");
		pr.setReferenceCode(2222L);
		pr.setAdditionalPropertiesVip(new ProductAdditionalPropertiesData(new ArrayList<>(List.of(new ProductAdditionalProperties("RailwayRegionOwn","ABC","Регион принадлежности"), new ProductAdditionalProperties("counter","123","Счетчик")))));
		pr.setInstanceArrangement(instanceArrangements);
		return pr;
	}

	@Test
	@DisplayName("ЭП. Тестирование валидации обязательных полей")
	void checkObligatoryFields() {
		ProductRequest productRequest = new ProductRequest();
		ProductInstanceArrangement productInstanceArrangement = new ProductInstanceArrangement();
		productRequest.setInstanceArrangement(new ArrayList<>(List.of(productInstanceArrangement)));
		ArrayList<String> arrErrMsg = new ArrayList<>();
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<ProductRequest>> violationSet = validator.validate(productRequest);
		violationSet.forEach( a -> arrErrMsg.add(a.getMessage()) );
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <productType> не заполнено"),"Fail <productType>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <productCode> не заполнено"), "Fail <productCode>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <registerType> не заполнено"), "Fail <registerType>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <mdmCode> не заполнено"), "Fail <mdmCode>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <contractNumber> не заполнено"), "Fail <contractNumber>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <contractDate> не заполнено"), "Fail <contractDate>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <priority> не заполнено"), "Fail <priority>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <contractId> не заполнено"), "Fail <contractId>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <branchCode> не заполнено"), "Fail <branchCode>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <isoCurrencyCode> не заполнено"), "Fail <isoCurrencyCode>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <urgencyCode> не заполнено"), "Fail <urgencyCode>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <instanceArrangement/number> не заполнено"), "Fail <instanceArrangement/number>");
		Assertions.assertTrue(arrErrMsg.contains("Имя обязательного параметра <instanceArrangement/openingDate> не заполнено"), "Fail <instanceArrangement/openingDate>");
	}

	@Test
	@DisplayName("Тестирование сервиса CheckAccountByProductAndTypeServic e")
	void testCheckAccountByProductAndTypeService(){
		accountRepo.deleteAll();
		productRepo.deleteAll();
		AccountRequest acc = testAccount();
		TppProduct pr = new TppProduct();
		pr.setProductCodeId(refProductClassRepo.findByValue("03.012.002"));
		pr.setClientId(generateClientIdByMdmServisable.generateClientIdByMdm("15"));
		Long prodId = productRepo.save(pr).getId();
		acc.setInstanceId(prodId);
		TppProductRegister register = new TppProductRegister();
		register.setProductId(productServiceable.findProduct(acc.getInstanceId()));
		register.setType( acc.getRegistryTypeCode()) ;
		register.setCurrencyCode(acc.getCurrencyCode());
		accountRepo.save(register);
		Assertions.assertThrows(DuplicateException.class, () ->  accountServiceable.checkAccountByProductAndType(acc));
	}

	@Test
	@DisplayName("Тестирование сервиса checkAccountByProductRegisterTypeCodeService")
	void testCheckAccountByProductRegisterTypeCodeService(){
		accountRepo.deleteAll();
		productRepo.deleteAll();
		AccountRequest acc = testAccount();
		TppProduct pr = new TppProduct();
		pr.setProductCodeId(refProductClassRepo.findByValue("03.012.002"));
		pr.setClientId(generateClientIdByMdmServisable.generateClientIdByMdm("15"));
		Long prodId = productRepo.save(pr).getId();
		acc.setInstanceId(prodId);
		TppProductRegister register = new TppProductRegister();
		register.setProductId(productServiceable.findProduct(acc.getInstanceId()));
		register.setType( acc.getRegistryTypeCode()) ;
		register.setCurrencyCode(acc.getCurrencyCode());
		accountRepo.save(register);
		productServiceable.checkAccountByProductRegisterTypeCode(acc);
		acc.setRegistryTypeCode("03.012.002_47533_###");
		Assertions.assertThrows(NotFoundException.class, () -> productServiceable.checkAccountByProductRegisterTypeCode(acc));
	}

	@Test
	@DisplayName("Тестирование сервиса CheckProductByNumService")
	void testCheckProductByNumService(){
		accountRepo.deleteAll();
		productRepo.deleteAll();
		TppProduct pr = new TppProduct();
		pr.setNumber("007");
		pr.setProductCodeId(refProductClassRepo.findByValue("03.012.002"));
		pr.setClientId(generateClientIdByMdmServisable.generateClientIdByMdm("15"));
		Long prodId = productRepo.save(pr).getId();
		Assertions.assertThrows(DuplicateException.class, () -> productServiceable.checkProductByNum("007") );
	}

	@Test
	@DisplayName("Тестирование сервиса СheckProductArrangementByNumService")
	void testCheckProductArrangementByNumService() {
		accountRepo.deleteAll();
		productRepo.deleteAll();
		TppProduct pr = new TppProduct();
		pr.setNumber("100/1");
		pr.setAgreementId(7L);
		Long prodId = productRepo.save(pr).getId();
		ProductRequest request = testProduct();
		Assertions.assertThrows(DuplicateException.class, () -> productServiceable.checkProductArrangementByNum(request));

	}
	@Test
	@DisplayName("ЭП. Тестирование создания продукта, ДС, регистра")
	void createAllTppProduct() throws Exception {
		accountRepo.deleteAll();
		productRepo.deleteAll();

		ProductRequest pr = testProduct();

		MvcResult mvcResult = mockMvc.perform(post("/corporate-settlement-instance/create")
					.content(mapper.writeValueAsString(pr))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				//.andExpect(content().string(containsString("Имя обязательного параметра ")))
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());

		List<TppProduct> tppProducts = productRepo.findAll();
		List<TppProductRegister> tppProductRegisters = accountRepo.findAll();
	    Assertions.assertEquals(3, tppProducts.size(), "Ошибка заполнения экземпляра продукта и ДС");
		Assertions.assertFalse(tppProductRegisters.isEmpty(), "Ошибка заполнения продуктового регистра");
	}

	@Test
	@DisplayName("Тестирование создания продуктового регистра")
	void createAccount() throws Exception {
		accountRepo.deleteAll();
		productRepo.deleteAll();

		TppProduct pr = new TppProduct();
		pr.setProductCodeId(refProductClassRepo.findByValue("03.012.002"));
		pr.setClientId(generateClientIdByMdmServisable.generateClientIdByMdm("15"));
		Long prodId = productRepo.save(pr).getId();
		AccountRequest acc = testAccount();
		acc.setInstanceId(prodId);

		MvcResult mvcResult = mockMvc.perform(post("/corporate-settlement-account/create")
						.content(mapper.writeValueAsString(acc))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());

		List<TppProductRegister> tppProductRegisters = accountRepo.findAll();
		TppProduct[] arrangements = productRepo.findByAgreementId(1L);
		Assertions.assertFalse(tppProductRegisters.isEmpty(), "Ошибка заполнения продуктового регистра");
	}
}
