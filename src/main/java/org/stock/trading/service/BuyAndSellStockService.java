package org.stock.trading.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.stock.trading.dto.StockDetailsDto;
import org.stock.trading.dto.UserRegistryDto;
import org.stock.trading.entity.StockEntity;
import org.stock.trading.entity.UserEntity;
import org.stock.trading.repository.StockRepository;
import org.stock.trading.repository.UserRepository;
import org.stock.trading.serviceInterface.BuyAndSellStockInterface;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class BuyAndSellStockService implements BuyAndSellStockInterface {

    private static final Logger log = LoggerFactory.getLogger(BuyAndSellStockService.class);
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRegistryDto buyStock(Long customerId, Long stockId, Long stkQuantity) {

        log.info("from buying stock for customerId{},StockId{},quantity:{}",customerId,stockId,stkQuantity);
        UserRegistryDto stockDetail = null;
        UserEntity stockedBuyed = null;
        Optional<StockEntity> stockDetails = stockRepository.findById(stockId);
            if (stockDetails.isPresent() && stockDetails.get().getAvailableQty() >= stkQuantity){
                StockEntity stock = stockDetails.get();
                Double totalStockAmt = stock.getStockPrise() * stkQuantity;
                UserEntity userDetails = userRepository.findById(customerId).get();
                if (totalStockAmt <= userDetails.getAccountBalance()){
                    log.info("Stock processed totalStockAmt {}",totalStockAmt);
                    userDetails.setAccountBalance(userDetails.getAccountBalance() - totalStockAmt);
                    stock.setAvailableQty(stock.getAvailableQty() - stkQuantity);
                    stockRepository.save(stock);
                    log.info("Stock processed post saving totalStockAmt {}",totalStockAmt);
                    StockEntity userStock = userDetails.getStock().stream().filter(usrStkId -> stockId == usrStkId.getStockId()).findFirst().orElse(null);
                    if (userStock == null){
                        userStock = new StockEntity();
                        userStock.setStockName(stock.getStockName());
                        userStock.setStockId(stock.getStockId());
                        userStock.setStockPrise(totalStockAmt);
                        userStock.setAvailableQty(stkQuantity);
                        userStock.setTimestamp(LocalDateTime.now());
                        userStock.setLastUpdated(LocalDateTime.now());
                        userDetails.getStock().add(userStock);

                    }else{
                        userStock.setStockPrise(userStock.getStockPrise() + totalStockAmt);
                        userStock.setAvailableQty(userStock.getAvailableQty() + stkQuantity);
                        userStock.setLastUpdated(LocalDateTime.now());
                    }

                    log.info("Stock processed userStock {}",userStock);
                    stockedBuyed =  userRepository.save(userDetails);

                    stockDetail = modelMapper.map(userDetails, UserRegistryDto.class);
                    log.info("Stock processed successfully");
                }else {
                    log.error("Error while buying stock: InSufficient balance : customerId{},StockId{},quantity:{}",customerId,stockId,stkQuantity);
                    throw new IllegalStateException("InSufficient balance");
                }
            }else {
                log.error("Error while buying stock: Stock not available : customerId{},StockId{},quantity:{}",customerId,stockId,stkQuantity);
                throw new IllegalStateException("Stock not available");
            }

        return stockDetail;
    }


    @Override
    public UserRegistryDto sellStock(Long customerId, Long stockId, Long stkQuantity) {

        return null;
    }

    @Override
    public StockDetailsDto createStock(StockDetailsDto stockDetailsDto) {
        log.info("from createStock:{}",stockDetailsDto);
        StockEntity stocktoSave = modelMapper.map(stockDetailsDto,StockEntity.class);
        StockEntity stockSaved = stockRepository.save(stocktoSave);
        log.info("Stock saved succesfully:{}",stockSaved);
        return modelMapper.map(stockSaved, StockDetailsDto.class);
    }

    public List<UserEntity> getUserStock(Long userId){
        List<UserEntity> us = null;
        if(userId == null){
            us = userRepository.findAll();
        }else{
            us = List.of(userRepository.findById(userId).get());
        }
        System.out.println(us);
        return us;
    }
}
