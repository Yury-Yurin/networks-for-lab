package com.univer;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiLayerPerseptron {
    private static double[] dataSetRow = new double[41];
    private static double[] weights = new double[41];
    private static double[] dataOutRow = new double[2];
    public static void main(String[] args) throws InterruptedException, IOException {
        NeuralNetwork neuralNetwork = new Kohonen(41,2);
        DataSet trainingSet = new DataSet(41,2);
        BufferedReader inData = new BufferedReader(new FileReader("/home/blondeks/lab3/correctedNew"));
        BufferedReader outData = new BufferedReader(new FileReader("/home/blondeks/lab3/attacks"));
        Object[] stringLies = inData.lines().toArray();
        Object[] stringLies2 = outData.lines().toArray();
        for(int i=0; i<311029;i++) {
            Double outValue = Double.valueOf(stringLies2[i].toString());
            String[] params = stringLies[i].toString().split(",");
            for(int j=0;j<41;j++) {
                dataSetRow[j] = Double.valueOf(params[j]);
            }
            if(outValue.equals(1.0)) {
                Thread.sleep(1);
            }
            if(outValue.equals(0.0)) {
                dataOutRow[0] = 0.0;
                dataOutRow[1] = 1.0;
            }
            else {
                dataOutRow[0] = 1.0;
                dataOutRow[1] = 0.0;
            }
            trainingSet.addRow(dataSetRow, dataOutRow);
        }
        Double[] ss = neuralNetwork.getWeights();
        neuralNetwork.learn(trainingSet);
        neuralNetwork.save("/home/blondeks/lab3/nnet.nnet");
    }
}
