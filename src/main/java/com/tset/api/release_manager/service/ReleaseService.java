package com.tset.api.release_manager.service;

import com.tset.api.release_manager.data.model.ServiceRelease;
import com.tset.api.release_manager.web.v1.model.ServiceReleaseDto;

import java.util.List;

public interface ReleaseService {
    List<ServiceRelease> getServiceReleasesBySystemVersion(Long systemVersion);

    Long addServiceRelease(ServiceReleaseDto serviceRelease);
}
