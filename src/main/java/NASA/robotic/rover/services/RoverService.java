package NASA.robotic.rover.services;

import NASA.robotic.rover.entities.Rover;
import NASA.robotic.rover.enums.Direction;
import NASA.robotic.rover.enums.Step;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RoverService {
    private static final Map<Direction, Consumer<Rover>> moveActions = new HashMap<>();
    private final static Map<Direction, Direction> turnLeft = new HashMap<>();
    private final static Map<Direction, Direction> turnRight = new HashMap<>();

    static {
        moveActions.put(Direction.N, Rover::moveNorth);
        moveActions.put(Direction.E, Rover::moveEast);
        moveActions.put(Direction.S, Rover::moveSouth);
        moveActions.put(Direction.W, Rover::moveWest);

        turnLeft.put(Direction.N, Direction.W);
        turnLeft.put(Direction.W, Direction.S);
        turnLeft.put(Direction.S, Direction.E);
        turnLeft.put(Direction.E, Direction.N);

        turnRight.put(Direction.N, Direction.E);
        turnRight.put(Direction.E, Direction.S);
        turnRight.put(Direction.S, Direction.W);
        turnRight.put(Direction.W, Direction.N);

    }

    public static void move(Rover rover, String steps) {
        steps.chars()
                .mapToObj(step -> (char) step)
                .forEach(step -> {
                    Step movement  = Step.fromValue(step);
                    Direction direction= rover.getDirection();
                    if (movement  == Step.Left) {
                        rover.setDirection(turnLeft.get(direction));
                    } else if (movement  == Step.Right) {
                        rover.setDirection(turnRight.get(direction));
                    } else if (movement  == Step.Move) {
                        moveActions.get(direction).accept(rover);
                        if (rover.getX() > rover.getPlateau().getWidth() || rover.getY() > rover.getPlateau().getHeight()) {
                            throw new RuntimeException("Merci de vérifier votre entrée, le rover ne peut pas sortir du plateau");
                        }
                    }
                });
    }

}
