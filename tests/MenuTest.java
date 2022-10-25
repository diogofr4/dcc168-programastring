import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MenuTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeAll
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterAll
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void GetStringSizeWithValidInputShouldReturnSizeInputed(){
        //Arrange
        String input = "5";
        provideInput(input);
        var expected = 5;
        var object = new Menu();

        //Act
        var result = object.GetStringSize();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void GetStringSizeWithInvalidInputShouldThrowException(){
        //Arrange
        String input = "-1";
        provideInput(input);
        var object = new Menu();
        var expectedMessage = "Tamanho inválido";


        //Act
        IllegalArgumentException expected = assertThrows(
                IllegalArgumentException.class, object::GetStringSize);

        //Assert
        assertTrue(expected.getMessage().contains(expectedMessage));
    }

    @Test
    public void GetStringWithValidInputShouldReturnString(){
        //Arrange
        String input = "teste";
        int size = 5;
        provideInput(input);
        var object = new Menu();
        var expected = "teste";

        //Act
        var result = object.GetString(size);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void GetStringWithInvalidInputShouldThrowException(){
        //Arrange
        String input = "teste";
        int size = 0;
        provideInput(input);
        var object = new Menu();
        var expectedMessage = "Tamanho da cadeia não corresponde com o valor fornecido.";


        //Act
        IllegalArgumentException expected = assertThrows(
                IllegalArgumentException.class, () -> object.GetString(size));

        //Assert
        assertTrue(expected.getMessage().contains(expectedMessage));
    }

    @Test
    public void GetCharToBeSearchedWithValidInputShouldReturnString(){
        //Arrange
        String input = "s";
        provideInput(input);
        var object = new Menu();
        var expected = 's';

        //Act
        var result = object.GetCharToBeSearched();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void GetCharToBeSearchedWithInvalidInputShouldReturnString(){
        //Arrange
        String input = "sa";
        provideInput(input);
        var object = new Menu();
        var expectedMessage = "Mais de 1 caractere informado.";


        //Act
        IllegalArgumentException expected = assertThrows(
                IllegalArgumentException.class, object::GetCharToBeSearched);

        //Assert
        assertTrue(expected.getMessage().contains(expectedMessage));
    }

    @Test
    public void FindCharWithExistingCharaterShouldReturnPosition(){
        //Arrange
        String input = "teste";
        char character = 's';
        var object = new Menu();
        var expected = "O caractére foi encontrado na posição: 2";

        //Act
        object.FindChar(input, character);

        //Assert
        assertTrue(getOutput().contains(expected));
    }

    @Test
    public void FindCharWithoutExistingCharaterShouldReturnNotFoundMessage(){
        //Arrange
        String input = "teste";
        char character = 'a';
        var object = new Menu();
        var expected = "Charactere não encontrado.";

        //Act
        object.FindChar(input, character);

        //Assert
        assertTrue(getOutput().contains(expected));
    }

    @Test
    public void AskAnotherSearchWithPositiveInputShouldReturnTrue(){
        //Arrange
        String input = "y";
        provideInput(input);
        var object = new Menu();
        var expected = true;

        //Act
        var result = object.AskAnotherSearch();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void AskAnotherSearchWithNegativeInputShouldReturnFalse(){
        //Arrange
        String input = "n";
        provideInput(input);
        var object = new Menu();
        var expected = false;

        //Act
        var result = object.AskAnotherSearch();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void AskAnotherSearchWithInvalidInputShouldThrowException(){
        //Arrange
        String input = "B";
        provideInput(input);
        var object = new Menu();
        var expectedMessage = "Opção inválida.";


        //Act
        IllegalArgumentException expected = assertThrows(
                IllegalArgumentException.class, object::AskAnotherSearch);

        //Assert
        assertTrue(expected.getMessage().contains(expectedMessage));
    }

}
