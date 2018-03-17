package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.ProductDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ProductManagerImpl implements ProductManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductDto retrieveProductById(int productId) {

        List<ProductDto> result = entityManager.createNamedQuery("Product.retrieveProductById", ProductDto.class)
                .setParameter("productId", productId).getResultList();

        return (!result.isEmpty()) ? result.get(0) : null;
    }
}