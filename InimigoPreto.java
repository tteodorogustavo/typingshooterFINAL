import greenfoot.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class InimigoVerde here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class InimigoPreto extends Inimigo {
    private ArrayList<String> palavras;

    public InimigoPreto() {
        super();
        palavras = new ArrayList<>();
        palavras.add("love");
        palavras.add("jump");
        palavras.add("fish");
        palavras.add("kite");
        palavras.add("blue");
        palavras.add("tree");
        palavras.add("book");
        palavras.add("song");
        palavras.add("rain");
        palavras.add("wolf");
        palavras.add("moon");
        palavras.add("game");
        palavras.add("bird");
        palavras.add("star");
        palavras.add("road");
        palavras.add("wind");
        palavras.add("fire");
        palavras.add("ship");
        palavras.add("apple");
        palavras.add("rock");

        setEstaDerrotado(false);
        setAguardandoRemocao(false);

        setPalavra(getPalavraAleatoria());
        Color verdeEscuro = new Color(0, 100, 0);
        setImagemTexto(new GreenfootImage(getPalavra(), 20, Color.WHITE, Color.BLACK, null));
        GreenfootImage imagemInimigo = new GreenfootImage("zombiepreto.png");

        // Redimensione a imagem do inimigo
        int novaLargura = imagemInimigo.getWidth() / 6; // metade da largura original
        int novaAltura = imagemInimigo.getHeight() / 6; // metade da altura original
        imagemInimigo.scale(novaLargura, novaAltura);

        // Cria uma nova imagem que é grande o suficiente para conter ambas as imagens
        setImagemCombinada(new GreenfootImage(Math.max(getImagemTexto().getWidth(), imagemInimigo.getWidth()),
                getImagemTexto().getHeight() + imagemInimigo.getHeight()));

        // Desenha a imagem do inimigo e o texto na imagem combinada
        getImagemCombinada().drawImage(imagemInimigo, (getImagemCombinada().getWidth() - imagemInimigo.getWidth()) / 2,
                0);
        getImagemCombinada().drawImage(getImagemTexto(),
                (getImagemCombinada().getWidth() - getImagemTexto().getWidth()) / 2,
                imagemInimigo.getHeight());

        setSomTiro(new GreenfootSound("rifle.wav"));
        getSomTiro().setVolume(70);

        setImage(getImagemCombinada());

        setJogoAcabou(false);
    }

    // chama o game over caso o inimigo atinja o canto inferior
    public void act() {
        if (!((Cenario) getWorld()).jogoAcabou()) {
            setLocation(getX(), getY() + 1);

            if (getY() == 705) {
                // Chama o método na classe Cenário para encerrar o jogo
                ((Cenario) getWorld()).encerrarJogo();
            }
        }

        verificarColisao();
    }

    // gera e retorna uma palavra aleatoria
    private String getPalavraAleatoria() {
        Random random = new Random();
        int indice = random.nextInt(palavras.size());
        return palavras.get(indice);
    }
}
