// src/main/java/unitins/br/ArvoreBinaria.java
package unitins.br;
public class ArvoreBinaria implements ArvoreBinariaADT<Integer> {
    private No<Integer> raiz;
    private int tamanho; // Quantidade de nós
    private int totalRegistros; // Quantidade total de eleitores

    public ArvoreBinaria() {
        this.raiz = null;
        this.tamanho = 0;
        this.totalRegistros = 0;
    }
    
    // MÉTODO ESSENCIAL 1: INSERÇÃO
    @Override
    public void inserir(Integer chave, PerfilEleitor registro) {
        if (raiz == null) {
            raiz = new No<>(chave, 100); // Capacidade inicial 100
            raiz.adicionarRegistro(registro);
            tamanho++;
        } else {
            inserirRecursivo(raiz, chave, registro);
        }
        totalRegistros++; // Incrementa o total de eleitores
    }

    private void inserirRecursivo(No<Integer> no, Integer chave, PerfilEleitor registro) {
        int comparacao = chave.compareTo(no.chave);

        if (comparacao == 0) {
            // Chave já existe: adicionar registro ao nó existente [cite: 243]
            no.adicionarRegistro(registro);
        } else if (comparacao < 0) {
            // Ir para a esquerda [cite: 244]
            if (no.esquerda == null) {
                no.esquerda = new No<>(chave, 100);
                no.esquerda.adicionarRegistro(registro);
                tamanho++;
            } else {
                inserirRecursivo(no.esquerda, chave, registro);
            }
        } else {
            // Ir para a direita [cite: 245]
            if (no.direita == null) {
                no.direita = new No<>(chave, 100);
                no.direita.adicionarRegistro(registro);
                tamanho++;
            } else {
                inserirRecursivo(no.direita, chave, registro);
            }
        }
    }

    // MÉTODO ESSENCIAL 2: BUSCA
    @Override
    public PerfilEleitor[] buscar(Integer chave) {
        No<Integer> no = buscarNo(raiz, chave);
        if (no == null) {
            return new PerfilEleitor[0]; // Retorna array vazio se não encontrou
        }
        // Retornar APENAS os registros que foram realmente adicionados [cite: 249, 250]
        PerfilEleitor[] resultado = new PerfilEleitor[no.qtdRegistros];
        System.arraycopy(no.registros, 0, resultado, 0, no.qtdRegistros);
        return resultado;
    }

    private No<Integer> buscarNo(No<Integer> no, Integer chave) {
        if (no == null) return null;
        int comparacao = chave.compareTo(no.chave);

        if (comparacao == 0) {
            return no; // Encontrou! [cite: 251]
        } else if (comparacao < 0) {
            return buscarNo(no.esquerda, chave);
        } else {
            return buscarNo(no.direita, chave);
        }
    }

    // Você deve implementar os outros métodos da interface (contem, tamanho, totalRegistros, etc)
    // Para agilizar, os mais simples são:
    @Override
    public int tamanho() { return tamanho; }
    @Override
    public int totalRegistros() { return totalRegistros; }
    @Override
    public boolean estaVazia() { return raiz == null; }
    @Override
    public boolean contem(Integer chave) { return buscarNo(raiz, chave) != null; }
    // Deixe altura(), emOrdem() e limpar() para o final se necessário.

    // ... Outros métodos ...
    @Override
    public int altura() { return 0; } // Implementar com recursão depois
    @Override
    public Integer[] emOrdem() { return new Integer[0]; } // Implementar com percurso
    @Override
    public void limpar() { 
        raiz = null;
        tamanho = 0;
        totalRegistros = 0;
    }
}