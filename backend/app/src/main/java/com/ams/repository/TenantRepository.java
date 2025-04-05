package com.ams.repository;

import com.ams.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByIsDeletedFalse(); // 🔥 Now usable in TenantService
}
