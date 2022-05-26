package org.example.classes;

public class ParametersForPSO {
    public Double maxInertia;
    public Double minInertia;
    public Double selfCoefficient;
    public Double selfDeviation;
    public Double swarmCoefficient;
    public Double swarmDeviation;

    public ParametersForPSO(Double maxInertia, Double minInertia, Double selfCoefficient, Double selfDeviation, Double swarmCoefficient, Double swarmDeviation) {
        this.maxInertia = maxInertia;
        this.minInertia = minInertia;
        this.selfCoefficient = selfCoefficient;
        this.selfDeviation = selfDeviation;
        this.swarmCoefficient = swarmCoefficient;
        this.swarmDeviation = swarmDeviation;
    }
}
