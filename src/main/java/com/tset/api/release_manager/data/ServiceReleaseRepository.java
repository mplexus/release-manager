package com.tset.api.release_manager.data;

import com.tset.api.release_manager.data.model.ServiceRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceReleaseRepository extends JpaRepository<ServiceRelease, Long> {

    List<ServiceRelease> findBySystemReleaseVersion(Long systemReleaseVersion);

    ServiceRelease findFirstByNameEqualsOrderBySystemReleaseDesc(String serviceName);
}
