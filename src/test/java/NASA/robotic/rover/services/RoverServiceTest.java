package NASA.robotic.rover.services;

import NASA.robotic.rover.entities.Plateau;
import NASA.robotic.rover.entities.Rover;
import NASA.robotic.rover.enums.Direction;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

public class RoverServiceTest {

    private Plateau plateau;
    private Rover roverMock;
    private Plateau plateauMock;
    private String steps;
    private Rover rover,roverExeption;

    @BeforeEach
    public void setUp() {
         roverMock = mock(Rover.class);
         plateauMock = mock(Plateau.class);
        steps="LMLMLMLMM";

        roverExeption = Rover.builder()
                .x(4)
                .y(3)
                .direction(Direction.N)
                .plateau(plateauMock)
                .build();
        rover = Rover.builder()
                .x(1)
                .y(2)
                .direction(Direction.N)
                .plateau(plateauMock)
                .build();

    }

    @Test
    public void testMoveRover() {


        setUpInitialRoverState();
        RoverService.move(rover, steps);
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(Direction.N, rover.getDirection());

    }

@Test
   public void testRoverStateAfterMovements() {
    setUpInitialRoverState();
    RoverService.move(roverMock, steps);
        verify(roverMock, times(4)).setDirection(any(Direction.class));
    }
@Test
    public void verifySetDirectionCalls() {
        setUpInitialRoverState();
        RoverService.move(roverMock, steps);

        long leftCount = steps.chars().filter(step -> step == 'L').count();
        long rightCount = steps.chars().filter(step -> step == 'R').count();
        verify(roverMock, times((int) leftCount + (int) rightCount)).setDirection(any());
    }



    @Test
    public void testMoveRoverOutsidePlateau() throws RuntimeException{
        assertThrows(RuntimeException.class, () -> RoverService.move(roverExeption, "MMMMM"));

    }


    private void setUpInitialRoverState() {
        when(roverMock.getX()).thenReturn(1);
        when(roverMock.getY()).thenReturn(2);
        when(roverMock.getDirection()).thenReturn(Direction.N);
        when(roverMock.getPlateau()).thenReturn(plateauMock);
        when(plateauMock.getWidth()).thenReturn(5);
        when(plateauMock.getHeight()).thenReturn(5);
    }
}
