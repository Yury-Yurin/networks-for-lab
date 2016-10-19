package com.univer;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import sun.nio.cs.KOI8_R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiLayerPerseptron {
    private static double[] dataSetRow = new double[41];
    private static double[] weights = new double[41];
    private static double[] dataOutRow = new double[2];
    public static void main(String[] args) throws Exception {
        Kohonen neuralNetwork = new Kohonen(41,1);
        DataSet trainingSet = new DataSet(41,1);
        BufferedReader inData = new BufferedReader(new FileReader("/home/blondeks/lab3/correctedNew"));
        BufferedReader outData = new BufferedReader(new FileReader("/home/blondeks/lab3/attacks"));
        Object[] stringLies = inData.lines().toArray();
        Object[] stringLies2 = outData.lines().toArray();
        for(int i=0; i<311029;i++) {
            dataSetRow = new double[41];
            Double outValue = Double.valueOf(stringLies2[i].toString());
            String[] params = stringLies[i].toString().split(",");
            for(int j=0;j<41;j++) {
                dataSetRow[j] = Double.valueOf(params[j]);
            }
            trainingSet.addRow(dataSetRow, new double[]{outValue});
        }
        Double[] ss = neuralNetwork.getWeights();
        neuralNetwork.learn(trainingSet);
      /*  Integer iter = 0;
        for(DataSetRow dataSetRow1 : trainingSet.getRows()) {
            iter++;
            neuralNetwork.setInput(dataSetRow1.getInput());
            neuralNetwork.calculate();
            double t = neuralNetwork.getOutput()[0];
            if(t<0.5) t=0.0;
            else t = 1.0;
            if(dataSetRow1.getDesiredOutput()[0] == t){
                System.out.println("GooD");
            }
            else throw new Exception("BAAADDDD: " + iter);
        }*/
        neuralNetwork.save("/home/blondeks/lab3/nnet.nnet");
    }
}
