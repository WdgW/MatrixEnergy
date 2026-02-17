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
        coal = _MEItem("coal", "煤", Color(0f, 0f, 0f), Items.coal)
        copper = _MEItem("copper", "铜", Color(255f, 140f, 0f), Items.copper) //Cu
        lead = _MEItem("lead", "铅", Color.valueOf("2s4mwjw"), Items.lead) //Pb
        glass = _MEItem("glass", "玻璃", Color.valueOf("d0fx0oi"), Items.metaglass)
        sand = _MEItem("sand", "沙子", Color.valueOf("gk9v045"), Items.sand)
        sulfide = _MEItem("sulfide", "硫化物", Color.valueOf("oxhg1ut"), Items.pyratite)
        Fe = PureSubstanceItem("iron", "铁", Color(192f, 192f, 192f)) //
        Fe2O3 = PureSubstanceItem("iron-oxide", "氧化铁", Color(255f, 0f, 0f)) //Fe2O3
        Si = PureSubstanceItem("silicon", "硅", Color(255f, 255f, 255f)) //Si
        SiO2 = PureSubstanceItem("silicon-dioxide", "二氧化硅", Color(255f, 255f, 255f)) //SiO2
        gold = PureSubstanceItem("gold", "金", Color(255f, 215f, 0f)) //Au
        leadZinc = MEItem("lead-zinc", "铅锌", Color(165f, 165f, 165f))
        nickel = PureSubstanceItem("nickel", "镍", Color(185f, 185f, 185f)) //Ni
        tin = PureSubstanceItem("tin", "锡", Color(220f, 220f, 220f)) //Sn
        starch = PureSubstanceItem("starch", "淀粉", Color.valueOf("ri31nrz"))
        Al = PureSubstanceItem("aluminium", "铝", Color.valueOf("e0e0e0")) //Al
        Al2O3 = PureSubstanceItem("aluminium-oxide", "氧化铝", Color.valueOf("e0e0e0")) //Al2O3
        plastic = MEItem("plastic", "塑钢", Color.valueOf("5rkz0lw"))
        saltpeter = MEItem("saltpeter", "火硝", Color.valueOf("rovf956"))
        blackPowder = MEItem("blackPowder", "黑火药", Color.valueOf("7cqb1if"))
        carbonFiber = MEItem("carbonFiber", "碳纤维", Color.valueOf("to9pp4p"))

        //多物品
        ironOreItem10 = CompositeItem("iron-ore-10", Seq.with(Fe2O3, 1, SiO2, 6, Al2O3, 3), "赤铁矿", Color.red)
        ironOreItem25 = CompositeItem("iron-ore-25", Seq.with(Fe2O3, 1, SiO2, 2, Al2O3, 1), "赤铁矿", Color.red)
        ironOreItem50 = CompositeItem("iron-ore-50", Seq.with(Fe2O3, 2, SiO2, 1, Al2O3, 1), "赤铁矿", Color.red)
        ironOreItem75 = CompositeItem("iron-ore-75", Seq.with(Fe2O3, 6, SiO2, 1, Al2O3, 1), "赤铁矿", Color.red)
        ironOreItem99 = CompositeItem("iron-ore-99", Seq.with(Fe2O3, 99, SiO2, 1), "赤铁矿", Color.red)

    }
}
