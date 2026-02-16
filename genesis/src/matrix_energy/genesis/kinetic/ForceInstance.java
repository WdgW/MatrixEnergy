package matrix_energy.genesis.kinetic;

import matrix_energy.genesis.kinetic.PropShaft.PropShaftBuild;

/**
 * @author dg
 */
public class ForceInstance {

    public interface CombinationBuild {

    }

    public interface PropShaft {
        /**
         * 传动轴的最后一个<br>
         * 在连接的传动轴中x or y中较大的一个<br>
         * 并没有先后顺序，只是两头而已 <br>
         * 只有两头的有，中间的不应该有
         */
        PropShaftBuild first = null;
        /**
         * 传动轴的最后一个<br>
         * 在连接的传动轴中x or y中较大的一个<br>
         * 并没有先后顺序，只是两头而已 <br>
         * 只有两头的有，中间的不应该有
         */
        PropShaftBuild last = null;

        matrix_energy.genesis.kinetic.PropShaft.PropShaftBuild getFirst();

        void setFirst(matrix_energy.genesis.kinetic.PropShaft.PropShaftBuild propShaftBuild);

        matrix_energy.genesis.kinetic.PropShaft.PropShaftBuild getLast();

        void setLast(matrix_energy.genesis.kinetic.PropShaft.PropShaftBuild propShaftBuild);
    }
}
