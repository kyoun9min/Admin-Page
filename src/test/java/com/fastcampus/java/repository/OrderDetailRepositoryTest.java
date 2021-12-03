package com.fastcampus.java.repository;

import com.fastcampus.java.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("OrderRepository 테스트")
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));


        //orderDetail.setOrderGroupId(1L);
        //orderDetail.setItemId(1L);


        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }
}

