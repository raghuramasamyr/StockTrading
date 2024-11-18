package org.stock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stock.trading.entity.StockEntity;
import org.stock.trading.entity.UserEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity,Long> {

}
