package matrix_energy.genesis.kinetic;

import matrix_energy.genesis.kinetic.KineticShaft.KineticShaftBuild;

/**
 * @author dg
 */
public class ForceInstance {

    public interface CombinationBuild {

    }

    public interface KineticShaft {
        /**
         * 传动轴的最后一个<br>
         * 在连接的传动轴中x or y中较大的一个<br>
         * 并没有先后顺序，只是两头而已 <br>
         * 只有两头的有，中间的不应该有
         */
        KineticShaftBuild first = null;
        /**
         * 传动轴的最后一个<br>
         * 在连接的传动轴中x or y中较大的一个<br>
         * 并没有先后顺序，只是两头而已 <br>
         * 只有两头的有，中间的不应该有
         */
        KineticShaftBuild last = null;

        matrix_energy.genesis.kinetic.KineticShaft.KineticShaftBuild getFirst();

        void setFirst(matrix_energy.genesis.kinetic.KineticShaft.KineticShaftBuild KineticShaftBuild);

        matrix_energy.genesis.kinetic.KineticShaft.KineticShaftBuild getLast();

        void setLast(matrix_energy.genesis.kinetic.KineticShaft.KineticShaftBuild KineticShaftBuild);
    }
}
