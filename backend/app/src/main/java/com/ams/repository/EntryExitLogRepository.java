package com.ams.repository;

import com.ams.entity.EntryExitLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryExitLogRepository extends JpaRepository<EntryExitLog, Long> {
    Page<EntryExitLog> findByIsActiveTrue(Pageable pageable);
}
