package matrix_energy.lib.content;

import arc.graphics.Color;
import matrix_energy.lib.type.MEItem;
import matrix_energy.lib.type.MultiItem;
import mindustry.content.Items;
import mindustry.type.ItemStack;

/**
 * @author dg
 */

@SuppressWarnings("ALL")
public class MEItems {
    public static MEItem
            coal, iron,lead, copper, gold, leadZinc, nickel, tin, starch, plastic, sand, saltpeter,
            sulfide, blackPowder, carbonFiber, glass, aluminium
            ;

    /**
     * 表示矿石的多物品实例，可用于将多个物品或数量组合在单一物品表示下。
     */
    public static MultiItem ironOreItems;
    @SuppressWarnings("AlibabaAvoidCommentBehindStatement")
    public static void load() {
        coal = new MEItem("coal", new Color(0, 0, 0), "煤", Items.coal);
        copper = new MEItem("copper", new Color(255, 140, 0), "铜",Items.copper);     //Cu
        iron = new MEItem("iron", new Color(192, 192, 192), "铁");                    //Fe
        lead = new MEItem("lead", Color.valueOf("2s4mwjw"), "铅", Items.lead);            //Pb
        gold = new MEItem("gold", new Color(255, 215, 0), "金");                      //Au
        leadZinc = new MEItem("lead-zinc", new Color(165, 165, 165), "铅锌");
        nickel = new MEItem("nickel", new Color(185, 185, 185), "镍");                //Ni
        tin = new MEItem("tin", new Color(220, 220, 220), "锡");                      //Sn
        starch = new MEItem("starch", Color.valueOf("ri31nrz"), "淀粉");
        plastic = new MEItem("plastic", Color.valueOf("5rkz0lw"), "塑钢");
        sand = new MEItem("sand", Color.valueOf("gk9v045"), "沙子",Items.sand);
        saltpeter = new MEItem("saltpeter", Color.valueOf("rovf956"), "火硝");
        sulfide = new MEItem("sulfide", Color.valueOf("oxhg1ut"), "硫化物", Items.pyratite);
        blackPowder = new MEItem("blackPowder", Color.valueOf("7cqb1if"), "黑火药");
        carbonFiber = new MEItem("carbonFiber", Color.valueOf("to9pp4p"), "碳纤维");
        glass = new MEItem("glass", Color.valueOf("d0fx0oi"), "玻璃",Items.metaglass);
        aluminium = new MEItem("aluminium", Color.valueOf("e0e0e0"), "铝");               //Al


        //多物品
        ironOreItems = new MultiItem("iron-ore", Color.red, "赤铁矿", ItemStack.list(iron, 1, aluminium, 1)){
            {
                description = "赤铁矿";
            }
        };
    }
}
