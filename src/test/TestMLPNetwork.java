package test;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestMLPNetwork {
    private static double[] dataSetRow = new double[20];
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(new File("/home/yury/BSTU/lab3/nnet.nnet"));
        BufferedReader inData = new BufferedReader(new FileReader("/home/yury/BSTU/lab3/mainComponents"));
        BufferedReader outData = new BufferedReader(new FileReader("/home/yury/BSTU/lab3/main"));
        Object[] stringLines = inData.lines().toArray();
        Object[] stringLies2 = outData.lines().toArray();
        Integer k=0;
        Double min = 1.0;
        for(int i=0;i<311029;i++) {
            dataSetRow = new double[20];
            String[] params = stringLines[i].toString().split(":");
            for(int j=0;j<20;j++) {
                dataSetRow[j] = Double.valueOf(params[j]);
            }
            neuralNetwork.setInput(dataSetRow);
            neuralNetwork.calculate();
            Double value = Double.valueOf(stringLies2[i].toString());
            Double result = 0.0;
            double[] networkOutput = neuralNetwork.getOutput();
           /* if(value.equals(1.0)) {
                Thread.sleep(0);
            }
            if(networkOutput[0]>0.01)
                result = 1.0;
            if(value.equals(result)) {
                k++;
            }
            else {
                System.out.println(value + "    " + networkOutput[0]);
            }*/
           if(networkOutput[0] > 0.9)
               k++;
        }
        System.out.println(min);
        System.out.println(k);
    }
}
