package org.stock.trading.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column
    private String userName;
    @Column
    private String emailId;
    @Column
    private String password;
    @Column
    private Double accountBalance;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "users_stock",joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "stockId"))
    private Set<StockEntity> stock;
}
