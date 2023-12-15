import greenfoot.*;
import java.util.ArrayList;

public class Inimigo extends Actor {
    private String palavra; // A palavra associada ao inimigo
    private boolean estaDerrotado; // Indica se o inimigo foi derrotado
    private boolean aguardandoRemocao; // Indica se o inimigo está aguardando remoção
    private GreenfootImage imagemTexto; // Imagem associada ao texto do inimigo
    private GreenfootImage imagemCombinada; // Imagem combinada do inimigo
    private int contadorInimigos; // Contador de inimigos
    private int contadorRemocao; // Contador para remoção
    private Actor alvo; // Alvo do inimigo
    private GreenfootSound somTiro; // Som do tiro disparado pelo inimigo
    private boolean jogoAcabou; // Indica se o jogo terminou
    private ArrayList<String> palavras; // Lista de palavras associadas aos inimigos
    private boolean inimigoChegou; // Indica se o inimigo chegou

    // Construtor da classe Inimigo
    public Inimigo() {
        estaDerrotado = false;
        aguardandoRemocao = false;
        inimigoChegou = false;
        jogoAcabou = false;

        somTiro = new GreenfootSound("rifle.wav");
        somTiro.setVolume(70);

        setImage(imagemCombinada);
    }

    // Método para verificar colisão com o tiro do jogador
    public void verificarColisao() {
        if (!((Cenario) getWorld()).jogoAcabou()) {
            Cenario cenario = (Cenario) getWorld();
            Jogador jogador = cenario.getJogador();

            if (!estaDerrotado && Greenfoot.isKeyDown("enter") && jogador != null) {
                String palavraDigitada = jogador.getPalavraDigitada();
                if (palavraDigitada.equalsIgnoreCase(palavra)) {
                    estaDerrotado = true;
                    jogador.limparPalavraDigitada();
                    aguardandoRemocao = true;

                    Tiro tiro = new Tiro(12, alvo);
                    cenario.addObject(tiro, jogador.getX(), jogador.getY());

                    tiro.atirarPara(this);
                    somTiro.play();

                    cenario.aumentarPontos(10);
                    jogador.turnTowards(getX(), getY());
                }
            }
        }
    }

    // Método chamado a cada ato (quadro) do mundo
    public void act() {
        if (!((Cenario) getWorld()).jogoAcabou()) {
            setLocation(getX(), getY() + 1);

            if (getY() == 705) {
                // Chama o método na classe Cenário para encerrar o jogo
                ((Cenario) getWorld()).encerrarJogo();
            }

            verificarColisao(); // Verifica colisão com o tiro do jogador
        }
    }

    // Métodos de acesso para os atributos privados

    public String getPalavra() {
        return palavra;
    }

    public boolean estaDerrotado() {
        return estaDerrotado;
    }

    public boolean aguardandoRemocao() {
        return aguardandoRemocao;
    }

    public GreenfootImage getImagemTexto() {
        return imagemTexto;
    }

    public GreenfootImage getImagemCombinada() {
        return imagemCombinada;
    }

    public int getContadorInimigos() {
        return contadorInimigos;
    }

    public int getContadorRemocao() {
        return contadorRemocao;
    }

    public Actor getAlvo() {
        return alvo;
    }

    public GreenfootSound getSomTiro() {
        return somTiro;
    }

    public boolean isJogoAcabou() {
        return jogoAcabou;
    }

    public ArrayList<String> getArrayPalavras() {
        return palavras;
    }

    // Métodos modificadores para os atributos privados

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void setEstaDerrotado(boolean estaDerrotado) {
        this.estaDerrotado = estaDerrotado;
    }

    public void setAguardandoRemocao(boolean aguardandoRemocao) {
        this.aguardandoRemocao = aguardandoRemocao;
    }

    public void setImagemTexto(GreenfootImage imagemTexto) {
        this.imagemTexto = imagemTexto;
    }

    public void setImagemCombinada(GreenfootImage imagemCombinada) {
        this.imagemCombinada = imagemCombinada;
    }

    public void setContadorInimigos(int contadorInimigos) {
        this.contadorInimigos = contadorInimigos;
    }

    public void setContadorRemocao(int contadorRemocao) {
        this.contadorRemocao = contadorRemocao;
    }

    public void setAlvo(Actor alvo) {
        this.alvo = alvo;
    }

    public void setSomTiro(GreenfootSound somTiro) {
        this.somTiro = somTiro;
    }

    public void setJogoAcabou(boolean jogoAcabou) {
        this.jogoAcabou = jogoAcabou;
    }

    public void setPalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }
}
