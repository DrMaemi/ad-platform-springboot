package com.ssg.backendpreassignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.backendpreassignment.config.RestDocsConfig;
import com.ssg.backendpreassignment.dto.CompanyRegReqDto;
import com.ssg.backendpreassignment.dto.ProductAddReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfig.class)
public class ApiTests {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addProducts() throws Exception {
        List<ProductAddReqDto> productAddReqDtos = new ArrayList<>();

        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("이마트")
                .productName("허쉬 초코멜로쿠키 45g")
                .price(600L)
                .stock(10L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("신세계백화점")
                .productName("크리스피롤 12 곡 180g")
                .price(2000L)
                .stock(5L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("삼성전자")
                .productName("갤럭시 22 자급제모델")
                .price(1200000L)
                .stock(0L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("이마트")
                .productName("말랑카우 핸드워시 250ml")
                .price(2600L)
                .stock(6L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("신세계백화점")
                .productName("삼립 미니꿀호떡 322g")
                .price(1200L)
                .stock(4L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("이마트")
                .productName("피코크 어랑손만두 어랑만두 700g")
                .price(6400L)
                .stock(2L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("신세계백화점")
                .productName("빼빼로바 아몬드아이스크림 4입")
                .price(2800L)
                .stock(1L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("이마트")
                .productName("피코크 에이 클래스 우유 900ml")
                .price(2500L)
                .stock(3L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("이마트")
                .productName("아삭달콤 방울토마토 500g")
                .price(4500L)
                .stock(0L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("이마트")
                .productName("[롯데삼강] 돼지바 (70ml*6 입)")
                .price(3000L)
                .stock(1L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("나이키")
                .productName("나이키 덩크로우 흰검")
                .price(129000L)
                .stock(4L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("스타벅스")
                .productName("이달의 원두 500g")
                .price(18500L)
                .stock(4L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("스타벅스")
                .productName("아쿠아머그")
                .price(23000L)
                .stock(7L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("삼성전자")
                .productName("삼성전자 43 인치 스마트모니터")
                .price(480000L)
                .stock(2L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("나이키")
                .productName("나이키 헤리티지 스우시 캡")
                .price(25000L)
                .stock(5L)
                .build());

        this.mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productAddReqDtos))
                )
                .andExpect(status().isCreated())
                .andDo(
                        document("POST_api-products",
                                requestFields(
                                        fieldWithPath("[]").description("An array of products to add"),
                                        fieldWithPath("[].companyName").description("Name of company which produces the product"),
                                        fieldWithPath("[].productName").description("Product name"),
                                        fieldWithPath("[].price").description("Product price"),
                                        fieldWithPath("[].stock").description("Product stock")
                                ),
                                responseFields(
                                        fieldWithPath("timestamp").description("API requested time"),
                                        fieldWithPath("code").description("HTTP status code"),
                                        fieldWithPath("status").description("HTTP status"),
                                        fieldWithPath("result").description("The number of records created")
                                )
                        )
                );
    }

    @Test
    void addProductsValidation() throws Exception {
        List<ProductAddReqDto> productAddReqDtos = new ArrayList<>();

        productAddReqDtos.add(ProductAddReqDto.builder()
                .productName("허쉬 초코멜로쿠키 45g")
                .price(600L)
                .stock(-10L)
                .build());
        productAddReqDtos.add(ProductAddReqDto.builder()
                .companyName("신세계백화점")
                .price(-2000L)
                .stock(5L)
                .build());

        this.mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productAddReqDtos))
                )
                .andExpect(status().isBadRequest())
                .andDo(
                        document("POST_api-products-validation",
                                requestFields(
                                        fieldWithPath("[]").description("An array of products to add"),
                                        fieldWithPath("[].companyName").description("Name of company which produces the product").optional(),
                                        fieldWithPath("[].productName").description("Product name").optional(),
                                        fieldWithPath("[].price").description("Product price").optional(),
                                        fieldWithPath("[].stock").description("Product stock").optional()
                                ),
                                responseFields(
                                        fieldWithPath("timestamp").description("API requested time"),
                                        fieldWithPath("code").description("HTTP status code"),
                                        fieldWithPath("status").description("HTTP status"),
                                        fieldWithPath("result").description("An array of validation result"),
                                        fieldWithPath("result.[].field").description("The field which is invalid"),
                                        fieldWithPath("result.[].message").description("Description message"),
                                        fieldWithPath("result.[].rejectedValue").description("The value rejected").optional()
                                )
                        )
                );
    }

    @Test
    void registerCompany() throws Exception {
        CompanyRegReqDto companyRegReqDto = CompanyRegReqDto.builder()
                .companyName("이마트")
                .businessRegistrationNumber("123-45-67890")
                .phoneNumber("010-123-4567")
                .address("주소")
                .build();

        this.mockMvc.perform(
                        post("/api/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(companyRegReqDto))
                )
                .andExpect(status().isOk())
                .andDo(
                        document("POST_api-company-register",
                                requestFields(
                                        fieldWithPath("companyName").description("Company name which is appeared in product list"),
                                        fieldWithPath("businessRegistrationNumber").description("Company's business registration number"),
                                        fieldWithPath("phoneNumber").description("Company's phone number"),
                                        fieldWithPath("address").description("Company's address")
                                ),
                                responseFields(
                                        fieldWithPath("timestamp").description("API requested time"),
                                        fieldWithPath("code").description("HTTP status code"),
                                        fieldWithPath("status").description("HTTP status"),
                                        fieldWithPath("result").description("Processed company info"),
                                        fieldWithPath("result.id").description("Processed company's ID"),
                                        fieldWithPath("result.companyName").description("Processed company's name"),
                                        fieldWithPath("result.businessRegistrationNumber").description("Processed company's business registration number"),
                                        fieldWithPath("result.phoneNumber").description("Processed company's phone number"),
                                        fieldWithPath("result.address").description("Processed company's address")
                                )
                        )
                );
    }

    @Test
    void registerCompanyValidation() throws Exception {
        CompanyRegReqDto companyRegReqDto = CompanyRegReqDto.builder()
                .companyName("등록되지 않은 업체 이름")
                .businessRegistrationNumber("123456-78901")
                .phoneNumber("010-1234567")
                .build();

        this.mockMvc.perform(
                        post("/api/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(companyRegReqDto))
                )
                .andExpect(status().isBadRequest())
                .andDo(
                        document("POST_api-company-validation",
                                requestFields(
                                        fieldWithPath("companyName").description("Company name which is not appeared in product list"),
                                        fieldWithPath("businessRegistrationNumber").description("Company's business registration number"),
                                        fieldWithPath("phoneNumber").description("Company's phone number"),
                                        fieldWithPath("address").description("Company's address")
                                ),
                                responseFields(
                                        fieldWithPath("timestamp").description("API requested time"),
                                        fieldWithPath("code").description("HTTP status code"),
                                        fieldWithPath("status").description("HTTP status"),
                                        fieldWithPath("result").description("Validation result"),
                                        fieldWithPath("result.[].field").description("The field which is invalid"),
                                        fieldWithPath("result.[].message").description("Description message"),
                                        fieldWithPath("result.[].rejectedValue").description("The value rejected").optional()
                                )
                        )
                );
    }

    @Test
    void getCompanies() throws Exception {
        this.mockMvc.perform(
                        get("/api/companies")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo( // REST docs 문서 작성
                        document("GET_api-companies",
                                responseFields(
                                        fieldWithPath("timestamp").description("API requested time"),
                                        fieldWithPath("code").description("HTTP status code"),
                                        fieldWithPath("status").description("HTTP stats"),
                                        fieldWithPath("result").description("HTTP result - An array of companies"),
                                        fieldWithPath("result.[].id").description("Company's ID"),
                                        fieldWithPath("result.[].name").description("Company's name"),
                                        fieldWithPath("result.[].businessRegistrationNumber").description("Company's business registration number"),
                                        fieldWithPath("result.[].phoneNumber").description("Company's phone number"),
                                        fieldWithPath("result.[].address").description("Company's address"),
                                        fieldWithPath("result.[].createdDate").description("Date when the record created"),
                                        fieldWithPath("result.[].lastModifiedDate").description("Last date when the record modified"),
                                        fieldWithPath("result.[].address").description("Last date when the record modified"),
                                        fieldWithPath("result.[].productDtos.[]").description("An array of products registered according to the company"),
                                        fieldWithPath("result.[].productDtos.[].id").description("Product ID"),
                                        fieldWithPath("result.[].productDtos.[].companyDto").description("Company who registered the product(it should be null because it is duplicated info)"),
                                        fieldWithPath("result.[].productDtos.[].productName").description("Product name"),
                                        fieldWithPath("result.[].productDtos.[].price").description("Product price"),
                                        fieldWithPath("result.[].productDtos.[].stock").description("Product stock"),
                                        fieldWithPath("result.[].productDtos.[].createdDate").description("Date when the product registered"),
                                        fieldWithPath("result.[].productDtos.[].lastModifiedDate").description("Last date when the product info modified")
                                )
                        )
                );
    }

    @Test
    void updateCompanyExceptName() throws Exception {
        CompanyRegReqDto companyRegReqDto = CompanyRegReqDto.builder()
                .companyName("이마트")
                .phoneNumber("010-2345-6789")
                .address("수정된 주소")
                .build();

        this.mockMvc.perform(
                        patch("/api/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(companyRegReqDto))
                )
                .andExpect(status().isOk())
                .andDo(
                        document("POST_api-company-update",
                                requestFields(
                                        fieldWithPath("companyName").description("Company name which is registered in the repo"),
                                        fieldWithPath("businessRegistrationNumber").description("Company's business registration number to update"),
                                        fieldWithPath("phoneNumber").description("Company's phone number to update"),
                                        fieldWithPath("address").description("Company's address to update")
                                ),
                                responseFields(
                                        fieldWithPath("timestamp").description("API requested time"),
                                        fieldWithPath("code").description("HTTP status code"),
                                        fieldWithPath("status").description("HTTP status"),
                                        fieldWithPath("result").description("Updated company info"),
                                        fieldWithPath("result.id").description("Updated company's ID"),
                                        fieldWithPath("result.companyName").description("Updated company's name"),
                                        fieldWithPath("result.businessRegistrationNumber").description("Updated company's business registration number"),
                                        fieldWithPath("result.phoneNumber").description("Updated company's phone number"),
                                        fieldWithPath("result.address").description("Updated company's address")
                                )
                        )
                );
    }
}
