package com.plan2go.viajes_back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;


@Entity
@Table(name = "trips")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne(optional = false, fetch = EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    void pre() {
        if(id == null) id=UUID.randomUUID();
        if(createdAt == null) createdAt=Instant.now();
        if(status == null) status="PLANNING";
    }
}
