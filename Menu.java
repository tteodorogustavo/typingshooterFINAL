import greenfoot.*;

public class Menu extends World {
    private GreenfootSound musicaMenu;
    private String nomePlayer;
    private Historico historico;

    public Menu() {
        super(460, 730, 1);

        historico = new Historico();
        setBackground("menu.png");
        musicaMenu = new GreenfootSound("menuMusic.mp3");
        musicaMenu.setVolume(10);
    }

    // ao apertar "espaco", o jogo começa
    public void act() {
        musicaMenu.playLoop();
        if (Greenfoot.isKeyDown("space")) {
            musicaMenu.stop();
            Greenfoot.setWorld(new Tutorial(historico)); // Passa a instância de historico
        }
    }
}
