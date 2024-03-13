package com.goalsgalaxyapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 100)
    private String name;
    private String description;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

}

