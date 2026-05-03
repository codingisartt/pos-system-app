package com.tnb.possystem.repository;

import com.tnb.possystem.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long id);

}

