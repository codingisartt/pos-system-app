package com.tnb.possystem.service;

import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.payload.dto.BranchDto;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(BranchDto branchDto) throws UserException;
    BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception;
    void deleteBranch(Long id) throws Exception;
    BranchDto getBranchById(Long branchId) throws Exception;
    List<BranchDto> getAllBranchesByStoreId(Long storeId);
}
