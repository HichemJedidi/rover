package NASA.robotic.rover.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TurnLeft {
    N(Direction.W),
    W(Direction.S),
    S(Direction.E),
    E(Direction.N);

    private Direction value;

    public static Direction getNewDirection(Direction value) {
        for (TurnLeft direction : TurnLeft.values()) {
            if (value.toString().equals(direction.toString())) {
                return direction.getValue();
            }
        }
        throw new IllegalArgumentException("Invalid TurnLeft derection value: " + value);
    }
}
