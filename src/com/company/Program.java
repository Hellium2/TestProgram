package com.company;

import java.io.File;

public class Program {

    private SwingGUI GUI;
    private Data data;
    static File inputFilesDirectory = new File("InputFiles");
    static File mathLibDir = new File("bin/MathLib.dll");;


    public void start() {
        GUI = new SwingGUI(this);
    }


    void OpenNewFile(File directory) {
        data = new Data();
        data.InputFromFile(directory);
        GUI.ChangeStatesBox(data.getStates());
    }

    void CalculatePopulationInState(String state){
        if (data != null) {
            String[] states = new String[data.getCount()];
            String[] cities = new String[data.getCount()];
            ;
            int[] populations = new int[data.getCount()];

            data.getDataForMathLib(states, cities, populations);

            NativeCalls nat = new NativeCalls(mathLibDir);

            int res = nat.CalculatePopulationSum(states, cities, populations, data.getCount(), state);

            GUI.changeResultText(res);
        }
        else {
            GUI.ShowMessage("Сначала выберите файл и название страны");
        }
    }
}
