package phoenix.utils.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public abstract class BlockWithTile extends Block
{
	public BlockWithTile(Block.Properties properties) { super(properties);	}
	@Override public boolean hasTileEntity(BlockState state){ return true;	}
	@Override public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);
}