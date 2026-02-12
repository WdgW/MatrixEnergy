package matrix_energy.lib.data.serialization

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.util.Pool

object KryoPool {
    lateinit var kryoPool: Pool<Kryo>

    @JvmStatic
    fun load() {
        kryoPool = object : Pool<Kryo>(true, false, 4) {
            override fun create(): Kryo {
                return Kryo().apply {
                    // add kryo config
                }
            }
        }
    }
}

