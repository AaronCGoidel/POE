package components.theme.neural_net;

import java.util.ArrayList;

public class Neuron
{
    public static double rate = 0.001;
    public static double momentum = 0.01;

    private double cost;
    private double gradient;
    private double output;
    private ArrayList<Connection> links = new ArrayList<>();

    public Neuron(ArrayList<Neuron> layer)
    {
        this.cost = 0.0;
        this.gradient = 0.0;
        this.output = 0.0;

        // Create new connections between node and neurons from the last layer
        if(layer != null){
            for(Neuron neuron : layer){
                this.links.add(new Connection(neuron));
            }
        }
    }


    public void setCost(double cost)
    {
        this.cost = cost;
    }

    private void addToCost(double addition)
    {
        this.cost += addition;
    }

    public void setOutput(double output)
    {
        this.output = output;
    }

    public double getOutput()
    {
        return output;
    }

    /**
     * The sigmoid function is a way of mapping any value of x to a number between 0 and 1
     *
     * @param x double The number which we wish to "squish" into the range
     * @return double The number between 0 and 1 which corresponds to x.
     */
    private double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }

    /**
     * An implementation of the derivative of the sigmoid function.
     * Since the derivative is written in terms of sigmoid(x) the function is implemented without any calls to the original function.
     * Instead, all of the values passed will have already been put through the sigmoid function.
     *
     * @param x double A value, which has already been put through the sigmoid function
     */
    private double sigmoidPrime(double x)
    {
        return x * (1.0 - x);
    }

    /**
     * Logic to feed forward through the network
     */
    public void advance()
    {
        double sum = 0;
        if(links.size() == 0){
            return;
        }
        for(Connection link : links){
            sum += link.getConnected().getOutput() * link.getWeight();
        }
        output = sigmoid(sum);
    }

    /**
     * Back propagation logic.
     * In order to learn the network uses random guesses and compares them to known actual values.
     * This is an implementation of the cost function which compares expected results to actual results.
     * Then it adjusts the weights of the connections according to the cost.
     */
    public void backProp()
    {
        gradient = cost * sigmoidPrime(output);

        for(Connection link : links){
            link.setWeightDelta(
                    Neuron.rate *
                            (link.getConnected().getOutput() * gradient)
                            + momentum * link.getWeightDelta()); // calculates needed change in weight of a connection
            link.setWeight(link.getWeight() + link.getWeightDelta());
            link.getConnected().addToCost(link.getWeight() * gradient);
        }
        setCost(0.0);
    }

    public ArrayList<Connection> getLinks()
    {
        return links;
    }
}
