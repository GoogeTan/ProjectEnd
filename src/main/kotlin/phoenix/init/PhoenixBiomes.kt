package phoenix.init

import net.minecraft.util.RegistryKey
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.WorldGenRegistries
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeMaker
import net.minecraft.world.biome.BiomeRegistry
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.registries.ForgeRegistries
import phoenix.Phoenix
import phoenix.utils.ResourceUtils
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object PhoenixBiomes
{
    val UNDER_KEY = makeKey("under")
    val HEARTVOID_KEY = makeKey("heart_void")

    private fun makeKey(name: String): RegistryKey<Biome>
    {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, ResourceUtils.key(name))
    }

    fun addBiomeTypes()
    {
        BiomeDictionary.addTypes(UNDER_KEY, BiomeDictionary.Type.END)
        BiomeDictionary.addTypes(HEARTVOID_KEY, BiomeDictionary.Type.END)
    }

    val BIOMES = KDeferredRegister(ForgeRegistries.BIOMES, Phoenix.MOD_ID)

    val UNDER by BIOMES.register("under") { BiomeMaker.makeEndBarrensBiome() }
}

private fun register(id: Int, key: RegistryKey<Biome>, biome: Biome): Biome
{
    return WorldGenRegistries.register(WorldGenRegistries.BIOME, id, key, biome)
}