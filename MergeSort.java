import java.io.*;
import java.util.*;


public class MergeSort {

    //Contadores glocais para as estatisticas
    static int totalTrocas = 0; // total de vezes que elementos foram movidos
    static int totalInteracoes = 0; // total de vezes que o algoritimo percebeu ou comparou elementos

    // Principal funcao recursiva do Merge Sort
    // aqui é responsavel por dividir o vetor em 2 partes iguais criando subvetores
    public static void fazerMergeSort(int[] vetor,int inicio,int fim){
        if (inicio < fim){ // inicio menor que fim
            int meio = (inicio + fim) / 2 ;
            //Ordena a metade esquerda
            fazerMergeSort(vetor, inicio, meio);
            //Ordena a metade direita
            fazerMergeSort(vetor, meio + 1, fim);

            //Mescla as duas metades ordenadas
            merge(vetor,inicio,meio,fim);
        }
    }

    //Funcao que mescla dois subvetores que ja estão ordenados
    public static void merge(int[] vetor,int inicio,int meio,int fim){
        totalInteracoes ++; // adiciona 1 ao total de mesclagens

        // faz a divisao de acordo com o tamanho do dados
        int tamanhoEsquerda = meio - inicio +1;
        int tamanhoDireita = fim - meio;

        int[] esquerda = new int[tamanhoEsquerda];
        int[] direita = new int[tamanhoDireita];

        // Copia os dados para os sub vetores
        for (int i = 0; i<tamanhoEsquerda; i++)
        // esquerda começa no inicio e vai indo 1 por 1 até o meio
            esquerda[i] = vetor[inicio +i];
        for (int j = 0; j<tamanhoDireita; j++)
        // direita começa no meio +1 e vai indo até o fim
            direita[j] = vetor[meio + 1 + j];    
        
        //Indices para trabalhar com os vetores
        int i =0, j = 0; // i = esquerda j = direita
        int tamanhoVetor = inicio; // indice para reconstruir o vetor original

        // mescla os dois vetores em ordem
        // && é E < menor
        while (i <tamanhoEsquerda && j < tamanhoDireita) {
            totalInteracoes++; // adiciona uma interação ao total
            // logica pra saber qual valor adicionar no vetor 
            if(esquerda[i] <= direita[j]){ // se o incide da esquerda for menor ou igual da direita
                vetor[tamanhoVetor++] = esquerda[i++]; // avança o indice da esquerda
            } else {
                vetor[tamanhoVetor++] = direita[j++]; // se não avança o da direita
            }
            totalTrocas++; //adiciona +1 nas trocas
        }

        //copia oque restou da esquerda
        while (i < tamanhoEsquerda) {
            vetor[tamanhoVetor++] = esquerda[i++];
            totalTrocas++; //adiciona +1 nas trocas
        }

        //copia oque restou da direita
        while (j <tamanhoDireita) {
            vetor[tamanhoVetor++] = direita[j++];
            totalTrocas++; //adiciona +1 nas trocas
        }
    }

    //funcao que le uma linha do arquivo e converte no vetor de inteiros pra fazer o Merge
    public static int[] lerLinha(String nomeArquivo,int linhaDesejada){
        // esse try catch esta lidando com leitura de arquivo
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))){
            String linha;
            int linhaAtual = 0;

            // este length serve para definir o tamanho do vetor de acordo com arquivo da vez
            // e ajuda a controlar o loop da leitura do arquivo
            while ((linha = leitor.readLine()) != null) {
                if (linhaAtual == linhaDesejada){
                    String[] partes = linha.split(",");
                    int [] vetor = new int[partes.length];
                    for (int i = 0; i<partes.length;i++){
                        vetor[i] = Integer.parseInt(partes[i]);
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

    //funcao que salva os dados coletados em CVS
    public static void salvarCSV(String nomeArquivo,List<String[]>dados){
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))){
            escritor.write("Rodada,Tempo(ms),Trocas,Interacoes\n");
            for (String[] linha: dados){
                escritor.write(String.join(",",linha));
                escritor.newLine();
            }
            } catch (IOException e){
                e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //arquivos com os dados
        String[] arquivosDados = {
            "dados_1000.txt",
            "dados_10000.txt",
            "dados_100000.txt",
            "dados_500000.txt",
            "dados_1000000.txt"
        };

        // loop pelos arquivos
        for (String nomeArquivo : arquivosDados){
            List<String[]> resultadosRodadas = new ArrayList<>();

            // 5 rodadas por arquivo
            for (int rodada = 0; rodada <5; rodada++){
                // o tamanho do vetor vai mudar sempre que mudar o tamanho total de dados
                int[] vetor = lerLinha(nomeArquivo, rodada);

                totalTrocas = 0;
                totalInteracoes = 0;

                // faz a medição de tempo em ms e precisa ser em long
                long tempoInicial = System.currentTimeMillis();
                // o fim é o tamanho do vetor da vez -1 pq o indice começa em 0 se não diminuir vai procurar 1 indice a mais(que n exsite)
                fazerMergeSort(vetor, 0, vetor.length - 1);
                long tempoFinal = System.currentTimeMillis();

                resultadosRodadas.add(new String[]{
                    String.valueOf(rodada + 1),
                    String.valueOf(tempoFinal - tempoInicial),
                    String.valueOf(totalTrocas),
                    String.valueOf(totalInteracoes)
                });

                System.out.println("Arquivo: " + nomeArquivo + " | Rodada: " + (rodada + 1) + " concluída.");
            }
            // Gera o nome do arquivo CSV de saída com base no nome de entrada
            String SaidaCSV = "resultados_mergeSort_" +
                                  nomeArquivo.replace("dados_", "").replace(".txt", "") +
                                  ".csv";

            salvarCSV(SaidaCSV, resultadosRodadas);
            System.out.println(">>> CSV gerado: " + SaidaCSV);
        }

    }
}
