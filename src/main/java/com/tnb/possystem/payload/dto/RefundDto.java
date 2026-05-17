package com.tnb.possystem.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tnb.possystem.domain.PaymentType;
import com.tnb.possystem.modal.Branch;
import com.tnb.possystem.modal.Order;
import com.tnb.possystem.modal.ShiftReport;
import com.tnb.possystem.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDto {

    private Long id;

    private OrderDto order;

    private Long orderId;

    private String reason;

    private Double amount;

//    private ShiftReport shiftReport;

    private Long shiftReportId;

    private UserDto cashier;

    private String cashierName;

    private BranchDto branch;

    private Long branchId;

    private PaymentType paymentType;

    private LocalDateTime createdAt;
}
