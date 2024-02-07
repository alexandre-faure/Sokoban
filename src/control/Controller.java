package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.Board;
import entity.CellStatus;
import entity.Direction;
import entity.Level;

@objid ("83c29b66-b346-49c5-889e-3092c8c995f8")
public class Controller {
    @objid ("918faf25-e4b0-4bae-b3f8-c40290dc864c")
    private int level;

    @objid ("9bdd7678-fcbe-4463-b59b-d0baa37488ea")
    private Board board;

    @objid ("cb57bf49-dcbb-4304-958e-03d75697b554")
    public void move(Direction direction) {
        // On considère que la map est toujours entourée de murs
        board.move(direction);
    }

    @objid ("630bc91a-3c06-4443-b755-f1515dff93ee")
    public CellStatus[][] getBoardStatus() {
        return board.getBoardStatus();
    }

    @objid ("17b983c4-9d5a-4319-ac31-a6f9fa211f12")
    public void beginGame(int level) {
        this.level = level;
        this.board = new Board(Level.getBoardTemplate(level));
    }

    @objid ("ac77e328-5105-4994-9a23-e44d92d9029d")
    public boolean hasWon() {
        // Renvoie si le joueur a gagné la partie ou non.
        // Cela correspond à ce que toutes les caisses soient sur une cible.
        return board.hasWon();
    }

    @objid ("42405f50-5062-4a63-b12b-d91a2bba5fda")
    public int getLevel() {
        return level;
    }

}
