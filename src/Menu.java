import java.util.Scanner;

public class Menu {
    protected Scanner scanner;

    public Menu(){
        scanner = new Scanner(System.in);
    }

    public void Initialize(){
        int size = GetStringSize();
        String stringChain = GetString(size);
        var searchChar = true;
        while(searchChar){
            char charToBeSearched = GetCharToBeSearched();
            FindChar(stringChain, charToBeSearched);
            searchChar = AskAnotherSearch();
        }
    }

    public int GetStringSize(){
        System.out.println("Digite o tamanho da cadeia entre 1 e 20:");
        var size = scanner.nextInt();
        if(size < 1 || size > 20){
            throw new IllegalArgumentException("Tamanho inválido");
        }

        return size;
    }

    public String GetString(int size){
        System.out.println("Digite a cadeia de caracteres:");
        var stringChain = scanner.next();
        if(stringChain.length() != size){
            throw new IllegalArgumentException("Tamanho da cadeia não corresponde com o valor fornecido.");
        }

        return stringChain;
    }

    public char GetCharToBeSearched(){
        System.out.println("Digite o caractere a ser procurado:");
        var input = scanner.next();
        if(input.length() > 1){
            throw new IllegalArgumentException("Mais de 1 caractere informado.");
        }

        return input.charAt(0);
    }

    public void FindChar(String input, char character){
        var position = input.indexOf(character);
        if(position == -1){
            System.out.println("Charactere não encontrado.");
        }
        else{
            System.out.println("O caractére foi encontrado na posição: " + position);
        }
    }

    public boolean AskAnotherSearch(){
        System.out.println("Deseja pesquisar outro caractere? (Y/N)");
        var input = Character.toLowerCase(scanner.next().charAt(0));
        if(input == 'y')
            return true;

        if(input != 'n'){
            throw new IllegalArgumentException("Opção inválida.");
        }

        return false;
    }

}
