package components.theme.neural_net;

import java.util.ArrayList;
import java.util.Scanner;

public class TestNet
{
    /**
     * Takes in the raw results from the network and translates it into a more human-readable format
     * @param rawResults An ArrayList<Double> of the decimal decisions from the neural network
     * @return ArrayList<Integer> The rounded and human-readable version of the output
     */
    private static ArrayList getHumanReadable(ArrayList<Double> rawResults)
    {
        ArrayList<Integer> results = new ArrayList<>();

        for(Double temp : rawResults){
            if(temp > 0.5){ // is the decision more than 0.5?
                results.add(1); // if so add one to the results
            }else{
                results.add(0);
            }
        }
        return results;
    }

    public static void main(String[] args)
    {
        int[] layout = {2, 4, 1};

        Network BinaryLogicNetwork = new Network(layout);
        Neuron.rate = 0.09;
        Neuron.momentum = 0.015;

        BinaryLogicNetwork.readWeights("src/components/theme/neural_net/weights.txt");

        boolean trained = false;

        while(!trained){
            double err = 0;

            int[][] inputs = {{0, 0}, {0, 1}, {1, 0}};
            double[][] outputs = {{0}, {1}, {1}};

            for(int i = 0; i < inputs.length; i++){
                BinaryLogicNetwork.setInputValues(inputs[i]);
                BinaryLogicNetwork.advance();
                BinaryLogicNetwork.backProp(outputs[i]);

                err += BinaryLogicNetwork.calculateNetworkCost(outputs[i]);
            }

            System.out.println("[Cost] " + err);
            if(err < 0.01){
                System.out.println("System Trained");
                trained = true;
                BinaryLogicNetwork.writeToFile("src/components/theme/neural_net/weights.txt");
            }
        }

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("Enter value A: ");
            int valA = in.nextInt();
            System.out.println("Enter value B: ");
            int valB = in.nextInt();
            int[] ins = {valA, valB};

            BinaryLogicNetwork.setInputValues(ins);
            BinaryLogicNetwork.advance();
            System.out.println(getHumanReadable(BinaryLogicNetwork.getDecision()));
        }
    }
}
