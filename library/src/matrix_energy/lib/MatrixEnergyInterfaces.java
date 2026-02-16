package matrix_energy.lib;

public class MatrixEnergyInterfaces {
    public interface MatrixEnergy {
    }

    public interface MatrixEnergyItem extends MatrixEnergy {}

    public interface Composite extends MatrixEnergyItem {}

    public interface PureSubstance extends MatrixEnergyItem {}

    public interface MatrixEnergyForce extends MatrixEnergy {}


}

