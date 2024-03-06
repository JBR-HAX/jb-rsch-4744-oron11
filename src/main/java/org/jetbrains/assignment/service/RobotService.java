package org.jetbrains.assignment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.assignment.db.entity.Direction;
import org.jetbrains.assignment.db.entity.Location;
import org.jetbrains.assignment.db.entity.Move;
import org.jetbrains.assignment.db.repository.LocationRepository;
import org.jetbrains.assignment.db.repository.MoveRepository;
import org.jetbrains.assignment.web.request.LocationRequest;
import org.jetbrains.assignment.web.request.MoveRequest;
import org.jetbrains.assignment.web.response.LocationResponse;
import org.jetbrains.assignment.web.response.MoveResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RobotService {
    private final LocationRepository locationRepository;
    private final MoveRepository moveRepository;

    @Transactional
    public List<LocationResponse> getLocations(List<MoveRequest> moveRequests) {
        log.debug("Getting locations, moveRequests={}", moveRequests);

        List<Move> moves = moveRequests.stream().map(request -> new Move(request.getDirection(), request.getSteps())).toList();
        moveRepository.saveAll(moves);

        List<Location> locations = generateLocations(moves);
        locationRepository.saveAll(locations);

        log.debug("Getting locations, locations={}", locations);

        return locations.stream().map(location -> LocationResponse.builder().x(location.getX()).y(location.getY()).build()).toList();
    }

    private List<Location> generateLocations(List<Move> moves) {
        Location location = new Location(0, 0);
        List<Location> locations = new ArrayList<>();
        int i = 0;

        do {
            locations.add(locationRepository.save(location));
            Move move = moves.get(i);
            location = generateLocationFromMove(move, location);
            i++;
        } while (i < moves.size());

        locations.add(locationRepository.save(location));

        return locations;
    }

    private Location generateLocationFromMove(Move move, Location lastLocation) {
        switch (move.getDirection()) {
            case EAST -> { return new Location(lastLocation.getX() + move.getSteps(), lastLocation.getY()); }
            case WEST -> { return new Location(lastLocation.getX() - move.getSteps(), lastLocation.getY()); }
            case NORTH -> { return new Location(lastLocation.getX(), lastLocation.getY() + move.getSteps()); }
            case SOUTH -> { return new Location(lastLocation.getX(), lastLocation.getY() - move.getSteps()); }
            default -> throw new IllegalStateException("Unexpected value: " + move.getDirection());
        }
    }

    public List<MoveResponse> getMoves(List<LocationRequest> locationRequests) {
        log.debug("Getting moves, locationRequests={}", locationRequests);

        List<Location> locations = locationRequests.stream().map(request -> new Location(request.getX(), request.getY())).toList();
        locationRepository.saveAll(locations);

        List<Move> moves = generateMoves(locations);
        locationRepository.saveAll(locations);

        log.debug("Getting moves, locations={}", locations);

        return moves.stream().map(move -> MoveResponse.builder().steps(move.getSteps()).direction(move.getDirection()).build()).toList();

    }

    private List<Move> generateMoves(List<Location> locations) {
        List<Move> moves = new ArrayList<>();
        Location lastLocation = locations.get(0);

        for (int i = 0; i < locations.size() - 1; i++) {
            Location nextLocation = locations.get(i + 1);
            Move move = generateMoveFromLocation(lastLocation, nextLocation);
            moves.add(move);
        }

        moveRepository.saveAll(moves);
        return moves;
    }

    private Move generateMoveFromLocation(Location lastLocation, Location nextLocation) {
        if (nextLocation.getX() > lastLocation.getX()) {
            return new Move(Direction.EAST, Math.abs(nextLocation.getX() - lastLocation.getX()));
        }
        else if (nextLocation.getX() < lastLocation.getX()) {
            return new Move(Direction.WEST, Math.abs(nextLocation.getX() - lastLocation.getX()));
        }
        else if (nextLocation.getY() > lastLocation.getY()) {
            return new Move(Direction.NORTH, Math.abs(nextLocation.getY() - lastLocation.getY()));
        }
        else {
            return new Move(Direction.SOUTH, Math.abs(nextLocation.getY() - lastLocation.getY()));
        }
    }
}
