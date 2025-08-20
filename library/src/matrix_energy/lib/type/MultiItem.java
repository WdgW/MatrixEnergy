package matrix_energy.lib.type;

import arc.graphics.Color;
import arc.struct.Seq;
import matrix_energy.lib.world.meta.MEStat;
import mindustry.type.Item;
import mindustry.type.ItemStack;

/**
 * 多物品
 *
 * @author dg
 */
public class MultiItem extends MEItem {
    public Seq<ItemStack> items;
    public long itemTotal;

    public MultiItem(String name) {
        super(name);
    }

    public MultiItem(String name, Color color) {
        this(name, color, name, null);
    }

    /**
     * 创建多物品
     *
     * @param name
     *         名字
     * @param color
     *         颜色
     * @param items
     *         key: 物品id value: 数量
     */
    public MultiItem(String name, Color color, String localizedName, Seq<ItemStack> items) {
        super(name, color, localizedName);
        this.items = items;
        if (items != null) {
            itemTotal = items.sum(i -> i.amount);
            for (ItemStack entry : items) {
                Item item = entry.item;
                if (item != null) {
                    this.explosiveness += item.explosiveness * entry.amount / itemTotal;
                    this.flammability += item.flammability * entry.amount / itemTotal;
                    this.radioactivity += item.radioactivity * entry.amount / itemTotal;
                    this.charge += item.charge * entry.amount / itemTotal;
                }
            }
        }
    }

    public MultiItem(String name, Color color, String localizedName) {
        this(name, color, localizedName, null);
    }

    @Override
    public void setStats() {
        super.setStats();
        items.forEach(i -> stats.add(MEStat.ingredients, i));
    }
}
