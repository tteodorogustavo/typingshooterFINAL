import greenfoot.*;

public class Tutorial extends World {
    private GreenfootSound musicaMenu;
    private Historico historico;

    public Tutorial(Historico historico) {
        super(460, 730, 1);
        setBackground("tutorial.jpg");

        this.historico = historico;
        musicaMenu = new GreenfootSound("menuMusic.mp3");
        musicaMenu.setVolume(10);
    }

    public void act() {
        musicaMenu.playLoop();
        if (Greenfoot.isKeyDown("enter")) {
            musicaMenu.stop();
            Greenfoot.setWorld(new CenarioEstrada(historico));
        }
    }
}
