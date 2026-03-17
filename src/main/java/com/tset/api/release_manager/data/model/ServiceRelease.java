package com.tset.api.release_manager.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
public class ServiceRelease {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="name", nullable=false)
    @Getter
    private String name;

    @Setter
    @Getter
    private Long version;

    @ManyToOne
    @JoinColumn(name = "system_release_id")
    @JsonIgnore // Prevents infinite recursion in REST responses
    @Getter
    @Setter
    private SystemRelease systemRelease;

    public ServiceRelease() {}

    //public void setSystemRelease(SystemRelease systemRelease) {
    //    this.systemRelease = systemRelease;
    //}
}
