package NASA.robotic.rover;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;



class RoverApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void testRoverApplicationWithValidInput() {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String[] args = { "C:\\Users\\hiche\\Downloads\\rover\\Input.txt" };
		RoverApplication.main(args);
		System.setOut(System.out);
		String expectedOutput = "1 3 N\r\n5 1 E\r\n1 3 N";
		String actualOutput = outputStream.toString().trim();
		System.out.println(actualOutput);
		assertTrue(actualOutput.contains(expectedOutput), "Output should contain the expected text");
	}




	@Test
	public void testRoverApplicationWithInvalidInput() {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		String[] args = { "C:\\Users\\hiche\\Downloads\\rover\\InvalidInput.txt" }; // Fournir un fichier d'entrée invalide
		RoverApplication.main(args);
		System.setOut(System.out);
		String expectedErrorMessage = "Erreur, votre fichier d'entrée n'est pas valide"; // Fournir le message d'erreur attendu
		String actualOutput = outputStream.toString().trim();
		System.out.println(actualOutput);
		assertTrue(actualOutput.contains(expectedErrorMessage), "Output should contain the expected error message");
	}

}
