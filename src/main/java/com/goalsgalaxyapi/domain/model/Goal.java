package com.goalsgalaxyapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
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

}
