import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class InimigoVerde here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class InimigoVermelho extends Inimigo {
    private ArrayList<String> palavras;

    public InimigoVermelho() {
        super();

        palavras = new ArrayList<>();
        palavras.add("brincar");
        palavras.add("carro");
        palavras.add("jardim");
        palavras.add("loucura");
        palavras.add("sorriso");
        palavras.add("frutas");
        palavras.add("passar");
        palavras.add("cantar");
        palavras.add("mergulho");
        palavras.add("barulho");
        palavras.add("pintura");
        palavras.add("cachorro");
        palavras.add("feliz");
        palavras.add("abacate");
        palavras.add("telefone");
        palavras.add("girafa");
        palavras.add("bolacha");
        palavras.add("castelo");
        palavras.add("teclado");

        setEstaDerrotado(false);
        setAguardandoRemocao(false);

        setPalavra(getPalavraAleatoria());
        Color vermelhoEscuro = new Color(150, 0, 0);
        setImagemTexto(new GreenfootImage(getPalavra(), 20, Color.WHITE, vermelhoEscuro, null));
        GreenfootImage imagemInimigo = new GreenfootImage("zombievermelho.png");
        ;

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

    private String getPalavraAleatoria() {
        Random random = new Random();
        int indice = random.nextInt(palavras.size());
        return palavras.get(indice);
    }

}
