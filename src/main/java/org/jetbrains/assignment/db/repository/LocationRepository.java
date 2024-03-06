package org.jetbrains.assignment.db.repository;

import org.jetbrains.assignment.db.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}
