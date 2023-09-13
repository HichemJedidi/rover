package NASA.robotic.rover.enums;

import NASA.robotic.rover.entities.Rover;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;
@Getter
@AllArgsConstructor
public enum MoveActions {

    N(Rover::moveNorth),
    E(Rover::moveEast),
    S(Rover::moveSouth),
    W(Rover::moveWest);

    private Consumer<Rover> moveToDirection;

    public static Consumer<Rover> nextMove(Direction value) {
        for (MoveActions movedirection : MoveActions.values()) {
            if (value.toString().equals(movedirection.toString())) {
                return movedirection.getMoveToDirection();
            }
        }
        throw new IllegalArgumentException("Invalid movedirection  value: " + value);
    }
}
