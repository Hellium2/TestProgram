package com.company;

import java.io.File;

class NativeCalls {

    NativeCalls(File f){
        System.load(f.getAbsolutePath());
    }

    native public static int CalculatePopulationSum
            (String[] states, String[] cities, int[] population, int count, String state);

}
