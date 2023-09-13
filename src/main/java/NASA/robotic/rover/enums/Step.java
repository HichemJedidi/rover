package NASA.robotic.rover.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Step {
    Left('L'), Right('R'), Move('M');
    private char value;

    public static Step fromValue(char value) {
        for (Step step : Step.values()) {
            if (step.getValue() == value) {
                return step;
            }
        }
        throw new IllegalArgumentException("Invalid step value: " + value);
    }

}
