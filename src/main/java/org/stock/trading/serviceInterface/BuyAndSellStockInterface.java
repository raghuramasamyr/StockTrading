package org.stock.trading.serviceInterface;

import org.springframework.stereotype.Component;
import org.stock.trading.dto.StockDetailsDto;
import org.stock.trading.dto.UserRegistryDto;
import org.stock.trading.entity.UserEntity;

import java.util.List;

@Component
public interface BuyAndSellStockInterface {

    UserRegistryDto buyStock(Long customerId, Long stockId, Long stkQuantity);
    UserRegistryDto sellStock(Long customerId, Long stockId, Long stkQuantity);
    StockDetailsDto createStock(StockDetailsDto stockDetailsDto);
    List<UserEntity> getUserStock(Long userId);
}
