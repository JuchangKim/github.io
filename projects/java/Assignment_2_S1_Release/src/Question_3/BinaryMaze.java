/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_3;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BinaryMaze {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        frame.add(panel);

        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a maze file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        JButton uploadButton = new JButton("Upload Maze");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    panel.loadMaze(filePath);
                    panel.repaint();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
