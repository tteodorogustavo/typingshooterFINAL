import greenfoot.*;
import java.util.ArrayList;

public class Tabela extends World {
    private Historico historico; // Objeto que armazena o histórico de pontos e nomes
    private boolean reinicia; // Indica se o jogo deve ser reiniciado

    // Construtor da classe Tabela, recebe um objeto Historico como parâmetro
    public Tabela(Historico historico) {
        super(460, 730, 1); // Chama o construtor da classe pai (World) com as dimensões do mundo
        this.historico = historico; // Inicializa o histórico com o fornecido como parâmetro

        setBackground("tabela.jpg"); // Define a imagem de fundo para a tabela

        // Obtém listas de nomes e pontos do histórico
        ArrayList<String> nomes = historico.getNomes();
        ArrayList<Integer> pontos = historico.getPontos();

        reinicia = false; // Inicializa a variável de reinicialização como falsa

        // Exibe até quatro melhores pontuações na tela
        for (int i = 0; i < 4 && i < nomes.size(); i++) {
            showText("NOME: " + nomes.get(i) + "  /  PONTOS: " + pontos.get(i), getWidth() / 2, (i * 63) + 320);
        }
    }

    // Método chamado a cada ato (quadro) do mundo
    public void act() {
        verificarReiniciar(); // Chama o método para verificar se o jogo deve ser reiniciado
    }

    // Método privado para verificar se o jogador pressionou ENTER para reiniciar o
    // jogo
    private void verificarReiniciar() {
        if (Greenfoot.isKeyDown("ENTER") && !reinicia) {
            Greenfoot.setWorld(new CenarioEstrada(historico)); // Reinicia o jogo com um novo cenário
            reinicia = true; // Define a flag de reinicialização como verdadeira para evitar múltiplas
                             // reinicializações
        }
    }
}
