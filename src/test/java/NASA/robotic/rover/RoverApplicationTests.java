package NASA.robotic.rover;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoverApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void testRoverApplicationWithValidInput() {
		// Rediriger System.out pour capturer la sortie
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		String[] args = { "C:\\Users\\hiche\\Downloads\\rover\\Input.txt" }; // Fournir un fichier d'entrée valide

		// Appeler la méthode main de votre classe d'application avec les arguments spécifiés
		RoverApplication.main(args);

		// Restaurer le System.out d'origine
		System.setOut(System.out);

		// Assert the expected output based on the input
		String expectedOutput = "1 3 N\r\n5 1 E\r\n1 3 N"; // Fournir la sortie attendue
		String actualOutput = outputStream.toString().trim();
		System.out.println(actualOutput);
		assertTrue(actualOutput.contains(expectedOutput), "Output should contain the expected text");
	}




	@Test
	public void testRoverApplicationWithInvalidInput() {
		// Rediriger System.out pour capturer la sortie
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		String[] args = { "C:\\Users\\hiche\\Downloads\\rover\\InvalidInput.txt" }; // Fournir un fichier d'entrée invalide

		// Appeler la méthode main de votre classe d'application avec les arguments spécifiés
		RoverApplication.main(args);

		// Restaurer le System.out d'origine
		System.setOut(System.out);

		// Assert the expected error message based on the input
		String expectedErrorMessage = "Erreur, votre fichier d'entrée n'est pas valide"; // Fournir le message d'erreur attendu
		String actualOutput = outputStream.toString().trim();
		System.out.println(actualOutput);
		assertTrue(actualOutput.contains(expectedErrorMessage), "Output should contain the expected error message");
	}

}
