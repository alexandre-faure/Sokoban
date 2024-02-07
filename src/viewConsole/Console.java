package viewConsole;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controller;
import entity.Direction;

@objid ("cca4341e-73eb-4f6c-bdd7-136cc6d4ce57")
public class Console {
    @objid ("23081337-30ee-485f-9bca-26175d8a1a5e")
    private Controller controller = new Controller();

    @objid ("ec6ee5a1-b6ec-494a-b5cf-b810ec775080")
    public static void main(String[] args) {
        new Console();
    }

    @objid ("2453a092-157d-4987-9429-fbbde197d7ee")
    public Console() {
        // On demande si l'utilisateur veut jouer
        System.out.println("\t██████████ SOKOBAN ██████████\n"
                + "Bienvenue au jeu de sokoban !\n"
                + "Pour afficher les commandes de base du jeu, tapez 'h'.\n");
        
        ConsoleRequest continuePlaying;
        do {
            // On propose à l'utilisateur de choisir son niveau
            if (InteractionHandler.askLevel("Choisissez votre niveau (1-5) : ", controller) == ConsoleRequest.QUIT)
                break;
            
            // Début de la partie
            if (playGame() == ConsoleRequest.QUIT)
                break;
            
            continuePlaying = InteractionHandler.askYesNo("Voulez-vous continuer à jouer ? (yes/no) : ");
        } while(continuePlaying == ConsoleRequest.YES);
        
        quitGame();
    }

    @objid ("82827378-9fe8-4451-9dc7-df2b4d05145a")
    protected ConsoleRequest playGame() {
        ConsoleRequest lastRequest = ConsoleRequest.UNKNOWN;
        do {
            // Si on commence ou que la commande précédente est juste, on affiche le Board
            if (lastRequest != ConsoleRequest.HELP) {
                GameGrid.displayBoard(controller);
            }
            
            // On demande le prochain mouvement
            lastRequest = InteractionHandler.askMove("Prochain mouvement : ");
            
            // On évalue la requête et on avance ou non en conséquence
            switch (lastRequest) {
            case MOVE_LEFT:
                controller.move(Direction.LEFT);
                break;
            case MOVE_RIGHT:
                controller.move(Direction.RIGHT);
                break;
            case MOVE_UP:
                controller.move(Direction.UP);
                break;
            case MOVE_DOWN:
                controller.move(Direction.DOWN);
                break;
            case QUIT:
                return ConsoleRequest.QUIT;
            case HELP:
                break;
            case REPLAY:
                controller.beginGame(controller.getLevel());
                System.out.println("\nVotre partie a été réinitialisée...");
                break;
            default:
                System.out.println("En cas d'oubli des commandes, saisissez 'h'.");
            }
        } while(!controller.hasWon());
        
        wonGame();
        return ConsoleRequest.UNKNOWN;
    }

    @objid ("55ce0415-dc4c-4995-ad5c-2b09829070e6")
    protected void wonGame() {
        // En cas de victoire
        GameGrid.displayBoard(controller);
        System.out.println(
                "\n\t█████████████████████\n"
                + "\t██ FELICITATIONS ! ██\n"
                + "\t█████████████████████\n");
    }

    @objid ("33a75f4a-2336-4dfc-bf65-1a41dce1732d")
    private void quitGame() {
        // On quitte la partie
        System.out.println("██████████ Au revoir ! ██████████");
    }

}
