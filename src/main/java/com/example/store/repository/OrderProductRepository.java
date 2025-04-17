package com.example.store.repository;

import com.example.store.entity.OrderProduct;
import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query("select op.orderId from OrderProduct op where op.productId =:productId")
    List<Long> findOrderIdsByProduct(Long productId);

    @Query("select p from Product p join OrderProduct op on op.productId = p.id where op.orderId =:orderId")
    List<Product> findProductsByOrderId(Long orderId);
}
