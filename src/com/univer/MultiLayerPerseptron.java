package com.univer;

import net.sf.javaml.clustering.SOM;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import sun.nio.cs.KOI8_R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiLayerPerseptron {
    private static double[] dataSetRow = new double[20];
    private static double[] weights = new double[10];
    private static double[] dataOutRow = new double[2];
    public static void main(String[] args) throws Exception {
        NeuralNetwork neuralNetwork = new MultiLayerPerceptron(20,5,1);
        DataSet trainingSet = new DataSet(20,1);
        BufferedReader inData = new BufferedReader(new FileReader("/home/yury/BSTU/lab3/mainComponents"));
        BufferedReader outData = new BufferedReader(new FileReader("/home/yury/BSTU/lab3/main"));
        Object[] stringLies = inData.lines().toArray();
        Object[] stringLies2 = outData.lines().toArray();
        for(int i=0; i<311029;i++) {
            dataSetRow = new double[20];
            Double outValue = Double.valueOf(stringLies2[i].toString());
            String[] params = stringLies[i].toString().split(":");
            for(int j=0;j<20;j++) {
                dataSetRow[j] = Double.valueOf(params[j]);
            }
            trainingSet.addRow(dataSetRow, new double[]{outValue});
        }
        Double[] ss = neuralNetwork.getWeights();
        neuralNetwork.learn(trainingSet);
        neuralNetwork.learn(trainingSet);
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
        neuralNetwork.save("/home/yury/BSTU/lab3/nnet.nnet");
    }
}
