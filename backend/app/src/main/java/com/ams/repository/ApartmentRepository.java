package com.ams.repository;

import com.ams.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findByIsDeletedFalse(); // Fetch only active (non-deleted) apartments
}
