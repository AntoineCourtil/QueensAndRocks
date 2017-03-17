package gameElements;

/**
 * Created by simon on 17/02/17.
 * Représente le plateau de jeu
 */
public interface Square {

    public Player getPlayer();

    public void setPlayer(Player player);

    public String toString();

    public boolean isEmpty();

    public boolean isEnemyQueen(Player p);
}
