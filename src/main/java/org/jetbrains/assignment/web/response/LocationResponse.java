package org.jetbrains.assignment.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record LocationResponse(int x, int y) {
}
