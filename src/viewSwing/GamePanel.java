package viewSwing;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("1facf43b-a0fb-4a61-b76e-83b88d0fcce4")
public class GamePanel extends JPanel {
    @objid ("ce8b90cf-f545-40ef-a711-7209f65cb28e")
    private JLabel levelLabel;

    @objid ("f1383c6c-c1ed-41aa-bb1f-216333ecc917")
    private Window mainWindow;

    @objid ("16308689-0be5-40a9-a09a-b55f18d270be")
    private SuccessBoxPanel sucessBox;

    @objid ("78ff9952-e540-4dec-86dc-a2f4633a9cd8")
    private GameGrid gameGrid;

    @objid ("fcd90a46-b9e6-4ce1-9a3c-61b3a2b1ffb5")
    public GamePanel(Window mainWindow) {
        super(new GridBagLayout());
        this.mainWindow = mainWindow;
        
        // Création du bouton pour revenir à l'accueil
        JButton backButton = new JButton("↩");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setActivePanel("home");  // Basculer vers l'interface de choix du niveau
            }
        });
        
        // Création du titre
        levelLabel = new JLabel("Niveau");
        levelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        levelLabel.setHorizontalAlignment(JLabel.CENTER);
        
        
        // Bouton rejouer
        JButton replayButton = new JButton("⟳");
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On rejoue
                sucessBox.setVisible(false);
                beginLevel(mainWindow.getController().getLevel());
            }
        });
        
        // Création de la grille de jeu
        gameGrid = new GameGrid(this);
        
        // On crée la sucess box
        sucessBox = new SuccessBoxPanel(this);
        
        // Ajouter les éléments au panel
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Retour à la page d'accueil
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        add(backButton, gbc);
        
        // Boutton de replay
        gbc.gridx = 2;
        add(replayButton, gbc);
        
        // Titre de l'onglet
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        add(levelLabel, gbc);
        
        // Plateau de jeu et sucessBox sont ajoutés sur deux calques superposés
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 10, 20, 10);
        
        add(gameGrid, gbc);
        
        // Ajout d'un calque suplémentaire
        GridBagConstraints gbcSucessBox = new GridBagConstraints();
        
        gbcSucessBox.gridx = 0;
        gbcSucessBox.gridy = 1;
        gbcSucessBox.gridwidth = GridBagConstraints.REMAINDER;
        gbcSucessBox.weighty = 1;
        gbcSucessBox.fill = GridBagConstraints.NONE;
        gbcSucessBox.insets = new Insets(20, 10, 20, 10);
        
        add(sucessBox, gbcSucessBox);
        setComponentZOrder(sucessBox,1);
    }

    @objid ("ca6cf6c0-f989-4a99-bcc4-fffb785f40c4")
    void beginLevel(int level) {
        mainWindow.requestFocus();
        mainWindow.getController().beginGame(level);
        levelLabel.setText("Niveau "+ level);
        updateGameGrid();
        repaint();
    }

    @objid ("1d70a283-1f10-4e2f-90c6-e05a68f1bc05")
    void updateGameGrid() {
        gameGrid.repaint();
        
        // Si le joueur a gagné, on affiche le message de succès
        if (mainWindow.getController().hasWon() && !sucessBox.isVisible()) {
            sucessBox.setVisible(true);
            repaint();
        }
        else if (!mainWindow.getController().hasWon() && sucessBox.isVisible()) {
            sucessBox.setVisible(false);
            repaint();
        }
    }

    @objid ("c3a4c305-7409-49a0-9c08-1f3966a222e9")
    protected Window getMainWindow() {
        return mainWindow;
    }

}
