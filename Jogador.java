import greenfoot.*;

public class Jogador extends Actor {
    private StringBuilder palavraDigitada;
    private GreenfootImage imagemDoJogador;
    private GreenfootImage imagemCombinada;
    private ImagemDeTexto imagemDeTexto;

    public Jogador() {
        palavraDigitada = new StringBuilder();
        imagemDoJogador = new GreenfootImage("torreta.png");

        // Redimensione a imagem do jogador
        int novaLargura = imagemDoJogador.getWidth() / 6; // metade da largura original
        int novaAltura = imagemDoJogador.getHeight() / 6; // metade da altura original
        imagemDoJogador.scale(novaLargura, novaAltura);

        // gira em 90 graus, pois o arquivo na pasta esta torto
        setRotation(-90);

        setImage(imagemDoJogador);
        limparPalavraDigitada();
    }

    public void act() {
        verificarTeclaPressionada();
        atualizarImagem();
    }

    private void verificarTeclaPressionada() {
        String tecla = Greenfoot.getKey();
        if (tecla != null && tecla.length() == 1) {
            // está adicionando o valor da variável tecla ao final da sequência de
            // caracteres palavraDigitada.
            palavraDigitada.append(tecla);
        } else if (tecla != null && tecla.equals("backspace") && palavraDigitada.length() > 0) {
            palavraDigitada.deleteCharAt(palavraDigitada.length() - 1);
        } else if (tecla != null && tecla.equals("enter")) {
            // Limpe a imagem definindo-a como nula (null)
            limparPalavraDigitada();
        }
    }

    private void atualizarImagem() {
        // Desenha o texto diretamente na torreta
        String digitado = palavraDigitada.toString();

        int textX = getX() + imagemDoJogador.getWidth();
        int textY = getY() + imagemDoJogador.getHeight() - 10; // -10 para nascer em baixo da imagem

        if (imagemDeTexto != null) {
            getWorld().removeObject(imagemDeTexto);
        }
        imagemDeTexto = new ImagemDeTexto(palavraDigitada.toString());
        getWorld().addObject(imagemDeTexto, getX(), getY() + imagemDoJogador.getHeight() - 10);
    }

    public String getPalavraDigitada() {
        return palavraDigitada.toString();
    }   

    public void limparPalavraDigitada() {
        palavraDigitada.setLength(0);
        if (imagemDeTexto != null) {
            getWorld().removeObject(imagemDeTexto);
            imagemDeTexto = null;
        }
    }
}
