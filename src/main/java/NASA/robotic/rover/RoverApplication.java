package NASA.robotic.rover;

import NASA.robotic.rover.entities.Plateau;
import NASA.robotic.rover.entities.Rover;
import NASA.robotic.rover.enums.Direction;
import NASA.robotic.rover.services.RoverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class RoverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoverApplication.class, args);
		if (args.length == 0) {
			System.out.println("Merci de préciser le fichier d'entrée");
			return;
		}

		try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
			List<String> inputLines = lines.collect(Collectors.toList());
			if (inputLines != null && !inputLines.isEmpty()) {
				String[] plateauSize = inputLines.get(0).split(" ");
				Plateau plateau = new Plateau(Integer.parseInt(plateauSize[0]), Integer.parseInt(plateauSize[1]));

				for (int i = 1; i < inputLines.size(); i += 2) {
					String roverPositionLine = inputLines.get(i);
					String[] roverPosition = roverPositionLine.split(" ");
					int x = Integer.parseInt(roverPosition[0]);
					int y = Integer.parseInt(roverPosition[1]);
					String direction = roverPosition[2];
					Rover rover = new Rover(x, y, Direction.valueOf(direction), plateau);

					String steps = inputLines.get(i + 1);
					RoverService.move(rover, steps);

					System.out.println(rover.getX() + " " + rover.getY() + " " + rover.getDirection());
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur, merci de vérifier votre fichier d'entrée");
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.out.println("Erreur, votre fichier d'entrée n'est pas valide");
			e.printStackTrace();
		}
	}

}
