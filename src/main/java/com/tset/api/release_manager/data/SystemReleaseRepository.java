package com.tset.api.release_manager.data;

import com.tset.api.release_manager.data.model.SystemRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemReleaseRepository extends JpaRepository<SystemRelease, Long> {
    Optional<SystemRelease> findFirstByOrderByIdDesc();
}