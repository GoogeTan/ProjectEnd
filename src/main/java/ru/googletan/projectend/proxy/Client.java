package ru.googletan.projectend.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.googletan.projectend.BlocksRegister;
import ru.googletan.projectend.util.ParticleConfEndRodFactory;

public class Client extends Common
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        BlocksRegister.registerRender();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
        Minecraft.getMinecraft().effectRenderer.registerParticle(EnumParticleTypes.END_ROD.getParticleID(), new ParticleConfEndRodFactory());
    }
}