package NASA.robotic.rover.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TurnRight {
    N(Direction.E),
    E(Direction.S),
    S(Direction.W),
    W(Direction.N);

    private Direction value;

    public static Direction getNewDirection(Direction value) {
        for (TurnRight direction : TurnRight.values()) {
            if (value.toString().equals(direction.toString())) {
                return direction.getValue();
            }
        }
        throw new IllegalArgumentException("Invalid TurnLeft derection value: " + value);
    }
}
