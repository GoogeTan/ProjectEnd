package ru.googletan.projectend.world.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import ru.googletan.projectend.block.ModBlocks;

import java.util.Random;

public class WorldGenEndCactus extends WorldGenerator
{

	private final static IBlockState END_MAGMA = ModBlocks.endMagma.getDefaultState();
	private final static IBlockState END_CACTUS = ModBlocks.endCactus.getDefaultState();

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) 
	{
		if(world.getBlockState(pos.down()).getBlock() == ModBlocks.endAcid)
			return false;
		
		int height = 3 + rand.nextInt(5);
		
		world.setBlockState(pos, END_MAGMA);
		for(int y=1; y<=height; y++)
			world.setBlockState(pos.add(0, y, 0), END_CACTUS);
		
		return true;
	}

}
