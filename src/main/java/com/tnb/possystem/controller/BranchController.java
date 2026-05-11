package com.tnb.possystem.controller;

import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.payload.dto.BranchDto;
import com.tnb.possystem.payload.response.ApiResponse;
import com.tnb.possystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws UserException {
        BranchDto createdBranch = branchService.createBranch(branchDto);
        return  ResponseEntity.ok().body(createdBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {
        BranchDto createdBranch = branchService.getBranchById(id);
        return  ResponseEntity.ok().body(createdBranch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(@PathVariable Long storeId) throws Exception {
        List<BranchDto> createdBranch = branchService.getAllBranchesByStoreId(storeId);
        return  ResponseEntity.ok().body(createdBranch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long id,
                                                  @RequestBody BranchDto branchDto) throws Exception {
        BranchDto updatedBranch = branchService.updateBranch(id,branchDto);
        return  ResponseEntity.ok().body(updatedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranchById(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Branch deleted successfully");
        return  ResponseEntity.ok(apiResponse);
    }
}
