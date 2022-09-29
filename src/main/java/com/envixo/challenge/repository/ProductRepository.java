package com.envixo.challenge.repository;

import com.envixo.challenge.entity.Product;
import com.envixo.challenge.repository.intf.IProductRepository;
import org.hibernate.QueryException;
import org.hibernate.TransactionException;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements IProductRepository {

    private static final Logger LOG = Logger.getLogger(ProductRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Product save(Product product) {
        try {
            product = entityManager.merge(product);
        } catch (PersistenceException e) {
            LOG.error("Error persisting the product", e);
        } finally {
            if (entityManager.isOpen()) {
                try {
                    entityManager.flush();
                } catch (TransactionException e) {
                    LOG.error("Error flushing transaction", e);
                }
            }
        }
        return product;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        try {
            product = entityManager.find(Product.class, id);
        } catch (EntityNotFoundException e) {
            LOG.error("Could not retrieve product by Id: {}", id, e);
        }
        return product;
    }

    @Override
    public Product findByCategory(String category) {
        Product product = null;
        try {
            CriteriaQuery<Product> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Product.class);
            Root<Product> productRoot = criteriaQuery.from(Product.class);
            Predicate predicate = entityManager.getCriteriaBuilder().equal(productRoot.get("category"), category);
            product = entityManager.createQuery(criteriaQuery.where(predicate)).getSingleResult();
        } catch (QueryException | EntityNotFoundException e) {
            LOG.error("Could not retrieve product by category: {}", category, e);
        }
        return product;
    }

    @Override
    public Product findByTag(String tag) {
        Product product = null;
        try {
            CriteriaQuery<Product> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Product.class);
            Root<Product> productRoot = criteriaQuery.from(Product.class);
            Predicate predicate = entityManager.getCriteriaBuilder().equal(productRoot.get("tag"), tag);
            product = entityManager.createQuery(criteriaQuery.where(predicate)).getSingleResult();
        } catch (QueryException | EntityNotFoundException e) {
            LOG.error("Could not retrieve product by tag: {}", tag, e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            CriteriaQuery<Product> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Product.class);
            productList = entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(Product.class))).getResultList();
        } catch (QueryException | EntityNotFoundException e) {
            LOG.error("Could not retrieve a list of products", e);
        }
        return productList;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Product product) {
        try {
            entityManager.remove(product);
        } catch (TransactionException e) {
            LOG.error("Could not delete the object product", e);
        } finally {
            if (entityManager.isOpen()) {
                try {
                    entityManager.flush();
                } catch (TransactionException e) {
                    LOG.error("Error flushing transaction", e);
                }
            }
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        try {
            Product productEntity = entityManager.find(Product.class, id);
            entityManager.remove(productEntity);
        } catch (TransactionException e) {
            LOG.error("Could not delete the product by id: {}", id, e);
        } finally {
            if (entityManager.isOpen()) {
                try {
                    entityManager.flush();
                } catch (TransactionException e) {
                    LOG.error("Error flushing transaction", e);
                }
            }
        }
    }
}

