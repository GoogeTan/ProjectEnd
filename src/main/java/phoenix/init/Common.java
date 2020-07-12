package phoenix.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import phoenix.Phoenix;
import phoenix.title.BlockTileEntity;
import phoenix.world.BiomeRegister;
import phoenix.world.WorldProviderEndBiomes;
import phoenix.world.capablity.IStager;
import phoenix.world.capablity.StageHandler;
import phoenix.world.capablity.StageStorage;
import phoenix.world.structures.Unit01.WorldGenCorn;
import phoenix.world.structures.Unit02.WorldGenHome;

import java.util.ArrayList;

import static phoenix.init.BlocksRegister.JUISER;

public class Common
{
    public void preInit(FMLPreInitializationEvent event)
    {
        FluidRegister.register();
        CapabilityManager.INSTANCE.register(IStager.class, new StageStorage(), StageHandler.class);//reg capablity
    }

    public void init(FMLInitializationEvent event)
    {
        //override end
        DimensionManager.unregisterDimension(1);
        DimensionType endBiomes = DimensionType.register("End", "_end", 1, WorldProviderEndBiomes.class, false);
        DimensionManager.registerDimension(1, endBiomes);
        //override end
        BiomeRegister.registerBiomes();
        GameRegistry.registerWorldGenerator(new WorldGenCorn(), 5);
        GameRegistry.registerWorldGenerator(new WorldGenHome(), 3);
    }

    public void postInit(FMLPostInitializationEvent event) { }
}
