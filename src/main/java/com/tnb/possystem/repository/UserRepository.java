package com.tnb.possystem.repository;

import com.tnb.possystem.modal.Store;
import com.tnb.possystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByStore(Store store);

    List<User> findByBranchId(Long branchId);
}
