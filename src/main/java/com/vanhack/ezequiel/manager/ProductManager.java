package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.ProductDto;

public interface ProductManager {

    ProductDto retrieveProductById(int productId);
}
