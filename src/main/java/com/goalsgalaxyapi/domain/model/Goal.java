package com.goalsgalaxyapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Goal(String name, String description, LocalDateTime createdDate, LocalDateTime deadline, Category category, User user) {
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.category = category;
        this.user = user;
        this.tasks = new ArrayList<>();
    }
}
