package com.rakuten.training.service;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.Mockito;
import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest {

  @Test
  public void addNewProduct_Returns_Valid_Id_When_ProductValue_GTEQ_MinValue() {
    // Arrange
    ProductServiceImpl service = new ProductServiceImpl();
    Product toBeAdded = new Product("test", 100000, 1); // notice 10000x1 >= 10000
    ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
    Product saved = new Product("test", 10000, 1);
    saved.setId(1);
    Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
    service.setDao(mockDAO);
    
    // Act
    int id = service.addNewProduct(toBeAdded);

    // Assert
    assertTrue(id > 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNewProduct_Throws_When_ProductValue_LT_MinValue() {
    // Arrange
    ProductServiceImpl service = new ProductServiceImpl();
    Product toBeAdded = new Product("test", 9999, 1); // notice 10000x1 <= 9999
    // Act
    service.addNewProduct(toBeAdded);
  }
//  @Test(expected = IllegalStateException.class)
//  public void remove_product_Deletes_When_No_ProductValue_LT_MinValue() {
//	  //Arrange
//	  ProductServiceImpl service = new ProductServiceImpl();
//	  ProductDAO dao;
//	    try {
//	     Mockito.when(dao.deleteById(id).thenReturn();
//	    }
//	    catch()
//	    {
//	    	
//	    }
//	    verify(bar, times(1)).someMethod();
//	  }
//  }
  
  
}
