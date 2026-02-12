package matrix_energy.lib.ui.dialogs

import arc.graphics.Color
import arc.scene.event.ClickListener
import arc.scene.event.InputEvent
import arc.scene.style.TextureRegionDrawable
import arc.scene.ui.ScrollPane
import arc.scene.ui.layout.Table
import arc.util.Align
import mindustry.gen.Icon
import mindustry.gen.Tex
import mindustry.graphics.Pal
import mindustry.ui.Fonts
import mindustry.ui.Styles
import mindustry.ui.dialogs.BaseDialog

class AchievementDialog : BaseDialog("@achievements") {

    // 成就数据模型
    data class Achievement(
        val id: String,
        val name: String,
        val description: String,
        val unlocked: Boolean,
        val icon: TextureRegionDrawable? = null
    )

    // 模拟成就数据
    private val achievements = listOf(
        Achievement("first_blood", "First Blood", "Destroy your first enemy unit.", true),
        Achievement("wave_50", "Survivor", "Survive 50 waves.", true),
        Achievement("boss_slayer", "Boss Slayer", "Defeat a boss enemy.", false),
        Achievement("producer", "Producer", "Produce 10,000 resources.", false),
        Achievement("architect", "Architect", "Build 500 blocks.", true),
        Achievement("researcher", "Researcher", "Research all technologies.", false),
        Achievement("explorer", "Explorer", "Discover all sectors.", false),
        Achievement("efficiency", "Efficiency", "Complete a sector with 100% efficiency.", false),
        Achievement("defender", "Defender", "Defend your core for 10 waves without damage.", false),
        Achievement("builder", "Builder", "Build 1000 blocks in a single game.", false)
    )

    // 当前筛选状态
    private var showUnlockedOnly = false
    private var searchQuery = ""

    init {
        setupUI()
    }

    /**
     * 设置UI布局
     */
    private fun setupUI() {
        // 设置对话框基本属性
        addCloseButton()
        setFillParent(true)

        // 主内容区域
        cont.table { mainTable ->
            // 标题栏
            mainTable.table { titleTable ->
                titleTable.add("@achievements").growX().center().pad(10f).get().style.font = Fonts.tech


                // 关闭按钮
                titleTable.button(Icon.cancel, Styles.cleari) {
                    hide()
                }.size(40f).padRight(5f)
            }.growX().row()

            // 分割线
            mainTable.image(Tex.whiteui).growX().height(3f).color(Color.darkGray).pad(5f).row()

            // 搜索和筛选栏
            mainTable.table { filterTable ->
                // 搜索框
                filterTable.image(Icon.zoom).padRight(8f)
                val searchField = filterTable.field("", { text ->
                    searchQuery = text.lowercase()
                    rebuildAchievementList()
                }).growX().get()
                searchField.setMessageText("@search")

                // 筛选按钮
                filterTable.button(Icon.lockOpen, Styles.clearTogglei) {
                    showUnlockedOnly = !showUnlockedOnly
                    rebuildAchievementList()
                }.size(40f).padLeft(10f).tooltip("@achievements.filter.unlocked")
            }.growX().padBottom(10f).row()

            // 成就列表
            val listTable = Table()
            rebuildAchievementList(listTable)

            // 滚动面板
            val scrollPane = ScrollPane(listTable, Styles.defaultPane)
            scrollPane.setFadeScrollBars(false)

            mainTable.add(scrollPane).grow()
        }.grow()
    }

    /**
     * 重建成就列表
     */
    private fun rebuildAchievementList(listTable: Table = cont.find<Table>("achievementList")) {
        listTable.clear()
        listTable.name = "achievementList"

        // 筛选成就
        val filteredAchievements = achievements.filter { achievement ->
            val matchesSearch = achievement.name.lowercase().contains(searchQuery) ||
                    achievement.description.lowercase().contains(searchQuery)
            val matchesFilter = !showUnlockedOnly || achievement.unlocked
            matchesSearch && matchesFilter
        }

        // 添加成就项
        filteredAchievements.forEach { achievement ->
            addAchievementItem(listTable, achievement)
            listTable.image(Tex.whiteui).growX().height(1f).color(Color.darkGray).pad(5f).row()
        }

        // 如果没有匹配的成就，显示提示
        if (filteredAchievements.isEmpty()) {
            listTable.add("@achievements.empty").pad(20f).center()
        }
    }

    /**
     * 添加单个成就项
     */
    private fun addAchievementItem(table: Table, achievement: Achievement) {
        table.table { itemTable ->
            // 成就图标
            val iconColor = if (achievement.unlocked) Pal.accent else Color.gray
            itemTable.image(achievement.icon ?: Icon.lock, iconColor).size(40f).padRight(10f)

            // 成就信息
            itemTable.table { infoTable ->
                // 成就名称
                val nameColor = if (achievement.unlocked) Color.white else Color.lightGray
                infoTable.add(achievement.name).color(nameColor).left().growX().row()

                // 成就描述
                val descColor = if (achievement.unlocked) Pal.lightishGray else Color.gray
                infoTable.add(achievement.description).color(descColor).left().growX().wrap().get()
                    .setAlignment(Align.left, Align.left)
            }.growX()

            // 成就状态指示器
            itemTable.table { statusTable ->
                if (achievement.unlocked) {
                    statusTable.image(Icon.ok, Pal.accent).size(30f)
                } else {
                    statusTable.image(Icon.lock, Color.gray).size(30f)
                }
            }.right()
        }.growX().pad(5f).margin(5f).get().addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                showAchievementDetails(achievement)
            }
        })
    }

    /**
     * 显示成就详情
     */
    private fun showAchievementDetails(achievement: Achievement) {
        val detailDialog = BaseDialog(achievement.name)

        detailDialog.cont.table { detailTable ->
            // 成就图标
            val iconColor = if (achievement.unlocked) Pal.accent else Color.gray
            detailTable.image(achievement.icon ?: Icon.lock, iconColor).size(80f).padRight(20f)

            // 成就信息
            detailTable.table { infoTable ->
                // 成就名称
                val nameColor = if (achievement.unlocked) Color.white else Color.lightGray
                infoTable.add(achievement.name).color(nameColor).left().growX().row()

                // 成就描述
                val descColor = if (achievement.unlocked) Pal.lightishGray else Color.gray
                infoTable.add(achievement.description).color(descColor).left().growX().wrap().get()
                    .setAlignment(Align.left, Align.left)

                // 成就状态
                infoTable.row()
                val statusText = if (achievement.unlocked) "@achievements.unlocked" else "@achievements.locked"
                val statusColor = if (achievement.unlocked) Pal.accent else Color.gray
                infoTable.add(statusText).color(statusColor).left()
            }.growX()
        }.growX().pad(10f).row()

        // 分割线
        detailDialog.cont.image(Tex.whiteui).growX().height(3f).color(Color.darkGray).pad(5f).row()

        // 成就奖励（示例）
        detailDialog.cont.table { rewardTable ->
            rewardTable.add("@achievements.rewards").color(Pal.lightishGray).left().row()
            rewardTable.add("XP: 100").color(Color.white).left().row()
            rewardTable.add("Copper: 500").color(Color.white).left().row()
        }.growX().pad(10f).left().row()

        // 关闭按钮
        detailDialog.buttons.button("@ok", detailDialog::hide).size(200f, 50f).pad(10f)

        detailDialog.show()
    }
}
