package org.stock.trading.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BuyAndSellStockDto {
    private Long stockId;
    private Long userId;
    private Long availableQty;
    private Long stockPrise;
    private LocalDateTime timestamp;
    private LocalDateTime lastUpdated;
}
