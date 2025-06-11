# 📊 Análise de Desempenho: Merge Sort vs Gnome Sort

Este projeto implementa e compara o desempenho de dois algoritmos de ordenação — **Merge Sort** (Grupo B) e **Gnome Sort** (Grupo C)

---

## 🛠️ Preparação dos Dados

Antes de executar os algoritmos, é necessário rodar uma única vez o programa `GerarConjuntos`. Isso se deve ao fato de que o GitHub não suporta arquivos `.txt` com tamanhos tão grandes, por isso os dados precisam ser gerados localmente.

O método `GerarConjuntos` cria os arquivos .txt , que contêm os vetores que serão ordenados. Os dados são sempre gerados com a **seed "123"** para garantir consistência entre as execuções.

Cada `.txt` contém **5 linhas**, e cada linha possui exatamente a quantidade de dados indicada no nome do arquivo, separados por vírgula. Por exemplo, `dados_1000.txt` possui 5 linhas com 1000 números inteiros aleatórios positivos (entre 1 e 1.000.000).
Cada **rodada de ordenação** utiliza uma dessas linhas como vetor de entrada.

---

## ⚙️ Merge Sort

O Merge Sort funciona dividindo recursivamente o vetor ao meio até que cada subvetor tenha apenas um elemento. Em seguida, os subvetores são **mesclados** (merge) de forma ordenada.

* A **esquerda** recebe os valores do início até o meio do vetor.
* A **direita** recebe os valores do meio até o fim.
* Na hora de juntar, o menor valor entre os dois vetores é adicionado ao vetor final, até que todos os elementos sejam reorganizados.

### 🔍 Métricas

* **Interações**: contadas em cada divisão e comparação durante o merge.
* **Trocas**: não ocorrem "trocas" diretas, mas **cada inserção de um valor no vetor final conta como uma troca**.

#### 📈 Resultados — Merge Sort

| Tamanho do vetor | Tempo médio (ms) | Trocas totais | Interações médias |
| ---------------- | ---------------- | ------------- | ----------------- |
| 1.000            | 1                | 9.976         | 11.749            |
| 10.000           | 7,8              | 133.616       | 130.475           |
| 100.000          | 50,4             | 1.668.929     | 1.636.412         |
| 500.000          | 171,2            | 9.475.712     | 9.337.085         |
| 1.000.000        | 250              | 19.951.424    | 19.674.398        |

> 💡 Um padrão observado: o número de **trocas**, é sempre o mesmo em todas as rodadas

> Gráficos do Merge Sort aqui 🎯

![merge1k](https://github.com/user-attachments/assets/eb2d9e76-4992-43b8-845c-b1d46b195764)
![merge10k](https://github.com/user-attachments/assets/f0742bc1-0e16-4d50-a553-062e6ae06254)
![Merge100k](https://github.com/user-attachments/assets/a5997aab-001e-44d2-b1e3-95fd69f1e278)
![Merge500k](https://github.com/user-attachments/assets/b210b8cb-75d6-48e7-8550-e16356bdeb16)
![Merge1000000](https://github.com/user-attachments/assets/b807fe81-4402-4bbf-b1c1-147d4016ab06)


## 🐌 Gnome Sort

O Gnome Sort simula uma espécie de inserção por comparação com os vizinhos anteriores:

* Um elemento é pego do vetor principal, para ser comparado.
* Se o elemento atual do ponteiro for menor que o anterior, eles são trocados.
* Agora o elemento a ser comparado é o elemento que estava no vetor
* O ponteiro "volta" até encontrar a posição correta do elemento.
* Se o elemento atual for maior que o anterior, o ponteiro avança.
* Quando a troca não trazer um elemento que estava no vetor ordenado
* Volta ao vetor original para pegar um novo elemento
* O processo se repete até o fim do vetor original

### 🔍 Métricas

* **Interações**: contadas em cada ida e volta do ponteiro pelo vetor.
* **Trocas**: contadas a cada vez que dois elementos trocam de posição.

#### 📈 Resultados — Gnome Sort

| Tamanho do vetor | Tempo médio           | Trocas médias   | Interações médias |
| ---------------- | --------------------- | --------------- | ----------------- |
| 1.000            | 3 ms                  | 248.300         | 497.624           |
| 10.000           | 635 ms                | 24.882.260      | 49.774.520        |
| 100.000          | 7.337 ms / 1,2 min    | 2.501.201.693   | 5.002.503.386     |
| 500.000          | 175.430 ms / 2,9 min  | 62.498.096.681  | 124.996.693.362   |
| 1.000.000        | 685.182 ms / 11,4 min | 249.971.848.807 | 499.944.697.618   |

> 💡 Um padrão observado: o número de **trocas é quase sempre metade do número de interações**, o que faz sentido dado o comportamento do algoritmo (cada troca ocorre após uma comparação negativa).

> Gráficos do Gnome Sort aqui 🎯
![gnome1k](https://github.com/user-attachments/assets/87be0cfa-7d0d-474e-b2d6-f79a22eb8090)
![gnome10k](https://github.com/user-attachments/assets/69b62acd-5c5a-4aea-9e74-a79221aedc8e)
![gnome100k](https://github.com/user-attachments/assets/a78200c4-b2ab-44db-825a-f5e8cc3afda3)
![Gnome500k](https://github.com/user-attachments/assets/fc201139-2293-42f8-b646-c6a431ecfcf8)
![gnome1kk](https://github.com/user-attachments/assets/fb2c5def-fdf7-4d29-ab31-78988ff47b02)


## 📌 Considerações Finais

* O **Merge Sort** apresenta desempenho muito superior em tempo e eficiência.
* O **Gnome Sort** sofre muito com vetores grandes não é recomendado para grandes volumes de dados.

---
