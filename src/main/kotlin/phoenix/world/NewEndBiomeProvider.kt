package phoenix.world

import net.minecraft.util.SharedSeedRandom
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.IExtendedNoiseRandom
import net.minecraft.world.gen.LazyAreaLayerContext
import net.minecraft.world.gen.SimplexNoiseGenerator
import net.minecraft.world.gen.area.IArea
import net.minecraft.world.gen.area.IAreaFactory
import net.minecraft.world.gen.layer.Layer
import net.minecraft.world.gen.layer.ZoomLayer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import phoenix.init.PhoenixBiomes
import phoenix.init.PhoenixConfiguration
import phoenix.world.genlayers.*
import java.util.*
import java.util.function.LongFunction

class EndBiomeProvider(private val lookupRegistry: Registry<Biome>, val seed: Long) : net.minecraft.world.biome.provider.EndBiomeProvider(lookupRegistry, seed)
{
    val generator: SimplexNoiseGenerator
    private lateinit var genBiomes: Layer

    init
    {
        val biomesIn = ArrayList<Biome>()
        biomesIn.addAll(biomes)
        biomesIn.add(PhoenixBiomes.UNDER)
        biomesIn.add(PhoenixBiomes.HEART_VOID)
        this.biomes = biomesIn
        updateLayer()
        INSTANCE = this
    }

    @OnlyIn(Dist.CLIENT)
    override fun getBiomeProvider(seed: Long) = EndBiomeProvider(lookupRegistry, seed)

    override fun getNoiseBiome(x: Int, y: Int, z: Int) : Biome = genBiomes.func_242936_a(lookupRegistry, x, z)

    init
    {
        val rand = SharedSeedRandom(seed)
        rand.skip(17292)
        generator = SimplexNoiseGenerator(rand)
    }

    fun updateLayer()
    {
        genBiomes = Layer(getLayersApply { dopSeed: Long -> LazyAreaLayerContext(25, seed, dopSeed) })
    }

    private fun <T : IArea, C : IExtendedNoiseRandom<T>> getLayersApply(context: LongFunction<C>): IAreaFactory<T>
    {
        var phoenixBiomes = EndBiomeLayer.apply(context.apply(200L), ParentLayer(this).apply(context.apply(1L)))
        val vanilaBiomes  = EndBiomeLayer.apply(context.apply(100L), ParentLayer(this).apply(context.apply(2L)))

        val stage = StageManager.stage

        if (stage >= 1)
        {
            phoenixBiomes = UnderLayer.apply(context.apply(200L), phoenixBiomes)
            phoenixBiomes = HeartVoidLayer.apply(context.apply(200L), phoenixBiomes)
        }

        for (i in 0..PhoenixConfiguration.COMMON_CONFIG.BIOME_SIZE.get() + 7) phoenixBiomes = ZoomLayer.NORMAL.apply(context.apply(200L), phoenixBiomes)

        return UnificationLayer.apply(context.apply(100L), phoenixBiomes, vanilaBiomes)
    }

    companion object
    {
        lateinit var INSTANCE : EndBiomeProvider
    }
}
