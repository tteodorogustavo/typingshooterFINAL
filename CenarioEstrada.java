import greenfoot.*;

public class CenarioEstrada extends Cenario {
    private String nomePlayer;

    public CenarioEstrada(Historico historico) {
        super(historico, false);

        setBackground("cenario.png");
        nomePlayer = Greenfoot.ask("Insira seu nome");
        setNomePlayer(nomePlayer);
    }
}
