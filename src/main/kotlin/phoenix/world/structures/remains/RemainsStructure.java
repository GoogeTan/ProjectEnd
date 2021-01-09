package phoenix.world.structures.remains;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RemainsStructure extends Structure<NoFeatureConfig>
{
    public RemainsStructure()
    {
        super(NoFeatureConfig.field_236558_a_);
    }

    @Override
    public String getStructureName()
    {
        return "Remains";
    }


    @Override
    public IStartFactory getStartFactory()
    {
        return Start::new;
    }


    public static class Start extends StructureStart
    {
        public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox mbb, int references, long seed)
        {
            super(structure, chunkPosX, chunkPosZ, mbb, references, seed);
        }

        @Override
        public void func_230364_a_(DynamicRegistries p_230364_1_, ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biome, IFeatureConfig config)
        {
            int i = chunkX * 16;
            int j = chunkZ * 16;

            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            RemainsPieces.init(generator, templateManagerIn, blockpos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}