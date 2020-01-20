package com.rakuten.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ReviewDAO;

@SpringBootApplication
public class ProductAppApplication {

  public static void main(String[] args) {
    ApplicationContext springContainer =
    SpringApplication.run(ProductAppApplication.class, args); //creates an object of applcation object,server will be launched.
    /*ProductConsoleUI ui = springContainer.getBean(ProductConsoleUI.class);
    ui.createProductWithUI();*/

    ReviewDAO reviewDAO = springContainer.getBean(ReviewDAO.class);
//    Review sample = new Review("uhgrihjg", "trhhh", 5);
//    Review saved = reviewDAO.save(sample);
//    System.out.println("Created review with id: " + saved.getId());
    
//    ProductDAO productDAO = springContainer.getBean(ProductDAO.class);
//    Product p = productDAO.findById(1);
//    System.out.println(p.getName());
//    System.out.println("This product has "+p.getReviews().size()+" reviews");
//    	productDAO.deleteById(1);
  }
}

