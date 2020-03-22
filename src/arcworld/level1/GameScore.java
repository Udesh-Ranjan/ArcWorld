/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcworld.level1;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 */
public class GameScore extends JPanel{

    public GameScore() {
        this.setBounds(700,0,200,700);
        this.setSize(200,700);
        this.add(new JButton("But"));
    }
}
