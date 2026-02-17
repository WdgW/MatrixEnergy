package matrix_energy.lib.content

import arc.graphics.Color
import arc.struct.Seq
import matrix_energy.lib.type.item.CompositeItem
import matrix_energy.lib.type.item.MEItem
import matrix_energy.lib.type.item.PureSubstanceItem
import mindustry.content.Items
import mindustry.type.Item

/**
 * @author dg
 */
object MEItems {


    @JvmField
    var coal: MEItem? = null

    @JvmField
    var Fe: PureSubstanceItem? = null

    @JvmField
    var Fe2O3: PureSubstanceItem? = null

    @JvmField
    var Si: PureSubstanceItem? = null

    @JvmField
    var SiO2: PureSubstanceItem? = null

    @JvmField
    var Al: PureSubstanceItem? = null

    @JvmField
    var Al2O3: PureSubstanceItem? = null

    @JvmField
    var lead: MEItem? = null

    @JvmField
    var copper: MEItem? = null

    @JvmField
    var gold: PureSubstanceItem? = null

    @JvmField
    var leadZinc: MEItem? = null

    @JvmField
    var nickel: PureSubstanceItem? = null

    @JvmField
    var tin: PureSubstanceItem? = null

    @JvmField
    var starch: PureSubstanceItem? = null

    @JvmField
    var plastic: MEItem? = null

    @JvmField
    var sand: MEItem? = null

    @JvmField
    var saltpeter: MEItem? = null

    @JvmField
    var sulfide: MEItem? = null

    @JvmField
    var blackPowder: MEItem? = null

    @JvmField
    var carbonFiber: MEItem? = null

    @JvmField
    var glass: MEItem? = null

    //10% 25% 50% 75% 99%
    @JvmField
    var ironOreItem10: CompositeItem? = null

    @JvmField
    var ironOreItem25: CompositeItem? = null

    @JvmField
    var ironOreItem50: CompositeItem? = null

    @JvmField
    var ironOreItem75: CompositeItem? = null

    @JvmField
    var ironOreItem99: CompositeItem? = null

    @JvmField
    val duplicateItems: Seq<MEItem> = Seq.with(coal, copper, lead, sand, sulfide, glass)


//    fun MEItem.loadItemIcon(item: Item): MEItem {
//        Events.on(EventType.ContentInitEvent::class.java) {
//            item.let {
//                Log.info(item)
//                item.loadIcon()
//                fullIcon = item.fullIcon
//                uiIcon = item.uiIcon
//            }
//        }
//        return this
//
//    }

    class _MEItem(
        name: String,
        localizedName: String = name,
        color: Color = Color.black.cpy(),
        var item: Item? = null
    ) : MEItem(name, localizedName, color) {
        override fun loadIcon() {
            super.loadIcon()
            item?.let {
                fullIcon = it.fullIcon
                uiIcon = it.uiIcon
            }

        }

    }

    @JvmStatic
    fun load() {
        coal = _MEItem("coal", Color(0f, 0f, 0f), "煤", Items.coal)
        copper = _MEItem("copper", Color(255f, 140f, 0f), "铜", Items.copper) //Cu
        lead = _MEItem("lead", Color.valueOf("2s4mwjw"), "铅", Items.lead) //Pb
        glass = _MEItem("glass", Color.valueOf("d0fx0oi"), "玻璃", Items.metaglass)
        sand = _MEItem("sand", Color.valueOf("gk9v045"), "沙子", Items.sand)
        sulfide = _MEItem("sulfide", Color.valueOf("oxhg1ut"), "硫化物", Items.pyratite)
        Fe = PureSubstanceItem("iron", Color(192f, 192f, 192f), "铁") //
        Fe2O3 = PureSubstanceItem("iron-oxide", Color(255f, 0f, 0f), "氧化铁") //Fe2O3
        Si = PureSubstanceItem("silicon", Color(255f, 255f, 255f), "硅") //Si
        SiO2 = PureSubstanceItem("silicon-dioxide", Color(255f, 255f, 255f), "二氧化硅") //SiO2
        gold = PureSubstanceItem("gold", Color(255f, 215f, 0f), "金") //Au
        leadZinc = MEItem("lead-zinc", Color(165f, 165f, 165f), "铅锌")
        nickel = PureSubstanceItem("nickel", Color(185f, 185f, 185f), "镍") //Ni
        tin = PureSubstanceItem("tin", Color(220f, 220f, 220f), "锡") //Sn
        starch = PureSubstanceItem("starch", Color.valueOf("ri31nrz"), "淀粉")
        Al = PureSubstanceItem("aluminium", Color.valueOf("e0e0e0"), "铝") //Al
        Al2O3 = PureSubstanceItem("aluminium-oxide", Color.valueOf("e0e0e0"), "氧化铝") //Al2O3
        plastic = MEItem("plastic", Color.valueOf("5rkz0lw"), "塑钢")
        saltpeter = MEItem("saltpeter", Color.valueOf("rovf956"), "火硝")
        blackPowder = MEItem("blackPowder", Color.valueOf("7cqb1if"), "黑火药")
        carbonFiber = MEItem("carbonFiber", Color.valueOf("to9pp4p"), "碳纤维")


        //多物品
        ironOreItem10 = CompositeItem("iron-ore-10", Seq.with(Fe2O3, 1, SiO2, 6, Al2O3, 3), Color.red, "赤铁矿")
        ironOreItem25 = CompositeItem("iron-ore-25", Seq.with(Fe2O3, 1, SiO2, 2, Al2O3, 1), Color.red, "赤铁矿")
        ironOreItem50 = CompositeItem("iron-ore-50", Seq.with(Fe2O3, 2, SiO2, 1, Al2O3, 1), Color.red, "赤铁矿")
        ironOreItem75 = CompositeItem("iron-ore-75", Seq.with(Fe2O3, 6, SiO2, 1, Al2O3, 1), Color.red, "赤铁矿")
        ironOreItem99 = CompositeItem("iron-ore-99", Seq.with(Fe2O3, 99, SiO2, 1), Color.red, "赤铁矿")


    }
}
