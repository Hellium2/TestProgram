package com.company;

import java.io.*;
import java.util.*;

class Data {

    private Map<String, ArrayList<City>> mapData = new TreeMap<String, ArrayList<City>>();

    private int count = 0;


    void InputFromFile(File f) {

        try(BufferedReader reader = new BufferedReader(new FileReader(f))) {

            String line = "";
            String Comma = ",";
            String[] buffer;
            ArrayList<City> city = null;


            while((line = reader.readLine()) != null){

                count++;

                buffer = line.split(Comma);

                if (mapData.containsKey(buffer[0])) {
                    city = mapData.get(buffer[0]);
                    city.add(new City(buffer[1], Integer.parseInt(buffer[2])));
                }
                else {
                    city = new ArrayList<City>();
                    city.add(new City(buffer[1], Integer.parseInt(buffer[2])));
                    mapData.put(buffer[0], city);
                }
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

    String[] getStates() {

        Set<String> states = mapData.keySet();

        String[] statesStr = new String[states.size()];
        int i = 0;
        for(String s: states){
            statesStr[i++] = s;
        }

        return statesStr;
    }

    void getDataForMathLib(String[] states, String[] cities, int[] populations){
        Set<String> s = mapData.keySet();
        ArrayList<City> buf;

        int i = 0;
        for(String state: s){
           buf = mapData.get(state);
           for(City city: buf){
               states[i]=state;
               cities[i]=city.Name;
               populations[i++] = city.Population;
           }
        }

    }

    int getCount() {
        return count;
    }


    private class City {
        String Name;
        Integer Population;
        public City(String name, Integer pop) {
            Name = name;
            Population = pop;
        }
    }


}
