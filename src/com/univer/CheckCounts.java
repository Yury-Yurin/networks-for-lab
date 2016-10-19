package com.univer;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CheckCounts {
    public static void main(String[] args) throws IOException {
        Set<String> set = new HashSet<String>();
        BufferedReader in = new BufferedReader(new FileReader("/home/blondeks/lab3/corrected"));
        File file1 = new File("/home/blondeks/lab3/column3.txt");
        FileWriter f1 = new FileWriter(file1);
        String str = "";
        while((str = in.readLine()) != null) {
            String[] params = str.split(",");
            // udp - 0, tcp - 1
              set.add(params[10]);
        }
        Double a = (100 / (double) set.size()) / 100.0;
        Double r = 0.0;
        for(String s: set) {
            f1.write(s + " " + r.toString() + "\n");
            r += a;
        }
        System.out.println(set.size());
        System.out.println(set);
    }
}
