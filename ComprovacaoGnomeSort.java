import java.io.*;
import java.util.*;


public class ComprovacaoGnomeSort {

    // Contadores Globais
    static long totalTrocas =0;
    static long totalInteracoes =0;

    //Algoritimo de GnomeSort
    public static void fazerGnome(int[] vetor){
        int posicaoAtual = 0;
        // este vetor.lengt vai ler o tamanho do vetor, que pode veriar de 1k a 1kk durante a execução
        while (posicaoAtual <vetor.length){
            totalInteracoes++;// adiicona um ao contador
            // se for o inicio do programa ou o valor da posição atual for maior ou igual a posição anterior
            if (posicaoAtual == 0 || vetor[posicaoAtual] >= vetor[posicaoAtual -1]){
                posicaoAtual++; // avança pra proxima posição e repete while
            } else {// se o valor da posição atual for menor que o valor da posição anteior
                int temporario = vetor [posicaoAtual]; // variavel temporaria pra armazenar o valor da posicao atual
                vetor[posicaoAtual] = vetor[posicaoAtual-1]; // o valor da posição atual passa pra posição anterior
                vetor[posicaoAtual -1] = temporario; // posição anterior fica com o valor antigo da posição atual
                totalTrocas++; // soma +1 ao total de trocas
                posicaoAtual --; // retrocede a posição pra garantir que o dado esteja no lugar correto
            }
        }
    }

    //funcao que le uma linha do arquivo e converte no vetor de inteiros pra fazer a ordenação
    public static int[] lerLinha(String nomeArquivo,int linhaDesejada){
        // esse try catch esta lidando com leitura de arquivo
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))){
            String linha;
            int linhaAtual = 0;

            // este length serve para definir o tamanho do vetor de acordo com arquivo da vez
            // e ajuda a controlar o loop da leitura do arquivo
            while ((linha = leitor.readLine()) != null) {
                if (linhaAtual == linhaDesejada){
                    String[] numerosComoTexto = linha.split(",");
                    int [] vetor = new int[numerosComoTexto.length];
                    for (int i = 0; i<numerosComoTexto.length;i++){
                        vetor[i] = Integer.parseInt(numerosComoTexto[i]);
                    }
                    return vetor;
                }
                linhaAtual++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Salva os resultados de cada rodada em um arquivo CSV
    public static void salvarCSV(String nomeArquivo, List<String[]> dados) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            escritor.write("Rodada,Tempo(ms),Trocas,Iteracoes\n");
            for (String[] linha : dados) {
                escritor.write(String.join(",", linha));
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // --- BLOC DE TESTE PARA O VETOR ESPECÍFICO (GNOME SORT) ---
        System.out.println("--- Teste do Gnome Sort com vetor específico ---");
        int[] meuVetorDeTeste = {255, 132, 7, 55, 190976, 204, 500, 9, 1};

        System.out.println("Vetor original: " + Arrays.toString(meuVetorDeTeste));

        // Zera os contadores antes de cada teste
        totalTrocas = 0;
        totalInteracoes = 0;

        long inicioTeste = System.currentTimeMillis();
        fazerGnome(meuVetorDeTeste); // Executa o Gnome Sort
        long fimTeste = System.currentTimeMillis();

        System.out.println("Vetor ordenado: " + Arrays.toString(meuVetorDeTeste));
        System.out.println("Tempo de execução: " + (fimTeste - inicioTeste) + " ms");
        System.out.println("Total de trocas: " + totalTrocas);
        System.out.println("Total de interações: " + totalInteracoes);
        System.out.println("-----------------------------------------------\n");



        }
    }
