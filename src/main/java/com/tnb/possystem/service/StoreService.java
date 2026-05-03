package com.tnb.possystem.service;

import com.tnb.possystem.domain.StoreStatus;
import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.modal.Store;
import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws Exception;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id, StoreStatus status) throws Exception;
}
