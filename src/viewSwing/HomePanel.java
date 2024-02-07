package viewSwing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("aaf74fe4-6b80-4b62-bbd7-3ec639c2444e")
public class HomePanel extends JPanel {
    @objid ("250bf9ac-1a87-4eb3-8e73-d2c4d9e1603c")
    private SpinnerNumberModel spinnerModel;

    @objid ("14f1efd5-3662-431d-ac02-8a762a5c488a")
    private Window mainWindow;

    @objid ("f4a99c43-47ec-4662-83f7-c489d2a22edd")
    public HomePanel(Window mainWindow) {
        super(new GridBagLayout());
        this.mainWindow = mainWindow;
        
        // Création du titre
        JLabel titleLabel1 = new JLabel("Jeu du");
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel1.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel titleLabel2 = new JLabel("SOKOBAN");
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel2.setHorizontalAlignment(JLabel.CENTER);
        
        // Création du label et de l'input pour demander le niveau        
        JLabel levelLabel = new JLabel("Niveau");
        levelLabel.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        levelLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        spinnerModel = new SpinnerNumberModel(1, 1, 5, 1); // Valeur initiale, minimum, maximum, pas
        JSpinner levelInput = new JSpinner(spinnerModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(levelInput, "");
        levelInput.setEditor(editor);
        
        // Création du bouton vers la partie
        JButton startButton = new JButton("Commencer le jeu");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour initialiser le niveau
                mainWindow.setActivePanel("game");  // Basculer vers l'interface du jeu
            }
        });
        
        // On ajoute les éléments au panel avec GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Titre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 10, 0, 10);
        add(titleLabel1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 30, 10);
        
        add(titleLabel2, gbc);
        
        // label numéraire du niveau
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        
        add(levelLabel, gbc);
        
        // champ numéraire du niveau
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 1;
        levelInput.setPreferredSize(new Dimension(60, 25));
        
        add(levelInput, gbc);
        
        // Boutton pour lancer le niveau
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE;
        
        add(startButton, gbc);
    }

    @objid ("6ffe5ac2-756d-4a96-a14e-53f67932da67")
    protected int getLevelInput() {
        return (int) spinnerModel.getValue();
    }

}
