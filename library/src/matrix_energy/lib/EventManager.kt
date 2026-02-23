package matrix_energy.lib

import arc.func.Cons
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.getOrPut


object EventManager {


    private val events: MutableMap<Any, CopyOnWriteArrayList<Cons<*>>> = ConcurrentHashMap()

    fun <T: Any> on(event: T, listener: Cons<T>) {
        events.getOrPut(event) { CopyOnWriteArrayList() }.add(listener)
    }

    fun <T: Any> remove(event: T, listener: Cons<T>): Boolean {
        val listenerList = events[event] ?: return false
        val removed = listenerList.remove(listener)
        // 如果一个事件类型的最后一个监听器被移除，则清理 map。
            if (listenerList.isEmpty()) {
                events.remove(event)
            }
            return removed
        }

        fun  fire(event: Any) {
            val listenerList = events[event] ?: return

            // 遍历 CopyOnWriteArrayList 是安全的，即使另一个线程同时对其进行修改也是如此。
            for (listener in listenerList) {
                try {
                    @Suppress("UNCHECKED_CAST")
                    (listener as Cons<Any>).get(event)
                } catch (t: Throwable) {
                    // 记录监听器执行时发生的错误，但继续通知其他监听器。
                    System.err.println("在执行 $event 事件的监听器时发生错误: ${t}")
                    t.printStackTrace()
                }

            }
        }

        /**
         * 清除所有事件类型的所有已注册监听器。
         */
        fun clear() {
            events.clear()
        }
    }
