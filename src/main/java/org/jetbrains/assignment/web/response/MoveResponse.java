package org.jetbrains.assignment.web.response;

import lombok.Builder;
import org.jetbrains.assignment.db.entity.Direction;

@Builder
public record MoveResponse(Direction direction, int steps) {
}
