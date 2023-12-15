import greenfoot.*;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

public class Historico {
    private LinkedHashMap<String, Integer> tabelaPontos; // Mapa que armazena o histórico de pontos associados a nomes de jogadores
    private ArrayList<String> arrayNome; // Lista de nomes dos jogadores ordenados por pontuação
    private ArrayList<Integer> arrayPontos; // Lista de pontuações dos jogadores ordenadas

    // Construtor da classe Historico
    public Historico() {
        tabelaPontos = new LinkedHashMap<>(); // Inicializa o mapa de pontos
    }

    // Método para adicionar informações de pontuação ao histórico
    public void setInfo(String nomePlayer, int pontos) {
        tabelaPontos.put(nomePlayer, pontos); // Adiciona a pontuação associada ao jogador ao mapa
        ordenarPontos(); // Chama o método para ordenar os pontos
    }

    // Método para ordenar os pontos em ordem decrescente
    public void ordenarPontos() {
        // Criar uma lista de entradas (chave-valor) a serem ordenadas
        ArrayList<Map.Entry<String, Integer>> listaEntradas = new ArrayList<>(tabelaPontos.entrySet());

        // Ordenar a lista usando um comparador personalizado (por pontuação em ordem decrescente)
        listaEntradas.sort((entrada1, entrada2) -> entrada2.getValue().compareTo(entrada1.getValue()));

        // Limpar a tabela original
        tabelaPontos.clear();

        // Preencher a tabela ordenada com as entradas ordenadas
        for (Map.Entry<String, Integer> entrada : listaEntradas) {
            tabelaPontos.put(entrada.getKey(), entrada.getValue());
        }

        // Preencher os arrays para facilitar o acesso posterior
        arrayNome = new ArrayList<>(tabelaPontos.keySet());
        arrayPontos = new ArrayList<>(tabelaPontos.values());

        // Imprimir os arrays (opcional, pode ser removido em versões finais)
        System.out.println(arrayNome);
        System.out.println(arrayPontos);
    }

    // Método para obter a lista de nomes ordenados por pontuação
    public ArrayList getNomes() {
        return arrayNome;
    }

    // Método para obter a lista de pontuações ordenadas
    public ArrayList getPontos() {
        return arrayPontos;
    }
}
