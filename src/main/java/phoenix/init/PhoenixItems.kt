package phoenix.init

import net.minecraft.item.AxeItem
import net.minecraft.item.Item
import net.minecraft.item.PickaxeItem
import net.minecraft.item.SwordItem
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import phoenix.Phoenix
import phoenix.Phoenix.Companion.PHOENIX
import phoenix.items.ItemDiary
import phoenix.items.ash.CrucibleItem
import phoenix.items.ash.HighQualityClayItem
import phoenix.items.ash.KnifeItem
import java.util.function.Supplier


object PhoenixItems
{
    private val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Phoenix.MOD_ID)

    val GUIDE                          = ITEMS.register("diary") { ItemDiary() }!!

    val HIGH_QUALITY_CLAY_ITEM         = ITEMS.register("high_quality_clay") { HighQualityClayItem() }!!

    val CRUCIBLE                       = ITEMS.register("crucible", basicItem())!!
    val CRUCIBLE_WITH_IRON_ORE         = ITEMS.register("crucible_with_iron_ore") { CrucibleItem() }!!
    val CRUCIBLE_WITH_IRON             = ITEMS.register("crucible_with_iron") { CrucibleItem() }!!
    val CRUCIBLE_WITH_GOLD_ORE         = ITEMS.register("crucible_with_gold_ore") { CrucibleItem() }!!
    val CRUCIBLE_WITH_GOLD             = ITEMS.register("crucible_with_gold") { CrucibleItem() }!!

    val STEEL_INGOT                    = ITEMS.register("steel_ingot", basicItem())!!
    val ZIRCONIUM_INGOT                = ITEMS.register("zirconium_ingot", basicItem())!!

    val ZIRCONIUM_SWORD_BLADE          = ITEMS.register("zirconium_sword_blade", basicItem())!!
    val ZIRCONIUM_KNIFE_BLADE          = ITEMS.register("zirconium_knife_blade", basicItem())!!
    val ZIRCONIUM_BAYONET              = ITEMS.register("zirconium_bayonet", basicItem())!!
    val ZIRCONIUM_BUTT                 = ITEMS.register("zirconium_butt", basicItem())!!

    val STEEL_FORM_KNIFE_BLADE         = ITEMS.register("steel_form_knife_blade", basicItem())!!
    val STEEL_FORM_SWORD_BLADE         = ITEMS.register("steel_form_sword_blade", basicItem())!!
    val STEEl_FORM_BAYONET             = ITEMS.register("steel_form_bayonet", basicItem())!!
    val STEEl_FORM_BUTT                = ITEMS.register("steel_form_butt", basicItem())!!

    val STEEL_FORM_KNIFE_BLADE_FULL    = ITEMS.register("steel_form_knife_blade_full", form(STEEL_FORM_KNIFE_BLADE))!!
    val STEEL_FORM_SWORD_BLADE_FULL    = ITEMS.register("steel_form_sword_blade_full", form(STEEL_FORM_SWORD_BLADE))!!
    val STEEl_FORM_BAYONET_FULL        = ITEMS.register("steel_form_bayonet_full", form(STEEl_FORM_BAYONET))!!
    val STEEl_FORM_BUTT_FULL           = ITEMS.register("steel_form_butt_full", form(STEEl_FORM_BUTT))!!

    val STEEL_FORM_KNIFE_BLADE_ROASTED = ITEMS.register("steel_form_knife_blade_roasted", form(STEEL_FORM_KNIFE_BLADE))!!
    val STEEL_FORM_SWORD_BLADE_ROASTED = ITEMS.register("steel_form_sword_blade_roasted", form(STEEL_FORM_SWORD_BLADE))!!
    val STEEl_FORM_BAYONET_ROASTED     = ITEMS.register("steel_form_bayonet_roasted", form(STEEl_FORM_BAYONET))!!
    val STEEl_FORM_BUTT_ROASTED        = ITEMS.register("steel_form_butt_roasted", form(STEEl_FORM_BUTT))!!

    val ZIRCONIUM_AXE                  = ITEMS.register<Item>("ceramic_zirconium_axe") { AxeItem(PhoenixTiers.ZIRCONIUM_TIER, 9.0f, 1.1f, Item.Properties().group(PHOENIX)) }!!
    val ZIRCONIUM_PICKAXE              = ITEMS.register<Item>("ceramic_zirconium_pickaxe") { PickaxeItem(PhoenixTiers.ZIRCONIUM_TIER, 4, 0.5f, Item.Properties().group(PHOENIX)) }!!
    val ZIRCONIUM_SWORD                = ITEMS.register<Item>("ceramic_zirconium_sword") { SwordItem(PhoenixTiers.ZIRCONIUM_TIER, 3, 1f, Item.Properties().group(PHOENIX)) }!!
    val ZIRCONIUM_KNIFE                = ITEMS.register("ceramic_zirconium_knife") { KnifeItem(PhoenixTiers.ZIRCONIUM_TIER, 3f, 1f, PhoenixConfiguration.COMMON_CONFIG.HARDCORE.get().maxKnifeUsages) }!!

    fun register() = ITEMS.register(FMLJavaModLoadingContext.get().modEventBus)

    private fun basicItem() =  Supplier { Item(Item.Properties().group(PHOENIX)) }
    private fun form(contains: RegistryObject<Item>) = Supplier { Item(Item.Properties().group(PHOENIX).containerItem(contains.get())) }
}