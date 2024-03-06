package org.jetbrains.assignment.db.repository;

import org.jetbrains.assignment.db.entity.Location;
import org.jetbrains.assignment.db.entity.Move;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {
}
