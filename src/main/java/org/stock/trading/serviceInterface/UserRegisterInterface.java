package org.stock.trading.serviceInterface;

import org.springframework.stereotype.Component;
import org.stock.trading.dto.UserRegistryDto;
@Component
public interface UserRegisterInterface {
    UserRegistryDto saveUser(UserRegistryDto userRegistryDto);
}
