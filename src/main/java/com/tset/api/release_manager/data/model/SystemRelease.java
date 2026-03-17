package com.tset.api.release_manager.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SystemRelease {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable=false, unique=false)
    private LocalDate releaseDate = LocalDate.now();

    @Column
    @Getter
    @Setter
    private Long version;

    @OneToMany(mappedBy = "systemRelease", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<ServiceRelease> serviceReleases =  new ArrayList<>();

    public void addServiceRelease(ServiceRelease serviceRelease)
    {
        serviceReleases.add(serviceRelease);
        serviceRelease.setSystemRelease(this);
    }
}
