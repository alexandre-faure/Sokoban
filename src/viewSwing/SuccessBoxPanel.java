package viewSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b2c3a5f6-0913-4a9c-9e5b-adff3ba2ed14")
public class SuccessBoxPanel extends JPanel {
    @objid ("c44d68e6-959c-4c0e-8d61-987e1b4f342a")
    private GamePanel gamePanel;

    @objid ("0cce06cf-a30e-42fc-b8f6-61ee3a542868")
    public SuccessBoxPanel(GamePanel gamePanel) {
        setLayout(new GridBagLayout());
        this.gamePanel = gamePanel;
        
        final Color DARK_GREEN = new Color(0,120,0);
        
        // Ajout d'une bordure
        setBorder(new LineBorder(DARK_GREEN, 3));
        
        // Texte de félicitations
        JLabel winLabel1 = new JLabel("FÉLICITATIONS");
        winLabel1.setFont(new Font("Arial", Font.BOLD, 35));
        winLabel1.setHorizontalAlignment(JLabel.CENTER);
        winLabel1.setForeground(DARK_GREEN);
        
        JLabel winLabel2 = new JLabel("Vous avez gagné !");
        winLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        winLabel2.setHorizontalAlignment(JLabel.CENTER);
        
        // Bouton retour à l'accueil
        JButton homeButton = new JButton("Retour à l'accueil");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On retourne vers l'accueil
                setVisible(false);
                gamePanel.getMainWindow().setActivePanel("home");
            }
        });
        
        // Bouton rejouer
        JButton replayButton = new JButton("Rejouer le niveau");
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On rejoue
                setVisible(false);
                gamePanel.beginLevel(gamePanel.getMainWindow().getController().getLevel());
            }
        });
        
        // Bouton quitter
        JButton quitButton = new JButton("Quitter");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.getMainWindow().dispose();  // Quitter
            }
        });
        
        // Ajout des éléments à la sucessBox
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Texte de félicitations
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 50, 0, 50);
        add(winLabel1, gbc);
        
        gbc.insets = new Insets(0, 50, 15, 50);
        gbc.gridy = 1;
        add(winLabel2, gbc);
        
        // Boutons
        homeButton.setPreferredSize(replayButton.getPreferredSize());
        quitButton.setPreferredSize(replayButton.getPreferredSize());
        
        homeButton.setMinimumSize(replayButton.getMinimumSize());
        quitButton.setMinimumSize(replayButton.getMinimumSize());
        
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        gbc.gridx = 0;
        add(homeButton, gbc);
        
        gbc.gridx = 1;
        add(replayButton, gbc);
        
        gbc.gridx = 2;
        add(quitButton, gbc);
        
        // Initialement invisible
        setVisible(false);
    }

}
