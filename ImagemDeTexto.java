import greenfoot.*;

public class ImagemDeTexto extends Actor {
    public ImagemDeTexto(String texto) {
        Color azulClaro = new Color(173, 216, 230);
        setImage(new GreenfootImage(texto, 20, Color.BLACK, azulClaro, null));
    }
}
