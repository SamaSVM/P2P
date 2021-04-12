package com.shpp.p2p.cs.vsamchenko.exam.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
    /**
     * ArrayList to store the NameSurferEntry displayed on the screen.
     */
    ArrayList<NameSurferEntry> nameList = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        update();
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        for (int i = 0; i < nameList.size(); i++) {
            nameList.clear();
        }
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        nameList.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawTheStartScreen();
        drawGraph();
    }

    /**
     * The function that sorts the nameList then adds all the elements to the screen.
     */
    private void drawGraph() {
        for (int i = 0; i < nameList.size(); i++) {
            add(createGraph(nameList.get(i), i));
        }
    }

    /**
     * A function that determines the color of the graphic and passes it to drawGraph.
     */
    private GCompound createGraph(NameSurferEntry entry, int color) {
        color = color % 4;
        GCompound graph = new GCompound();
        graph.add(drawingGraphicLines(entry, color));
        graph.add(drawingGraphicLabels(entry, color));
        return graph;
    }

    /**
     * Returns all captions that should be on the screen.
     */
    private GCompound drawingGraphicLabels(NameSurferEntry entry, int color) {
        GCompound label = new GCompound();

        // We add variables to adjust the position of the inscription.
        int deviationLine = 5;
        int adjustmentFactor = 20;

        // The size of one decade.
        int decade = getWidth() / NDECADES;

        double startX = 0;
        //Indent y-coordinate relative to the position in the ranking.
        double stepCoordinates = (getHeight() - (GRAPH_MARGIN_SIZE * 2.0)) / MAX_RANK;

        for (int i = 0; i < NDECADES - 1; i++) {
            double y = (entry.getRank(i) * stepCoordinates) + GRAPH_MARGIN_SIZE;

            if (entry.getRank(i) == 0) {
                y = getHeight() - GRAPH_MARGIN_SIZE;
            }
            if (entry.getRank(i) > entry.getRank(i + 1) && entry.getRank(i + 1) != 0) {
                y += adjustmentFactor;
            }
            // If the position in the rating is 0, then the name is *.
            String name;
            if (entry.getRank(i) == 0) {
                name = entry.name + "  *";
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
            } else {
                name = entry.name + " " + entry.getRank(i);
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
            }

            GLabel myLabel = new GLabel(name, startX + deviationLine, y - deviationLine);
            myLabel.setColor(getColor(color));
            label.add(myLabel);
            //Go to the next decade.
            startX += decade;

        }
        return label;
    }

    /**
     * Returns all lines that should be on the screen.
     */
    private GCompound drawingGraphicLines(NameSurferEntry entry, int color) {
        GCompound lines = new GCompound();
        // The size of one decade.
        int decade = getWidth() / NDECADES;

        double startX = 0;
        //Indent y-coordinate relative to the position in the ranking.
        double stepCoordinates = (getHeight() - (GRAPH_MARGIN_SIZE * 2.0)) / MAX_RANK;

        for (int i = 0; i < NDECADES - 1; i++) {
            double y = (entry.getRank(i) * stepCoordinates) + GRAPH_MARGIN_SIZE;
            if (entry.getRank(i) == 0) {
                y = getHeight() - GRAPH_MARGIN_SIZE;
            }

            double nextY;
            if (entry.getRank(i + 1) == 0) {
                nextY = getHeight() - GRAPH_MARGIN_SIZE;
            } else {
                nextY = (entry.getRank(i + 1) * stepCoordinates) + GRAPH_MARGIN_SIZE;
            }

            GLine myLine = new GLine(startX, y, startX + decade, nextY);
            myLine.setColor(getColor(color));
            lines.add(myLine);
            //Go to the next decade.
            startX += decade;
        }
        return lines;
    }

    /**
     * A function that determines what color it should be. The graph of the first record is blue, the second - red,
     * the third - magenta, the fourth - black. After that, the colors are repeated in the same sequence.
     */
    private Color getColor(int color) {
        if (color == 0) {
            return Color.BLUE;
        } else if (color == 1) {
            return Color.RED;
        } else if (color == 2) {
            return Color.MAGENTA;
        }
        return Color.BLACK;
    }

    /**
     * A function that draws the starting grid of the window.
     */
    private void drawTheStartScreen() {
        // Horizontal line from above.
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        // Horizontal line from the bottom.
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(),
                getHeight() - GRAPH_MARGIN_SIZE));
        // Vertical lines.
        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(getWidth() / NDECADES * i, 0, getWidth() / NDECADES * i, getHeight()));
        }
        // Inscriptions of years.
        for (int i = 0; i < NDECADES; i++) {
            String text = String.valueOf(START_DECADE + 10 * i);
            add(new GLabel(text, getWidth() / NDECADES * i, getHeight()));
        }
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
