package org.jetbrains.assignment.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.assignment.db.entity.Direction;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationRequest {

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;
}
