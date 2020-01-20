package com.rakuten.training.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  ProductDAO dao; // = new ProductDAOInMemImpl();
  @Autowired
  public void setDao(ProductDAO dao) {
    this.dao = dao;
  }

  @Override
  public int addNewProduct(Product toBeAdded) {
    if (toBeAdded.getPrice() * toBeAdded.getQoh() > 10000) {
      Product added = dao.save(toBeAdded);
      return added.getId();
    } else {
      throw new IllegalArgumentException("The monetory value of product is <10000.");
    }
  }

  @Override
  public void removeProduct(int id) {
    Product existing = dao.findById(id);
    if (existing != null) {
      if (existing.getPrice() * existing.getQoh() < 1) {
        throw new IllegalStateException("Monetory value>100000. Can't be deleted");
      } else {
        dao.deleteById(id);
      }
    }
  }

  @Override
  public List<Product> findAll() {
    return dao.findAll();
  }

  @Override
  public Product findById(int id) {
    return dao.findById(id);
  }
}
