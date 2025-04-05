package com.ams.controller;

import com.ams.dto.FamilyMemberDTO;
import com.ams.service.FamilyMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family-members")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @GetMapping
    public List<FamilyMemberDTO> getAllFamilyMembers() {
        return familyMemberService.getAllFamilyMembers();
    }
}
