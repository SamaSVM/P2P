package com.shpp.p2p.cs.vsamchenko.exam.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /**
     * We create buttons, fields for inscriptions and a text box.
     */
    public static final JTextField babyName = new JTextField(null, GRAPH_MARGIN_SIZE);
    JLabel text = new JLabel("Name:");
    JButton buttonGraph = new JButton("Graph");
    JButton buttonClear = new JButton("Clear");
    private NameSurferDataBase data;
    private NameSurferGraph graph;


    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        addButtons();
        data = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        add(graph);
    }

    /**
     * Add buttons and their listeners.
     */
    private void addButtons() {
        // We add the inscription name.
        add(text, NORTH);

        // We add a field for an inscription.
        add(babyName, NORTH);

        // Add a schedule button and clean.
        // We add listeners.

        add(buttonGraph, NORTH);
        buttonGraph.addActionListener(this);

        add(buttonClear, NORTH);
        buttonClear.addActionListener(this);

        babyName.addKeyListener(this);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        // If you press the graph button.
        if (e.getSource() == buttonGraph) {
            addName();
        }
        // If you press the Clean button.
        if (e.getSource() == buttonClear) {
            graph.clear();
        }
    }

    /**
     * We add the listener of buttons that when the ENTER button was pressed the name was added.
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            addName();
        }
    }

    /**
     * Add our name to the list you want to display on the screen.
     */
    private void addName() {
        String name = babyName.getText().toLowerCase();
        NameSurferEntry entry = data.findEntry(name);
        if (entry != null){
            graph.addEntry(entry);
            graph.update();
        }
    }
}
