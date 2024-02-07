package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("4ee76a8a-042f-4ba6-9e61-0d4f2209d05e")
public class Board {
    @objid ("9115a572-9df8-40ee-a767-658159624ebb")
    private int nbRow;

    @objid ("13081de1-6b11-495e-9bb9-0f16f8b7bab8")
    private int nbCol;

    @objid ("3164fa8c-8dfd-4101-b17e-d99352ab5f77")
    private Cell[][] grid;

    @objid ("243ce912-e2f4-4b2e-8a16-e286735d7f02")
    private Player player;

    @objid ("4341f935-d257-4c99-9b08-f0bbaccb3ec7")
    private List<Box> boxes = new ArrayList<Box>();

    @objid ("adef7714-8621-4c2a-85ac-a92e9e79f50e")
    public Board(String[] map) {
        this.nbRow = map.length;
        this.nbCol = map[0].length();
        this.grid = new Cell[nbRow][nbCol];
        
        // On crée le Board
        for (int i=0; i<nbRow; i++) {
            for (int j=0; j<nbCol; j++) {
                // Selon la lettre, on crée la cellule correspondate
                switch (map[i].charAt(j)) {
                case ' ':
                    grid[i][j] = new Floor(i, j, false);
                    break;
                case 'W':
                    grid[i][j] = new Wall(i, j);
                    break;
                case 'T':
                    grid[i][j] = new Floor(i, j, true);
                    break;
                case 'B':
                    grid[i][j] = new Floor(i, j, false);
                    Box box = new Box();
                    boxes.add(box);
                    grid[i][j].moveInMovable(box);
                    break;
                case 'P':
                    grid[i][j] = new Floor(i, j, false);
                    player = new Player();
                    grid[i][j].moveInMovable(player);
                    break;
                }
            }
        }
    }

    @objid ("7f81d217-a618-40bf-a0f5-a8bed90f46d4")
    public CellStatus[][] getBoardStatus() {
        // Renvoie le statut du plateau
        CellStatus[][] boardStatus = new CellStatus[nbRow][nbCol];
        for (int i=0; i<nbRow; i++)
            for (int j=0; j<nbCol; j++)
                boardStatus[i][j] = grid[i][j].getCellStatus();
        return boardStatus;
    }

    @objid ("00e8edd2-4419-4eeb-a8af-df098301b2e2")
    public boolean hasWon() {
        // Vérifie si toutes les caisses sont sur une cible
        return boxes.stream().allMatch(b -> b.getCell().isTarget());
    }

    @objid ("1c97c7f2-d720-4b4f-8a5c-9bc1654d95bf")
    public void move(Direction direction) {
        // Récupération les coordonnées du player
        Floor cellPlayer = player.getCell();
        int i = cellPlayer.getRow();
        int j = cellPlayer.getCol();
        
        // On instancie les coordonnées des deux celulles dans la direction demandée
        int i1 = i, i2 = i, j1 = j, j2 = j;
        switch (direction) {
        case UP:
            i1--;
            i2 -= 2;
            break;
        case DOWN:
            i1++;
            i2 += 2;
            break;
        case LEFT:
            j1--;
            j2 -= 2;
            break;
        case RIGHT:
            j1++;
            j2 += 2;
            break;
        }
        
        CellStatus nextCellStatus = grid[i1][j1].getCellStatus();
        
        // On vérifie si la cellule adjacente est libre
        if (nextCellStatus == CellStatus.WALL) return;
        
        // On vérifie si une caisse se trouve sur la case suivante
        if (    nextCellStatus == CellStatus.BOX ||
                nextCellStatus == CellStatus.BOX_TARGET) {
            // On vérifie si on peut pousser la caisse sur la case suivante
            CellStatus nextNextCellStatus = grid[i2][j2].getCellStatus();
            if (    nextNextCellStatus == CellStatus.WALL ||
                    nextNextCellStatus == CellStatus.BOX ||
                    nextNextCellStatus == CellStatus.BOX_TARGET
            ) return;
        }
        
        // On peut donc avancer
        if (    nextCellStatus == CellStatus.BOX ||
                nextCellStatus == CellStatus.BOX_TARGET ) {
            // On déplace la caisse au besoin
            grid[i2][j2].moveInMovable(grid[i1][j1].moveOutMovable());
        }
        // On déplace le personnage
        grid[i1][j1].moveInMovable(grid[i][j].moveOutMovable());
    }

}
