import greenfoot.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Object;

public class Cenario extends World {
    private Jogador jogador; // Instância do jogador no cenário
    private int tempoDesdeUltimoSpawn; // Contador para controlar o tempo desde o último spawn de inimigos
    private int pontos; // Pontuação do jogador
    private int taxaDeSpawn; // Taxa de spawn de inimigos
    private GreenfootSound musicaGame; // Som de fundo do jogo
    private boolean jogoAcabou; // Indica se o jogo terminou
    private Cenario CenarioDeserto; // Cenário para transição (Deserto)
    private boolean trocouFase; // Indica se houve troca de fase
    private String nomePlayer; // Nome do jogador
    private Historico historico; // Histórico de pontuações
    private Tabela tabela; // Tabela de pontuações exibida ao final do jogo

    // Construtor da classe Cenario
    public Cenario(Historico historico, boolean trocouFase) {
        super(460, 730, 1);

        this.historico = historico;
        this.trocouFase = trocouFase;

        musicaGame = new GreenfootSound("musicadefundo.mp3");
        musicaGame.setVolume(25);

        preparar(); // Configuração inicial do cenário

        gerarInimigo("verde"); // Gera inimigo verde
        gerarInimigo("vermelho"); // Gera inimigo vermelho

        atualizarExibicaoDePontos(); // Exibe a pontuação inicial
        taxaDeSpawn = 300; // Taxa inicial de spawn

        if (trocouFase) {
            pontos = 160; // Pontuação inicial após troca de fase
        }
    }

    // Método chamado a cada ato (quadro) do mundo
    public void act() {
        if (!jogoAcabou) {
            novoCenario(); // Verifica se é necessário transitar para um novo cenário

            tempoDesdeUltimoSpawn++;
            musicaGame.playLoop();

            // Verifica se é hora de spawnar novos inimigos
            if (tempoDesdeUltimoSpawn >= taxaDeSpawn) {
                gerarInimigo("verde");
                gerarInimigo("vermelho");
                gerarInimigo("preto");

                tempoDesdeUltimoSpawn = 0;

                // Ajusta a taxa de spawn com base na pontuação
                if (pontos >= 100 && pontos < 150) {
                    taxaDeSpawn = 250;
                } else if (pontos >= 150 && pontos < 200) {
                    taxaDeSpawn = 225;
                } else if (pontos >= 200 && pontos < 250) {
                    taxaDeSpawn = 200;
                } else if (pontos >= 250 && pontos < 300) {
                    taxaDeSpawn = 190;
                } else if (pontos >= 300) {
                    taxaDeSpawn = 180;
                }
            }

            atualizarExibicaoDePontos(); // Atualiza a exibição da pontuação
        }
    }

    // Método para obter o jogador
    public Jogador getJogador() {
        return jogador;
    }

    // Método privado para configuração inicial do cenário
    private void preparar() {
        jogador = new Jogador(); // Inicializa o jogador
        addObject(jogador, getWidth() / 2, getHeight() - 100); // Adiciona o jogador ao cenário
    }

    // Método privado para gerar inimigos de diferentes cores
    private void gerarInimigo(String cor) {
        int x = Greenfoot.getRandomNumber(getWidth() - 50);
        int y = Greenfoot.getRandomNumber(getHeight() / 6);

        // Ajusta a posição do inimigo se estiver muito próximo das bordas
        if (x >= 450) {
            x -= 60;
        }
        if (x <= 50) {
            x += 60;
        }

        // Gera inimigos com base na cor especificada
        if (cor.equals("verde"))
            addObject(new InimigoVerde(), x, y);
        if (cor.equals("vermelho"))
            addObject(new InimigoVermelho(), x, y);
        if (cor.equals("preto"))
            addObject(new InimigoPreto(), x, y);
    }

    // Método para aumentar os pontos e atualizar a exibição
    public void aumentarPontos(int quantidade) {
        pontos += quantidade;
        atualizarExibicaoDePontos();
    }

    // Método privado para exibir os pontos na tela
    private void atualizarExibicaoDePontos() {
        GreenfootImage imagemTexto = new GreenfootImage("Pontos: " + pontos, 24, Color.WHITE, Color.YELLOW,
                Color.YELLOW);
        showText("Pontos: " + pontos, 70, 30);
    }

    // Método para encerrar o jogo
    public void encerrarJogo() {
        historico.setInfo(getNomePlayer(), pontos); // Atualiza o histórico de pontuações
        showText("GAME OVER", getWidth() / 2, getHeight() / 2 - 10);
        showText("PRESS ENTER FOR RESTART", getWidth() / 2, getHeight() / 2 + 10);
        jogoAcabou = true;
        Greenfoot.setWorld(new Tabela(historico)); // Transita para a tabela de pontuações
        musicaGame.stop();
    }

    // Método para obter a pontuação
    public int getPontos() {
        return pontos;
    }

    // Método para verificar se o jogo acabou
    public boolean jogoAcabou() {
        return jogoAcabou;
    }

    // Método para transitar para um novo cenário (Deserto)
    public void novoCenario() {
        if (getPontos() == 150) {
            Greenfoot.setWorld(new CenarioDeserto(historico, getNomePlayer()));
        }
    }

    // Método para obter se houve troca de fase
    public boolean getTrocouFase() {
        return trocouFase;
    }

    // Método para definir o nome do jogador
    public void setNomePlayer(String nomePlayer) {
        this.nomePlayer = nomePlayer;
    }

    // Método para obter o nome do jogador
    public String getNomePlayer() {
        return nomePlayer;
    }
}
