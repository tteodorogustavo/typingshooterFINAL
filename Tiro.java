import greenfoot.*;

public class Tiro extends Actor {
    private int speed;
    private Actor alvo; // O alvo para o qual o tiro está sendo disparado
    private GreenfootSound somZumbi; // Som ao acertar um zumbi
    private GreenfootSound somTiro; // Som do tiro (não utilizado no código atual)

    // Construtor da classe Tiro
    public Tiro(int speed, Actor alvo) {
        this.speed = speed; // Velocidade do tiro
        this.alvo = alvo; // Define o alvo para o qual o tiro está sendo disparado

        // Configura a imagem do tiro
        GreenfootImage imagemTiro = new GreenfootImage("tiro.png");

        // Redimensiona a imagem do tiro
        int novaLargura = imagemTiro.getWidth() / 20;
        int novaAltura = imagemTiro.getHeight() / 20;
        imagemTiro.scale(novaLargura, novaAltura);

        // Define a imagem do tiro
        setImage(imagemTiro);

        // Inicializa o som de zumbi
        somZumbi = new GreenfootSound("zombieDeath3.wav");
        somZumbi.setVolume(80);
    }

    // Método chamado a cada ato (quadro) do mundo
    public void act() {
        if (alvo != null) {
            // Calcula a direção em relação ao alvo
            int deltaX = alvo.getX() - getX();
            int deltaY = alvo.getY() - getY();
            double angle = Math.atan2(deltaY, deltaX);

            // Define a rotação do tiro para apontar na direção do alvo
            setRotation((int) Math.toDegrees(angle));

            // Move o tiro na direção do alvo com a velocidade definida
            int x = getX() + (int) (Math.cos(angle) * speed);
            int y = getY() + (int) (Math.sin(angle) * speed);
            setLocation(x, y);

            // Verifica se há colisão com um objeto da classe Inimigo
            if (isTouching(Inimigo.class)) {
                // Se houver colisão, remove o inimigo do mundo
                removeInimigo();
                // Toca o som de zumbi ao morrer
                somZumbi.play();
                // Remove o próprio tiro do mundo
                removeTiro();
            }
        }
    }

    // Define o alvo para o qual o tiro deve ser disparado
    public void atirarPara(Actor alvo) {
        this.alvo = alvo;
    }

    // Método privado para remover o inimigo do mundo
    private void removeInimigo() {
        World world = getWorld();
        if (world != null && alvo != null) {
            world.removeObject(alvo);
        }
    }

    // Método privado para remover o próprio tiro do mundo
    private void removeTiro() {
        World world = getWorld();
        if (world != null && alvo != null) {
            world.removeObject(this);
        }
    }
}
