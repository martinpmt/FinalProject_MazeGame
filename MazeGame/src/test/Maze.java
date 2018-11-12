/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.JFrame;
import view.*;

/**
 *
 * @author Martin Paramarta / 175314090
 * @author Gregorius Bryan Osaldi / 175314111
 */
public class Maze {

    public static void main(String[] args) {
        Maze maze = new Maze();
    }

    public Maze() {
        JFrame frame = new JFrame();
        frame.setTitle("Maze Game");
        frame.add(new TempatPanel());
        frame.setSize(455, 475);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
