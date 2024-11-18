package org.stock.trading.StockController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stock.trading.dto.UserRegistryDto;
import org.stock.trading.serviceInterface.UserRegisterInterface;

@RestController
@RequestMapping("/userRegister")
@Slf4j
public class UserRegistoryController {

    @Autowired
    private UserRegisterInterface userRegisterInterface;

    @PostMapping
    public ResponseEntity<UserRegistryDto> userRegistry(@RequestBody UserRegistryDto userRegistryDto){

        ResponseEntity<UserRegistryDto> responseEntity;
        log.info("From user registry controller for user:{}", userRegistryDto.getUserName());
        try {
            UserRegistryDto savedDetails = userRegisterInterface.saveUser(userRegistryDto);
            responseEntity = new ResponseEntity<>(savedDetails, HttpStatus.OK);
            log.info("End of user registry ID{}", userRegistryDto.getUserName());
        }catch (Exception e){
            log.error("Error while saving user details: userId:{},ErrorMesage:{},Error:{}", userRegistryDto.getUserName(),e.getMessage(),e);
            responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
