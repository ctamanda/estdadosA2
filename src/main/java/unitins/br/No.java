// src/main/java/unitins/br/No.java
package unitins.br;

public class No<T extends Comparable<T>> {
    T chave; // Chave: Código da cidade (Integer)
    PerfilEleitor[] registros; // Array de registros dos eleitores desta cidade
    int qtdRegistros;
    int capacidade;
    No<T> esquerda;
    No<T> direita;

    public No(T chave, int capacidadeInicial) {
        this.chave = chave;
        this.capacidade = capacidadeInicial; // Ex: 100
        this.registros = new PerfilEleitor[capacidadeInicial];
        this.qtdRegistros = 0;
        this.esquerda = null;
        this.direita = null;
    }

    public void adicionarRegistro(PerfilEleitor registro) {
        if (qtdRegistros >= capacidade) {
            // Lógica de EXPANSÃO: dobra a capacidade do array [cite: 132]
            int novaCapacidade = capacidade * 2;
            PerfilEleitor[] novoArray = new PerfilEleitor[novaCapacidade];
            System.arraycopy(registros, 0, novoArray, 0, qtdRegistros);
            registros = novoArray;
            capacidade = novaCapacidade;
        }
        // Adiciona o novo registro
        registros[qtdRegistros++] = registro;
    }
}