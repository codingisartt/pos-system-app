package com.tnb.possystem.service;

import com.tnb.possystem.domain.OrderStatus;
import com.tnb.possystem.domain.PaymentType;
import com.tnb.possystem.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto getOrderById(Long orderId) throws Exception;
    List<OrderDto> getOrdersByBranch(Long branchId,
                                     Long customerId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus status) throws Exception;
    List<OrderDto> getOrderByCashier(Long cashierId) throws Exception;
    void deleteOrder(Long orderId) throws Exception;
    List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception;
    List<OrderDto> getOrderByCustomerId(Long customerId) throws Exception;
    List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;
}
