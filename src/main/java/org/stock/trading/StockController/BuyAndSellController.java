package org.stock.trading.StockController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stock.trading.dto.BuyAndSellStockDto;
import org.stock.trading.dto.StockDetailsDto;
import org.stock.trading.dto.UserRegistryDto;
import org.stock.trading.entity.UserEntity;
import org.stock.trading.serviceInterface.BuyAndSellStockInterface;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/stock")
public class BuyAndSellController {

    @Autowired
    private BuyAndSellStockInterface buyAndSellStockInterface;

    @PostMapping("/buy")
    public ResponseEntity<UserRegistryDto> buyStock(@RequestBody BuyAndSellStockDto stockDetails){
        log.info("from buyStock api for ID:{}",stockDetails);
        UserRegistryDto stock = null;
        try {
           stock = buyAndSellStockInterface.buyStock(stockDetails.getUserId(),
                   stockDetails.getStockId(), stockDetails.getAvailableQty());
       }catch (Exception e){
           log.error("Error while buying stock {},message:{}",stockDetails.getStockId(),e);
           return new ResponseEntity<>(stock, HttpStatus.INTERNAL_SERVER_ERROR);
       }
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<StockDetailsDto> createStock(@RequestBody StockDetailsDto stockDetailsDto){
        //buyAndSellStockInterface.createStock(stockDetailsDto);
        return  new ResponseEntity<>(buyAndSellStockInterface.createStock(stockDetailsDto),HttpStatus.OK);
    }

    @GetMapping("/userstock")
    ResponseEntity<List<UserEntity>> getUserStock(@RequestParam(required = false) Long userid){
        return  new ResponseEntity<>(buyAndSellStockInterface.getUserStock(userid), HttpStatus.OK);
    }

}
