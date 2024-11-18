package org.stock.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDetailsDto {
    private Long stockId;
    private String stockName;
    private Double stockPrise;
    private Long availableQty;
}
