package matrix_energy.lib.content;

import mindustry.type.UnitType;

/**
 * @author dg
 */
public class MEUnitTypes {
    public static UnitType zmx;

    public static void load() {
//        zmx = new UnitType("zmx") {{
//            constructor = UnitEntity::create;
//            weapons.add(new Weapon("fork") {{
//                top = true;
//                reload = 10;
//                shootSound = Sounds.none;
//                bullet = new BasicBulletType(3.35f, 17f, "matrixenergy-lib-fork") {
//                    {
//
//                        width = 48;
//                        height = 48;
//                        hitSize = 8;
//                        lifetime = 16f;
//                        pierce = true;
//                        collidesAir = false;
//                        hittable = false;
//                        hitSound = Sounds.none;
//                        hitEffect = Fx.none;
//                        despawnEffect = Fx.none;
//
//
//                        fragBullet = new BasicBulletType(bullet.speed, bullet.damage, "matrixenergy-lib-fork-re") {
//                            public boolean trackShooter = true;
//
//                            {
//                                width = 48;
//                                height = 48;
//                                hitSize = bullet.hitSize;
//                                lifetime = 14f;
//                                pierce = true;
//                                collidesAir = false;
//                                hittable = false;
//                                hitSound = Sounds.none;
//                                hitEffect = Fx.none;
//                                despawnEffect = Fx.none;
//                            }
//
//                            @Override
//                            public void update(Bullet b) {
//                                super.update(b);
//
//                                // 如果启用了追踪发射者，并且发射者存在且是单位
//                                if (trackShooter && b.owner instanceof Unit) {
//                                    Unit shooter = (Unit) b.owner;
//                                    if (shooter.isValid()) {
//                                        // 计算指向发射者的角度
//                                        float targetAngle = b.angleTo(shooter);
//                                        // 逐渐调整子弹方向朝向发射者
//                                        b.vel.setAngle(Angles.moveToward(b.rotation(), targetAngle, 0.2f * Time.delta * 50f));
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void draw(Bullet b) {
//                                // 调用父类绘制方法
//                                super.draw(b);
//                                drawStable(b);
//                            }
//
//                            public void drawStable(Bullet b) {
//                                // 重置父类的绘制逻辑，使用固定大小绘制
//                                float height = this.height;
//                                float width = this.width;
//                                float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360f) + b.time * spin : 0f) + rotationOffset;
//
//                                Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());
//
//                                Draw.mixcol(mix, mix.a);
//
//                                if (backRegion.found()) {
//                                    Draw.color(backColor);
//                                    Draw.rect(backRegion, b.x, b.y, width, height, b.rotation() + offset);
//                                }
//
//                                Draw.color(frontColor);
//                                Draw.rect(frontRegion, b.x, b.y, width, height, b.rotation() + offset);
//
//                                Draw.reset();
//                            }
//
//
//                        };
//                        // 让子弹原路返回的关键设置
//                        fragRandomSpread = 0f;  // 无随机散布
//                        fragAngle = 180f;       // 角度偏移180度（即反方向）
//                        fragSpread = 0f;        // 无散布
//                        fragBullets = 1;        // 只生成一个子弹
//                        fragVelocityMin = 1f;   // 速度与原来相同
//                        fragVelocityMax = 1f;
//                    }
//
//                    @Override
//                    public void createFrags(Bullet b, float x, float y) {
//                        if (fragBullet != null && (fragOnAbsorb || !b.absorbed) && !(b.frags >= pierceFragCap && pierceFragCap > 0)) {
//                            for (int i = 0; i < fragBullets; i++) {
//                                float len = Mathf.random(fragOffsetMin, fragOffsetMax);
//                                float a = b.rotation() + Mathf.range(fragRandomSpread / 2) + fragAngle + fragSpread * i - (fragBullets - 1) * fragSpread / 2f;
//
//                                // 创建子弹时传递发射者信息
//                                Bullet frag = fragBullet.create(b, x + Angles.trnsx(a, len), y + Angles.trnsy(a, len), a, Mathf.random(fragVelocityMin, fragVelocityMax), Mathf.random(fragLifeMin, fragLifeMax));
//
//                                // 如果fragBullet支持追踪发射者，则设置相关信息
//                                if (frag != null) {
//                                    // 发射者信息已经通过b.owner传递，无需额外操作
//
//                                }
//                            }
//                            b.frags++;
//                        }
//                    }
//
//                    @Override
//                    public void draw(Bullet b) {
//                        // 调用父类绘制方法
//                        super.draw(b);
//                        drawStable(b);
//                    }
//
//                    public void drawStable(Bullet b) {
//                        // 重置父类的绘制逻辑，使用固定大小绘制
//                        float height = this.height;
//                        float width = this.width;
//                        float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360f) + b.time * spin : 0f) + rotationOffset;
//
//                        Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());
//
//                        Draw.mixcol(mix, mix.a);
//
//                        if (backRegion.found()) {
//                            Draw.color(backColor);
//                            Draw.rect(backRegion, b.x, b.y, width, height, b.rotation() + offset);
//                        }
//
//                        Draw.color(frontColor);
//                        Draw.rect(frontRegion, b.x, b.y, width, height, b.rotation() + offset);
//
//                        Draw.reset();
//                    }
//                };
//                range = 120;
//            }});
//
//        }};
    }
}
