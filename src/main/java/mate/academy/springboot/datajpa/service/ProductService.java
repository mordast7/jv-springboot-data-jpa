package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product get(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> getProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getProductsByCategories(List<Long> categoryIds);
}