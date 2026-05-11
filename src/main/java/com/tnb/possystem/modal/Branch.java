package com.tnb.possystem.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalTime openTime;

    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Store store;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private User manager;

    @PrePersist
    protected  void  onCreate(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected  void  onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
