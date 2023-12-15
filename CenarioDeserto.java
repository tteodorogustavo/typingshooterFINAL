import greenfoot.*;

public class CenarioDeserto extends Cenario {
    public CenarioDeserto(Historico historico, String nomePlayer) {
        super(historico, true);
        setNomePlayer(nomePlayer);
        setBackground("deserto.png");
    }
}
