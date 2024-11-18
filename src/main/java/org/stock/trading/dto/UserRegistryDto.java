package org.stock.trading.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistryDto {

    private Long userId;
    @NotBlank(message = "Username is mandatory")
    private String userName;
    @NotBlank(message = "EmailId is mandatory")
    private String emailId;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Account balance is mandatory")
    private Double accountBalance;
    private List<BuyAndSellStockDto> stock;

}
