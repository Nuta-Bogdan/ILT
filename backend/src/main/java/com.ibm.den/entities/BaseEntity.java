package com.ibm.den.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "id_sequence", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Version
    @Column(name = "DATABASE_VERSION", nullable = false)
    private Long databaseVersion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", updatable = false, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE", nullable = false)
    private LocalDateTime updateDate;

    /**
     * Set the update date, before saving the entity in the database
     */
    @PrePersist
    private void setUpdateDate() {
        updateDate = LocalDateTime.now();
    }
}
