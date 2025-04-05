package com.ams.repository;

import com.ams.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {

    long countByOwnerIdAndIsDeletedFalse(Long ownerId);

    long countByTenantIdAndIsDeletedFalse(Long tenantId);
}
