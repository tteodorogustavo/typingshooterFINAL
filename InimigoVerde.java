import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class InimigoVerde here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class InimigoVerde extends Inimigo {
    private ArrayList<String> palavras;

    public InimigoVerde() {
        super();
        palavras = new ArrayList<>();
        palavras.add("foca");
        palavras.add("azul");
        palavras.add("solto");
        palavras.add("neve");
        palavras.add("cafe");
        palavras.add("pao");
        palavras.add("flor");
        palavras.add("chuva");
        palavras.add("verde");
        palavras.add("ceu");
        palavras.add("bola");
        palavras.add("gato");
        palavras.add("cama");
        palavras.add("teto");
        palavras.add("uva");
        palavras.add("luz");
        palavras.add("bico");
        palavras.add("frio");
        palavras.add("pato");
        palavras.add("voo");

        setEstaDerrotado(false);
        setAguardandoRemocao(false);

        setPalavra(getPalavraAleatoria());
        Color verdeEscuro = new Color(0, 100, 0);
        setImagemTexto(new GreenfootImage(getPalavra(), 20, Color.WHITE, verdeEscuro, null));
        GreenfootImage imagemInimigo = new GreenfootImage("zombie.png");

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
