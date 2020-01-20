package com.rakuten.training.dal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements ReviewDAO {

  @Autowired EntityManager em;

  @Override
  public Review findById(int id) {
    return em.find(Review.class, id);
  }

  @Override
  public Review save(Review toBeSaved) {

    em.persist(toBeSaved);
    return toBeSaved;
  }

  @Override
  public void deleteById(int id) {
    Review r = em.find(Review.class, id);
    em.remove(r);
  }

  @Override
  public List<Review> findAll() {

    return null;
  }

  @Override
  public List<Review> findByProductId(int productId) {
    TypedQuery<Review> q = em.createQuery("select r from Review r where r.product.id=:id", Review.class) ;
    q.setParameter("id", productId);
    return q.getResultList();
  }
}

