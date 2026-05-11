package com.tnb.possystem.service.impl;

import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.mapper.BranchMapper;
import com.tnb.possystem.modal.Branch;
import com.tnb.possystem.modal.Store;
import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.BranchDto;
import com.tnb.possystem.repository.BranchRepository;
import com.tnb.possystem.repository.StoreRepository;
import com.tnb.possystem.service.BranchService;
import com.tnb.possystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public BranchDto createBranch(BranchDto branchDto) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDto, store);
        Branch savedBranch = branchRepository.save(branch);
        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not exist...")
        );
        existing.setName(branchDto.getName());
        existing.setAddress(branchDto.getAddress());
        existing.setEmail(branchDto.getEmail());
        existing.setPhone(branchDto.getPhone());
        existing.setWorkingDays(branchDto.getWorkingDays());
        existing.setCloseTime(branchDto.getCloseTime());
        existing.setOpenTime(branchDto.getOpenTime());
        existing.setUpdatedAt(LocalDateTime.now());

        Branch updatedBranch = branchRepository.save(existing);
        return BranchMapper.toDto(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not exist...")
        );
        branchRepository.delete(existing);
    }

    @Override
    public BranchDto getBranchById(Long branchId) throws Exception {
        Branch existing = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not exist...")
        );
        return BranchMapper.toDto(existing);
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {
        List<Branch> branches = branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDto).collect(Collectors.toList());
    }
}
