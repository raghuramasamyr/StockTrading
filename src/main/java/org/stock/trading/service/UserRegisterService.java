package org.stock.trading.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stock.trading.dto.UserRegistryDto;
import org.stock.trading.entity.UserEntity;
import org.stock.trading.repository.UserRepository;
import org.stock.trading.serviceInterface.UserRegisterInterface;

@Service
@Slf4j
public class UserRegisterService implements UserRegisterInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRegistryDto saveUser(UserRegistryDto userRegistryDto){
        log.info("User details is about to be saved");
        UserEntity userDetail = modelMapper.map(userRegistryDto,UserEntity.class);
        UserEntity savedDetails = userRepository.save(userDetail);
        log.info("User details is saved for ID:{}",savedDetails.getUserId());
        return modelMapper.map(savedDetails, UserRegistryDto.class);
    }
}
