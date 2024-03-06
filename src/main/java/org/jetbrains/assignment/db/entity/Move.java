package org.jetbrains.assignment.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Entity
@Data
public class Move {
    public Move(Direction direction, Integer steps) {
        this.direction = direction;
        this.steps = steps;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Direction direction;

    private Integer steps;
}
