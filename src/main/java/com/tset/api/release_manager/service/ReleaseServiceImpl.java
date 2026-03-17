package com.tset.api.release_manager.service;

import com.tset.api.release_manager.data.ServiceReleaseRepository;
import com.tset.api.release_manager.data.SystemReleaseRepository;
import com.tset.api.release_manager.data.model.ServiceRelease;
import com.tset.api.release_manager.data.model.SystemRelease;
import com.tset.api.release_manager.web.v1.model.ServiceReleaseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseServiceImpl implements ReleaseService{

    private final SystemReleaseRepository systemRepository;
    private final ServiceReleaseRepository serviceRepository;

    public ReleaseServiceImpl(SystemReleaseRepository systemRepository, ServiceReleaseRepository serviceRepository) {
        this.systemRepository = systemRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceRelease> getServiceReleasesBySystemVersion(Long systemVersion)
    {
        return serviceRepository.findBySystemReleaseVersion(systemVersion);
    }

    public Long addServiceRelease(ServiceReleaseDto serviceReleaseDto)
    {
        ServiceRelease serviceRelease = serviceRepository.findOneByName(serviceReleaseDto.getName());

        if(serviceRelease != null)
        {
            if (serviceRelease.getVersion() == serviceReleaseDto.getVersion())
            {
                // existing service release
                return serviceRelease.getSystemRelease().getId();
            }
            else {
                // upgraded service release, create new system release
                SystemRelease systemRelease = serviceRelease.getSystemRelease();

                SystemRelease newSystemRelease = new SystemRelease();
                newSystemRelease.setVersion(systemRelease.getVersion() + 1L);
                systemRepository.save(newSystemRelease);

                updateExistingServiceReleases(systemRelease, newSystemRelease);

                return systemRelease.getVersion();
            }
        }
        else {
            // new service release, create new system release

            Optional<SystemRelease> optionalSystemRelease = systemRepository.findFirstByOrderByIdDesc();
            Long newSystemVersion = 1L;
            if (optionalSystemRelease.isPresent())
            {
                newSystemVersion += 1L;
            }

            SystemRelease newSystemRelease = new SystemRelease();
            newSystemRelease.setVersion(newSystemVersion);

            ServiceRelease newServiceRelease = new ServiceRelease();
            newServiceRelease.setVersion(serviceReleaseDto.getVersion());
            newServiceRelease.setName(serviceReleaseDto.getName());
            newServiceRelease.setSystemRelease(newSystemRelease);

            systemRepository.save(newSystemRelease);
            serviceRepository.save(newServiceRelease);

            if (optionalSystemRelease.isPresent()) {
                updateExistingServiceReleases(optionalSystemRelease.get(), newSystemRelease);
            }

            return newSystemRelease.getVersion();
        }
    }

    private void updateExistingServiceReleases(SystemRelease oldSystemRelease,SystemRelease newSystemRelease)
    {
        List<ServiceRelease> serviceReleases = serviceRepository.findBySystemReleaseVersion(oldSystemRelease.getVersion());
        for (ServiceRelease release : serviceReleases) {
            ServiceRelease newServiceRelease = new ServiceRelease();
            newServiceRelease.setName(release.getName());
            newServiceRelease.setVersion(release.getVersion());
            newServiceRelease.setSystemRelease(newSystemRelease);
            serviceRepository.save(newServiceRelease);
        }
    }
}
