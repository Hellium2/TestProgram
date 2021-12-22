package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

class SwingGUI extends JFrame {
    private JPanel rootPanel;
    private JComboBox stateSelectionBox;
    private JTextArea resultText;
    private JTextArea fileDirectoryText;
    private JButton chooseFileButton;
    private JButton calculateButton;
    private JFileChooser fileChooser;

    private Program prog;

    SwingGUI(Program p) {

        prog = p;

        this.setSize(600,400);

        fileChooser = new JFileChooser();

        addListeners();

        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void ChangeStatesBox(String[] states) {
        stateSelectionBox.removeAllItems();
        for(String s: states) {
            stateSelectionBox.addItem(s);
        }
    }

    void changeResultText(Integer population){
        resultText.setText(population.toString());
    }

    private void changeFileDirectoryText(File directory, int maxLen){
        String s = directory.toString();
        if (s.length()>maxLen) {
            s= s.substring(s.length()-maxLen+2,s.length());
            s= "..." + s;
        }
        fileDirectoryText.setText(s);
    }

    private void addListeners(){

        chooseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор csv файла");
                fileChooser.setCurrentDirectory(Program.inputFilesDirectory);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("*.csv",  "csv"));
                int result = fileChooser.showOpenDialog(SwingGUI.this);
                if (result == JFileChooser.APPROVE_OPTION ) {
                    prog.OpenNewFile(fileChooser.getSelectedFile());
                    changeFileDirectoryText(fileChooser.getSelectedFile(), 70);
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String state = (String) stateSelectionBox.getSelectedItem();
                prog.CalculatePopulationInState(state);
            }
        });

    }

    void ShowMessage(String message){
        JOptionPane.showMessageDialog(SwingGUI.this, message);
    }


}