
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int i =0;
    public static void main(String[] args) {
        try {
            String mt = args[0];
            String filePath = args[1];

            // Criando um leitor para o arquivo
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Lendo linhas do arquivo
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividindo a linha com base no caractere ':'
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    // Obtendo a palavra e o resultado
                    String palavra = parts[0];
                    String result = parts[1];

                    // Gerando o comando
                    String command = "java -jar MT.jar "+ mt +" "+ palavra;
                    // Executando o comando e lendo a saída
                    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
                    Process process = processBuilder.start();

                    BufferedReader readerResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String output = readerResult.readLine();

                    if(!(result.equals(output))){
                        System.out.println("erro: "+ i);
                        i++;
                    }
                    // Aguardando o término do processo
                    process.waitFor();
                }
            }

            // Fechando o leitor
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
