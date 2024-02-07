package viewSwing;

import java.awt.Graphics;
import javax.swing.JPanel;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.CellStatus;

@objid ("bf679cea-f2d3-4561-ade8-dfcc5757d7be")
public class GameGrid extends JPanel {
    @objid ("43b076b1-2991-4575-9c32-c971672f2c2c")
    private final int MAX_CELL_SIZE = 80;

    @objid ("ad40c78c-d6a0-41aa-bddc-50bc1d773ed6")
    private final int MIN_CELL_SIZE = 25;

    @objid ("26ed7a50-4a82-4f51-95dc-333f974b9cf2")
    private final int GRID_GAP = 1;

    @objid ("b950172d-40fb-4941-bb56-549aa3f11568")
    private CellRendering cellRendering = new CellRendering();

    @objid ("dec417a0-d8f5-4540-859f-c1358a908e42")
    private GamePanel gamePanel;

    @objid ("314aecbc-95fb-4ab6-b0f5-dbc78fbffca1")
    public GameGrid(GamePanel gamePanel) {
        // On ajoute le controleur
        this.gamePanel = gamePanel;
    }

    @objid ("da7e5638-8ae4-452e-beb9-18bebbc22685")
    @Override
    public void paint(Graphics g) {
        // Récupération du board
        CellStatus[][] board = gamePanel.getMainWindow().getController().getBoardStatus();
        int nbRow = board.length, nbCol = board[0].length;
        
        // On détermine la taille des cellules optimale
        int cellSize = Integer.min(MAX_CELL_SIZE,
                Integer.max(MIN_CELL_SIZE,
                        Integer.min((int) this.getSize().height/nbRow - GRID_GAP,
                                (int) this.getSize().width/nbCol - GRID_GAP)));
        
        // On détermine les coordonéees initiales de sorte à centrer la grille
        int x0 = this.getSize().width/2 -
                nbCol * (cellSize + GRID_GAP) / 2;
        int y0 = this.getSize().height/2 -
                nbRow * (cellSize + GRID_GAP) / 2;
        
        // On affiche les celulles
        for (int i=0; i<nbRow; i++) {
            for (int j=0; j<nbCol; j++) {
                g.drawImage(cellRendering.getCellRendering(board[i][j], cellSize),
                        x0 + j*(cellSize + GRID_GAP),
                        y0 + i*(cellSize + GRID_GAP),
                        null);
            }
        }
    }

}
