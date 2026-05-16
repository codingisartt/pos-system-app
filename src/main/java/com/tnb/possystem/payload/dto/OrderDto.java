package com.tnb.possystem.payload.dto;

import com.tnb.possystem.domain.PaymentType;
import com.tnb.possystem.modal.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;

    private Long customerId;

    private BranchDto branch;

    private UserDto cashier;

    private Customer customer;

    private List<OrderItemDto> items;

    private PaymentType paymentType;

}
