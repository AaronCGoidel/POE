package components.theme.neural_net;

import java.io.*;
import java.util.ArrayList;

public class Network
{
    private ArrayList<ArrayList<Neuron>> graph = new ArrayList<>();

    public Network(int[] dimensions)
    {
        for(int layerSize : dimensions){
            ArrayList<Neuron> layer = new ArrayList<>();
            for(int i = 0; i < layerSize; i++){
                if(graph.size() == 0){
                    layer.add(new Neuron(null));
                }else{
                    layer.add(new Neuron(graph.get(graph.size() - 1)));
                }
            }
            layer.add(new Neuron(null));
            layer.get(layer.size() - 1).setOutput(1);
            graph.add(layer);
        }
    }


    public void setInputValues(int[] inputValues)
    {
        for(int i = 0; i < inputValues.length; i++){
            graph.get(0).get(i).setOutput(inputValues[i]);
        }
    }

    public double calculateNetworkCost(double[] expected)
    {
        double networkCost = 0.0;

        for(int i = 0; i < expected.length; i++){
            double e = (expected[i] - graph.get(graph.size() - 1).get(i).getOutput());
            networkCost += Math.pow(e, 2);
        }

        networkCost /= expected.length;
        networkCost = Math.sqrt(networkCost);
        return networkCost;
    }

    public void advance()
    {
        for(ArrayList<Neuron> layer : graph.subList(1, graph.size())){
            for(Neuron neuron : layer){
                neuron.advance();
            }
        }
    }

    /**
     *
     * @param expected double[] of the values that are wanted out of the network for the given input.
     */
    public void backProp(double[] expected)
    {
        /*
         * traverses through the output layer and calculates and sets the cost of each neuron using the values from
         * the array of expected values
         */
        for(int i = 0; i < expected.length; i++){
            graph.get(graph.size() - 1).get(i).setCost(expected[i] -
                    graph.get(graph.size() - 1).get(i).getOutput());
        }

        for(int i = graph.size() - 1; i >= 0; i--){
            for(Neuron neuron : graph.get(i)){
                neuron.backProp();
            }
        }
    }

    /**
     * Gets results from the network and returns an ArrayList of data from the output nodes
     * @return ArrayList<Double> The data from the output layer
     */
    public ArrayList<Double> getDecision()
    {
        ArrayList<Double> results = new ArrayList<>();

        for(Neuron neuron : graph.get(graph.size() - 1)){
            results.add(neuron.getOutput());
        }
        results.remove(results.size() - 1);
        return results;
    }

    public void writeToFile(String path)
    {
        try{
            PrintWriter writer = new PrintWriter(path);
            for(ArrayList<Neuron> layer : graph){
                for(Neuron neuron : layer){
                    for(Connection connection : neuron.getLinks()){
                        writer.println(connection.getWeight());
                    }
                }
            }writer.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void readWeights(String path)
    {
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File(path)));
            for(ArrayList<Neuron> layer : graph){
                for(Neuron neuron : layer){
                    for(Connection connection: neuron.getLinks()){
                        connection.setWeight(Double.parseDouble(in.readLine()));
                    }
                }
            }in.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
