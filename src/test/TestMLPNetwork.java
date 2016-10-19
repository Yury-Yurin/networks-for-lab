package test;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestMLPNetwork {
    private static double[] dataSetRow = new double[41];
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(new File("/home/blondeks/lab3/nnet.nnet"));
        BufferedReader inData = new BufferedReader(new FileReader("/home/blondeks/lab3/correctedNew"));
        BufferedReader outData = new BufferedReader(new FileReader("/home/blondeks/lab3/attacks"));
        Object[] stringLines = inData.lines().toArray();
        Object[] stringLies2 = outData.lines().toArray();
        Integer k=0;
        for(int i=0;i<311029;i++) {
            String[] params = stringLines[i].toString().split(",");
            for(int j=0;j<41;j++) {
                dataSetRow[j] = Double.valueOf(params[j]);
            }
            neuralNetwork.setInput(dataSetRow);
            neuralNetwork.calculate();
            Double value = Double.valueOf(stringLies2[i].toString());
            if(value.equals(1.0)) {
                Thread.sleep(1);
            }
            double[] networkOutput = neuralNetwork.getOutput();
            if (networkOutput[0] > networkOutput[1]) {
                networkOutput[0] = 1.0;
                networkOutput[1] = 0.0;
            }
            else {
                networkOutput[0] = 0.0;
                networkOutput[1] = 1.0;
            }
            if(value.equals(networkOutput[0])) {
                k++;
            }
        }
        System.out.println(k);
    }
}
