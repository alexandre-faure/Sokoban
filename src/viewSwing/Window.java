package viewSwing;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controller;
import entity.Direction;

@objid ("d30194df-a6fb-4df6-b4df-9ae14e33005b")
public class Window extends JFrame implements KeyListener {
    @objid ("7018237d-e47a-478e-84f1-a18e6abd4a37")
    private CardLayout cardLayout;

    @objid ("7c84164c-695a-4a02-a43a-df3c54c3789f")
    private JPanel cardPanel;

    @objid ("b75edd40-5c48-4a3f-8efc-95fc9e2f027b")
    private String currentPanelName = "home";

    @objid ("546aed2e-5e94-4f1b-887b-f9a924d065fb")
    private Controller controller;

    @objid ("022def5f-44f2-4a07-b11a-1d22c0812ee8")
    private GamePanel gamePanel;

    @objid ("bf01d04a-ca56-49ca-a01b-1edf48092590")
    private HomePanel homePanel;

    @objid ("f86eb7cf-3889-4d15-86d5-f823a2af8bee")
    public static void main(String[] args) {
        // Affichage de la fenêtre dans le thread
        SwingUtilities.invokeLater(() -> {
            new Window().setVisible(true);
        });
    }

    @objid ("fe5628fb-b44c-4d20-8650-8f11d0a754d2")
    public Window() {
        super("Sokoban");
        
        // Création du contrôleur
        controller = new Controller();
        
        // Paramétrage de la fenêtre
        setPreferredSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Création d'un card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // On ajoute les interfaces souhaitées
        homePanel = new HomePanel(this);
        cardPanel.add(homePanel, "home");
        
        gamePanel = new GamePanel(this);
        cardPanel.add(gamePanel, "game");
        
        // On ajoute le panel à la fenêtre
        this.add(cardPanel);
        
        // On affiche l'interface de démarrage
        cardLayout.show(cardPanel, currentPanelName);
        
        // Ajout de la gestion du clavier
        this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        
        // Met en forme la fenêtre
        pack();
    }

    @objid ("b3db7232-2f01-45a1-b197-32f1af95b077")
    void setActivePanel(String panelName) {
        currentPanelName = panelName;
        cardLayout.show(cardPanel, panelName);
        if (currentPanelName.equals("game")) {
            gamePanel.beginLevel(homePanel.getLevelInput());
        }
    }

    @objid ("ee764f9a-4b9f-407a-a51b-b3f0c1e1843c")
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @objid ("7cc55b1e-60c7-4524-a92a-4a08af1a54f2")
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @objid ("eb29f1f3-e978-4b2d-8309-ae46b2eaf6f0")
    @Override
    public void keyReleased(KeyEvent e) {
        // Gérer les déplacements
        if (currentPanelName.equals("game") && !controller.hasWon()) {
            switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.move(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                controller.move(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                controller.move(Direction.LEFT);
                break;        
            case KeyEvent.VK_RIGHT:
                controller.move(Direction.RIGHT);
                break;
            }
            
            // Mise à jour de l'affichage de la grille
            gamePanel.updateGameGrid();
        }
        
        // Pour quitter
        if(e.getKeyCode() == KeyEvent.VK_Q)
            dispose();
    }

    @objid ("8a99851f-f0ee-473e-8d35-22469abbdee2")
    protected Controller getController() {
        return controller;
    }

}
