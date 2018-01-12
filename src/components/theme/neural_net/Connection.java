package components.theme.neural_net;

import java.util.Random;

public class Connection
{
    private Neuron connected;
    private double weight;
    private double weightDelta;

    public Connection(Neuron connected)
    {
        this.connected = connected;
        Random r = new Random();
        this.weight = r.nextGaussian(); // initialize the weight randomly to start out with
        this.weightDelta = 0.0;
    }

    public Neuron getConnected()
    {
        return connected;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeightDelta(double weight)
    {
        this.weightDelta = weight;
    }


    public double getWeightDelta()
    {
        return weightDelta;
    }
}
