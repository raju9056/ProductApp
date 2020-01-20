package com.rakuten.training.web;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;



@RunWith(SpringRunner.class)
@WebMvcTest({ProductController.class})
public class ProductControllerUnitTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService service;
	
  @Test
  public void getProductById_Returns_OK_With_Correct_Product_If_Found() throws Exception {
    //Arrange
	  Product found = new Product("test",123.0f,100);
	  int id = 1;
	  found.setId(id);
	  Mockito.when(service.findById(id)).thenReturn(found);
	//Act and Assert
	  mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",id))
	  		.andExpect(MockMvcResultMatchers.status().isOk())
	  		.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
  }

  @Test
  public void addProduct_Returns_CREATED_With_Valid_Product() throws Exception {
    // Arrange
    Product added = new Product("test", 123.0f, 100);
    int id = 1;
    added.setId(id);
    ObjectMapper mapper = new ObjectMapper();
    // Act & Assert
    Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(id);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(added)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.header().string("location", "/products/"+ id));
    Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class));
  }
//  
//  @Test
//  public void Delete_Product_Returns_CREATED_With_Valid_Product() throws Exception {
//	  //Arrange
//	  Product added = new Product("test", 123.0f, 100);
//	    int id = 1;
//	    added.setId(id);
//	    ObjectMapper mapper = new ObjectMapper();
//	    Mockito.when(service.removeProduct(id).then
//  }
}


