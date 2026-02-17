package matrix_energy.genesis.kinetic

import arc.func.Prov

/**
 * @author dg
 */
open class ForceNode(name: String, localizedName: String = name) : KineticBlock(name) {

    init {
        this.localizedName = localizedName
        buildType = Prov { KineticNodeBuild() }
    }

    override fun setStats() {
        super.setStats()
        //        stats.add(Stat.powerRange, range, StatUnit.blocks);
    }

    override fun init() {
        super.init()
        //        updateClipRadius((range + 1) * tilesize);
    }


    open inner class KineticNodeBuild : KineticBuild() {

    }
}
