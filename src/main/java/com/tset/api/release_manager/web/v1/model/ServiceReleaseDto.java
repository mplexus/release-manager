package com.tset.api.release_manager.web.v1.model;

public class ServiceReleaseDto {
    private String name;
    private long version;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getVersion() { return version; }
    public void setVersion(long version) { this.version = version; }
}
