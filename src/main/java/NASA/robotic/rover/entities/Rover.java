package NASA.robotic.rover.entities;

import NASA.robotic.rover.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rover {
    private int x;
    private int y;
    private Direction direction;
    private Plateau plateau;

    public void moveNorth() {
        y++;
    }

    public void moveEast() {
        x++;
    }

    public void moveSouth() {
        y--;
    }

    public void moveWest() {
        x--;
    }
}
