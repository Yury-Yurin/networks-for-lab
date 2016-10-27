package com.univer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Parser {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("/home/yury/BSTU/lab3/corrected"));
        Integer k = 0;
        Object[] stringList = in.lines().toArray();
        String[] stringList2 = new String[311029];
        String[] stringList3 = new String[311029];
        for (Object str : stringList) {
            int index = str.toString().lastIndexOf(",");
            String[] params = str.toString().substring(0, index).split(",");
            // udp - 0, tcp - 1
            if (params[1].equals("udp")) params[1] = "0";
            if (params[1].equals("tcp")) params[1] = "0.5";
            if (params[1].equals("icmp")) params[1] = "1";

            for (int i = 0; i < 4; i++) {
                int t = 0;
                if (!params[i].equals("0") && !params[i].equals("1") && !params[i].equals("1.00") && !params[i].equals("0.00") && i != 1) {
                    for (int j = 0; j < params[i].length(); j++) {
                        t += params[i].toCharArray()[j];
                    }
                    Double size = Math.pow(10, String.valueOf(t).length());
                    params[i] = String.valueOf(t / size);
                }
            }
            for (int i = 4; i < params.length; i++) {
                if (!params[i].equals("0") && !params[i].equals("1") && !params[i].equals("1.00") && !params[i].equals("0.00") ) {
                    Double value = Double.valueOf(params[i]);
                    if(value > 1.0) {
                        Double size = Math.pow(10, params[i].length());
                        params[i] = String.valueOf(value / size);
                    }
                }
            }
            stringList2[k] = "";
            for (int i=0;i<params.length;i++) {
                if (!Objects.equals(i, params.length - 1))
                    stringList2[k] += params[i] + ",";
                else {
                    stringList2[k] += params[i];
                }
            }
            k++;
        }
        k = 0;
        in.close();
        in = new BufferedReader(new FileReader("/home/yury/BSTU/lab3/corrected"));
        stringList = in.lines().toArray();
        for (Object str : stringList) {
            int index = str.toString().lastIndexOf(",");
            String prob = str.toString().substring(index + 1, str.toString().lastIndexOf("."));
            stringList3[k] = "";
            stringList3[k] = changeProbe(prob);
            k++;
        }
        File file2 = new File("/home/yury/BSTU/lab3/attacks");
        FileWriter f2 = new FileWriter(file2);
        for(int i=0;i<311029;i++) {
            f2.append(stringList3[i] + "\n");
        }
        f2.close();
        File file1 = new File("/home/yury/BSTU/lab3/correctedNew");
        FileWriter f1 = new FileWriter(file1);
        for(int i=0;i<311029;i++) {
            f1.append(stringList2[i] + "\n");
        }
        f1.close();


    }

    public static String changeProbe(String str) throws Exception {
        if (!str.equals(""))
            switch (str) {
                case "nmap":
                    return "1";
                case "ipsweep":
                    return "1";
                case "portsweep":
                    return "1";
                case "satan":
                    return "1";
                default:
            }
        else throw new Exception();
        return "0";
    }

}
