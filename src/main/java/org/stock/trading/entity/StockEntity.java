package org.stock.trading.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;
    @Column
    private String stockName;
    @Column
    private Double stockPrise;
    @Column
    private Long availableQty;
    @Column
    private LocalDateTime timestamp;
    @Column
    private LocalDateTime lastUpdated;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "stock",fetch = FetchType.EAGER)
    private Set<UserEntity> userEntity;



}
