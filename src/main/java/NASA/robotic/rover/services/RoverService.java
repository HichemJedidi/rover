package NASA.robotic.rover.services;

import NASA.robotic.rover.entities.Rover;
import NASA.robotic.rover.enums.*;

public class RoverService {


    public  void move(Rover rover, String steps) {
        steps.chars()
                .mapToObj(step -> (char) step)
                .forEach(step -> {
                    Step movement  = Step.fromValue(step);
                    Direction direction= rover.getDirection();
                    if (movement  == Step.Left) {
                        rover.setDirection(TurnLeft.getNewDirection(direction));
                    } else if (movement  == Step.Right) {
                        rover.setDirection(TurnRight.getNewDirection(direction));
                    } else if (movement  == Step.Move) {
                        MoveActions.nextMove(direction).accept(rover);
                        if (rover.getX() > rover.getPlateau().getWidth() || rover.getY() > rover.getPlateau().getHeight()) {
                            throw new RuntimeException("Merci de vérifier votre entrée, le rover ne peut pas sortir du plateau");
                        }
                    }
                });
    }

}
