package viewConsole;

import java.util.Scanner;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controller;

@objid ("6923cc22-171b-44e2-b4df-395d50fe943c")
public class InteractionHandler {
    @objid ("bf2d9033-0ba4-4db2-b8d7-61711d4a5dfd")
    private static Scanner scanner = new Scanner(System.in);

    @objid ("8ef612e9-066f-46f8-806c-5bd0c3b6a8b0")
    private static ConsoleRequest readRequest(String line, QuestionStatus questionStatus) {
        // Permet d'interpréter la commande saisie par l'utilisateur selon le contexte de la question
        switch(line) {
        case "h":
            displayHelp();
            return ConsoleRequest.HELP;
        case "q":
            scanner.close();
            return ConsoleRequest.QUIT;
        case "n":
            // On n'accepte cette commande que si la partie est en cours
            if (questionStatus == QuestionStatus.MOVE)
                return ConsoleRequest.REPLAY;
        case "u":
            if (questionStatus == QuestionStatus.MOVE)
                return ConsoleRequest.MOVE_UP;
        case "d":
            if (questionStatus == QuestionStatus.MOVE)
                return ConsoleRequest.MOVE_DOWN;
        case "l":
            if (questionStatus == QuestionStatus.MOVE)
                return ConsoleRequest.MOVE_LEFT;
        case "r":
            if (questionStatus == QuestionStatus.MOVE)
                return ConsoleRequest.MOVE_RIGHT;
        case "yes":
            // On n'accepte cette commande que si la question attend une réponse par oui ou non
            if (questionStatus == QuestionStatus.YES_NO)
                return ConsoleRequest.YES;
        case "no":
            // On n'accepte cette commande que si la question attend une réponse par oui ou non
            if (questionStatus == QuestionStatus.YES_NO)
                return ConsoleRequest.NO;
        default:
            // Si on demande un niveau
            if (questionStatus == QuestionStatus.LEVEL) {
                try {
                    int level = (int) Integer.valueOf(line);
                    if (level >= 1 && level <= 5)
                        return ConsoleRequest.LEVEL;
                    else
                        System.out.println("Veuillez choisir un niveau entre 1 et 5.");
                } catch (NumberFormatException nfe) {}
            }
        }
        // Si aucune commande acceptée n'est détectée on renvoie unkown
        System.out.println("En cas d'oubli des commandes, affichez l'aide avec 'h'.");
        return ConsoleRequest.UNKNOWN;
    }

    @objid ("fc625d58-6561-4b01-92aa-03ff99fb5c64")
    protected static ConsoleRequest askYesNo(String message) {
        // Permet de poser une question par laquelle on ne répond que par oui ou par non
        ConsoleRequest request;
        do {
            System.out.print(message);
            String line = scanner.nextLine().toLowerCase();
            request = readRequest(line, QuestionStatus.YES_NO);
        }
        while (!(request == ConsoleRequest.QUIT ||
                request == ConsoleRequest.YES ||
                request == ConsoleRequest.NO ));
        return request;
    }

    @objid ("335c234d-0f19-47df-86e9-ac2ffa062102")
    protected static ConsoleRequest askMove(String message) {
        // Permet de poser une question par laquelle on ne répond que par oui ou par non
                ConsoleRequest request;
                do {
                    System.out.print(message);
                    String line = scanner.nextLine().toLowerCase();
                    request = readRequest(line, QuestionStatus.MOVE);
                }
                while (request == ConsoleRequest.UNKNOWN ||
                        request == ConsoleRequest.HELP);
        return request;
    }

    @objid ("6fc5042d-5bf5-4fa2-b466-179f9dc7f2b9")
    protected static ConsoleRequest askLevel(String message, Controller controller) {
        // Demande au joueur le niveau de son choix
        ConsoleRequest request = ConsoleRequest.UNKNOWN;
        int level = -1;
        while (!(level>=1 && level<=5) &&
              request != ConsoleRequest.QUIT) {
            System.out.print(message);
            String line = scanner.nextLine().toLowerCase();
            request = readRequest(line, QuestionStatus.LEVEL);
            
            // Si la réponse est bien un niveau, on récupère sa valeur
            if (request == ConsoleRequest.LEVEL) {
                try {
                    level = (int) Integer.valueOf(line);
                }
                catch(NumberFormatException nfe) {}
            }
        };
        
        if (request == ConsoleRequest.LEVEL)
            controller.beginGame(level);
        return request;
    }

    @objid ("251dae18-11b1-4318-bf4c-4f3eb6fbdd90")
    private static void displayHelp() {
        // On énnonce les différentes commandes existantes
        System.out.println("\n████████████████ Comment jouer ? ████████████████\n"
                + "Les commandes spéciales sont :\n"
                + "  -> 'q' : pour quitter (quit)\n"
                + "  -> 'h' : pour afficher les commandes (help).\n"
                + "  -> 'n' : pour recommencer le niveau (new game).\n"
                + "\n"
                + "Les commandes pour se déplacer sont :\n"
                + "  -> 'u' : pour aller vers le haut (up)\n"
                + "  -> 'd' : pour aller vers le bas (down)\n"
                + "  -> 'r' : pour aller vers la droite (right)\n"
                + "  -> 'l' : pour aller vers la gauche (left)\n"
                + "█████████████████████████████████████████████████\n");
    }

}
