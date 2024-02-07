package viewConsole;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controller;
import entity.CellStatus;

@objid ("ba9f225e-42d2-4e3a-995b-5c1c7d841040")
public class GameGrid {
    @objid ("12fbc78d-7341-4485-8fb2-47f6cab03dbc")
    protected static void displayBoard(Controller controller) {
        // Affiche le Board
        CellStatus[][] boardStatus = controller.getBoardStatus();
        for (int i=0; i<boardStatus.length; i++) {    
            for (int j=0; j<boardStatus[0].length; j++) {
                // Affichage dans le terminal
                String cellRendering = "";
                switch (boardStatus[i][j]){
                case WALL:
                    cellRendering = "███";
                    break;
                case FLOOR:
                    cellRendering = "░░░";
                    break;
                case PLAYER:
                    cellRendering = " ☻";
                    break;
                case PLAYER_TARGET:
                    cellRendering = " ☻";
                    break;
                case TARGET:
                    cellRendering = " X";
                    break;
                case BOX:
                    cellRendering = " ◩";
                    break;
                case BOX_TARGET:
                    cellRendering = " ◩";
                    break;
                }
                
                System.out.printf("%-3s", cellRendering);
            }
            System.out.println();
        }
    }

}
