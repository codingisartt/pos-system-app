package com.tnb.possystem.service.impl;

import com.tnb.possystem.domain.OrderStatus;
import com.tnb.possystem.domain.PaymentType;
import com.tnb.possystem.mapper.OrderMapper;
import com.tnb.possystem.modal.*;
import com.tnb.possystem.payload.dto.OrderDto;
import com.tnb.possystem.repository.OrderItemRepository;
import com.tnb.possystem.repository.OrderRepository;
import com.tnb.possystem.repository.ProductRepository;
import com.tnb.possystem.service.OrderService;
import com.tnb.possystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        User cashier = userService.getCurrentUser();

        Branch branch = cashier.getBranch();
        if (branch == null) {
            throw new Exception("Cashier's branch not found");
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .paymentType(orderDto.getPaymentType())
                .build();
        List<OrderItem> orderItems = orderDto.getItems().stream().map(
                itemDto->{
                    Product product = productRepository.findById(itemDto.getProductId())
                            .orElseThrow(()-> new EntityNotFoundException("Product not found"));
                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice() * itemDto.getQuantity())
                            .order(order)
                            .build();
                }
        ).toList();
        order.setItems(orderItems);
        double total = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
                .map(OrderMapper::toDto).orElseThrow(
                ()-> new Exception("Order not found with id: " + orderId)
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId,
                                            Long customerId,
                                            Long cashierId,
                                            PaymentType paymentType,
                                            OrderStatus status) throws Exception {
        return orderRepository.findByBranchId(branchId).stream()
                .filter(order -> customerId==null ||
                        (order.getCustomer()!=null &&
                                order.getCustomer().getId().equals(customerId)))
                .filter(order -> customerId==null ||
                        (order.getCashier()!=null &&
                                order.getCashier().getId().equals(cashierId)))
                .filter(order -> paymentType==null ||
                        order.getPaymentType()==paymentType)
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) throws Exception {
        return orderRepository.findByCashierId(cashierId).stream()
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new Exception("Order not found with id: " + orderId)
        );
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId,start,end)
                .stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCustomerId(Long customerId) throws Exception {
        return orderRepository.findByCustomerId(customerId)
                .stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId)
                .stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }
}
