package com.insurance.insuranceApp.controller;


import com.insurance.insuranceApp.model.Product;
import com.insurance.insuranceApp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void testGetAllProducts_success() throws Exception{

        List<Product> productList = new ArrayList<Product>();
        productList.addAll(this.getProductsMock());

        when(productRepository.findAll()).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());

    }


    @Test
    public void testGetProductByName_success() throws Exception{
        List<Product> productList = new ArrayList<Product>();
        productList.addAll(this.getProductsMock());
        String name="name";
        when(productRepository.findProductByName(name)).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/name")
                        .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());
    }


    @Test
    public void testGetProductByLevel_success() throws Exception{
        List<Product> productList = new ArrayList<Product>();
        productList.addAll(this.getProductsMock());
        Integer levelName=1;
        when(productRepository.findProductByLevel(levelName)).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/level/1")
                        .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());
    }

   // @Test
    /*public void testGetKeyword_success() throws Exception{

        Integer levelName=1;
        when(productRepository.findKeyword(levelName)).thenReturn(getKeywords());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/keywords/1")
                        .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }*/

    private String getKeywords() {
        return "Key & Law";
    }


    private List<Product> getProductsMock() {
        List<Product> statisticList = new ArrayList<Product>();
        statisticList.add(this.getSingleStatistic());
        return statisticList;
    }
    private Product getSingleStatistic(){
        Product data=new Product();
        data.setId(1);
        data.setName("Autauga");
        data.setLevel(0);
        return data;
    }

    private Product  getSingleStatisticNullId(){
        Product  data=new Product ();
        data.setId(null);
        data.setName("Autauga");
        data.setLevel(0);
        return data;
    }

}
