package control;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.CellStatus;
import entity.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@objid ("be3cafe8-5228-4241-919b-5f9c8884853f")
public class TestBoiteNoire {
    @objid ("0ae5c043-f9d5-484a-aef1-31096fdb1316")
     Controller controller;

    @objid ("5470a6e9-b8eb-4f51-b359-91416299aac0")
    @BeforeEach
    void init() {
        // Initialisation au début de chaque nouveau test
        controller = new Controller();
    }

    @objid ("281b2618-e4e2-45f4-bff6-e7efc8165d1b")
    @Test
    public void testLevelCreation() {
        // On teste l'ouverture des niveaux valides
        for (int i = 1; i <= 5; i++) {
            controller.beginGame(i);
            assertEquals(controller.getLevel(), i);
        }
        
        // On demande un niveau qui n'existe pas
        assertThrows(InvalidParameterException.class, () -> {
            controller.beginGame(0);
        }, "Un niveau inexistant a été demandé.");
        
        assertThrows(InvalidParameterException.class, () -> {
            controller.beginGame(6);
        }, "Un niveau inexistant a été demandé.");
    }

    @objid ("97ab260c-61d5-4d09-9c2f-b79403e93453")
    @Test
    void testValidBoardConfiguration() {
        // On teste que le niveau a l'allure souhaitée
        controller.beginGame(1);
        
        // On vérifie une partie de la structure du niveau
        CellStatus[][] board = controller.getBoardStatus();
        assertEquals(board[2][1], CellStatus.PLAYER);
        assertEquals(board[2][3], CellStatus.BOX);
        assertEquals(board[3][4], CellStatus.TARGET);
        
        assertEquals(board[0][0], CellStatus.WALL);
        assertEquals(board[0][5], CellStatus.WALL);
        assertEquals(board[4][0], CellStatus.WALL);
        assertEquals(board[4][5], CellStatus.WALL);
        assertEquals(board[1][1], CellStatus.FLOOR);
    }

    @objid ("f4da1900-344f-47e5-b3aa-162af4db485b")
    void moveAccordingToDirectionSequence(char[] directionSequence) {
        // Fonction pour faire une séquence de mouvement
        for (char direction : directionSequence) {
            switch(direction) {
            case 'l':
                controller.move(Direction.LEFT);
                break;
            case 'r':
                controller.move(Direction.RIGHT);
                break;
            case 'u':
                controller.move(Direction.UP);
                break;
            case 'd':
                controller.move(Direction.DOWN);
                break;
            }
        }
    }

    @objid ("720b0dda-ab07-46c1-942f-9b4717149d56")
    @Test
    void testMovements() {
        // On vérifie que les déplacements fonctionnent
        controller.beginGame(1);
        
        // Position initiale du player
        assertEquals(controller.getBoardStatus()[2][1], CellStatus.PLAYER);
        
        // Déplacement à droite
        controller.move(Direction.RIGHT);
        assertEquals(controller.getBoardStatus()[2][2], CellStatus.PLAYER);
        
        // Déplacement à gauche
        controller.move(Direction.LEFT);
        assertEquals(controller.getBoardStatus()[2][1], CellStatus.PLAYER);
        
        // Déplacement en haut
        controller.move(Direction.UP);
        assertEquals(controller.getBoardStatus()[1][1], CellStatus.PLAYER);
        
        // Déplacement en bas
        controller.move(Direction.DOWN);
        assertEquals(controller.getBoardStatus()[2][1], CellStatus.PLAYER);
    }

    @objid ("729b6291-db4a-4fc5-9481-4974c85ac766")
    @Test
    void testValidMoves() {
        // L'objectif est de tester les mouvements valides et invalides par la configuration du jeu
        controller.beginGame(1);
        
        // Position initiale du player
        assertEquals(controller.getBoardStatus()[2][1], CellStatus.PLAYER);
        assertEquals(controller.getBoardStatus()[2][0], CellStatus.WALL);
        
        // Déplacement à gauche interdit à cause du mur
        controller.move(Direction.LEFT);
        assertEquals(controller.getBoardStatus()[2][1], CellStatus.PLAYER);
        
        // Déplacement de caisse valide
        controller.move(Direction.RIGHT);
        assertEquals(controller.getBoardStatus()[2][2], CellStatus.PLAYER);
        assertEquals(controller.getBoardStatus()[2][3], CellStatus.BOX);
        controller.move(Direction.RIGHT);
        assertEquals(controller.getBoardStatus()[2][3], CellStatus.PLAYER);
        assertEquals(controller.getBoardStatus()[2][4], CellStatus.BOX);
        
        // Déplacement caisse contre mur interdit
        assertEquals(controller.getBoardStatus()[2][5], CellStatus.WALL);
        controller.move(Direction.RIGHT);
        assertEquals(controller.getBoardStatus()[2][3], CellStatus.PLAYER);
        assertEquals(controller.getBoardStatus()[2][4], CellStatus.BOX);
        
        // Déplacement d'une caisse sur une cible valide
        controller.move(Direction.UP);
        controller.move(Direction.RIGHT);
        assertEquals(controller.getBoardStatus()[1][4], CellStatus.PLAYER);
        assertEquals(controller.getBoardStatus()[2][4], CellStatus.BOX);
        assertEquals(controller.getBoardStatus()[3][4], CellStatus.TARGET);
        controller.move(Direction.DOWN);
        assertEquals(controller.getBoardStatus()[2][4], CellStatus.PLAYER);
        assertEquals(controller.getBoardStatus()[3][4], CellStatus.BOX_TARGET);
    }

    @objid ("d02fc836-8269-4e3e-95a4-71eaecaf69c7")
    @Test
    void testHasWon() {
        // On vérifie que le joueur gagne à condition que les caisses soient sur les cibles
        controller.beginGame(2);
        
        char[] directionSequence1 = {'d','l','d','l','r','u','r','u','u','l','l','l','d','d','d','r','d','l'};
        char[] directionSequence2 = {'u','r','r','u','l','d','l','u','u'};
        char[] directionSequence3 = {'d','r','r','u','r','u','l'};
        
        assertTrue(!controller.hasWon());
        
        // On positionne correctement la première boîte
        moveAccordingToDirectionSequence(directionSequence1);
        assertEquals(controller.getBoardStatus()[5][1], CellStatus.BOX_TARGET);
        assertTrue(!controller.hasWon());
        
        // On positionne correctement la deuxième boîte
        moveAccordingToDirectionSequence(directionSequence2);
        assertEquals(controller.getBoardStatus()[1][2], CellStatus.BOX_TARGET);
        assertTrue(!controller.hasWon());
        
        // On positionne correctement la dernière boîte
        moveAccordingToDirectionSequence(directionSequence3);
        assertEquals(controller.getBoardStatus()[1][3], CellStatus.BOX_TARGET);
        assertTrue(controller.hasWon());
    }

}
