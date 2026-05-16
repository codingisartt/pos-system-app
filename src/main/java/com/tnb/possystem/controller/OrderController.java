package com.tnb.possystem.controller;

import com.tnb.possystem.domain.OrderStatus;
import com.tnb.possystem.domain.PaymentType;
import com.tnb.possystem.payload.dto.OrderDto;
import com.tnb.possystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto order) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrdersByBranch(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) OrderStatus status) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId,
                customerId, cashierId, paymentType, status));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDto>> getOrderByCashier(
            @PathVariable Long cashierId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    @GetMapping("/today/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getTodayOrder(
            @PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(branchId));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getCustomersOrder(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrder(
            @PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
    }
}
