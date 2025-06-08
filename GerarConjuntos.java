import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class GerarConjuntos {

    public static void gerarConjuntos(int quantidade,String nomeArquivo){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))){
            Random rd = new Random(123); // seed é 123

            for (int i = 0; i<5; i++){ // for que controla os 5 conjuntos
                StringBuilder linha = new StringBuilder();

                for (int j = 0; j<quantidade;j++){ // for da quantidade de dados do conjunto, 1k 10k...
                    int numero = rd.nextInt(1_000_000); // numero maximo é 1kk
                    linha.append(numero);
                    if (j < quantidade -1){
                        linha.append(","); // faz uma separação por , no lugar de espaço faz o arquivo ser lido mais rapido pelo que pesquisei
                    }
                }

                writer.write(linha.toString());
                writer.newLine(); // próxima linha é novo vetor
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        gerarConjuntos(1000, "dados_1000.txt");
        gerarConjuntos(10000, "dados_10000.txt");
        gerarConjuntos(100000, "dados_100000.txt");
        gerarConjuntos(500000, "dados_500000.txt");
        gerarConjuntos(1000000, "dados_1000000.txt");
    }
}