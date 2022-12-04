package com.example.kreambidding.model.bidding;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long productId;
    @Column(nullable = false)
    private long userId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BiddingType biddingType;
    @Column(nullable = false)
    private long price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BiddingStatus biddingStatus;
    @CreatedDate
    private LocalDate createdAt;
}
