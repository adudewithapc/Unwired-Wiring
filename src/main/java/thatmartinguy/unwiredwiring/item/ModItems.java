package thatmartinguy.unwiredwiring.item;

import baubles.api.IBauble;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import thatmartinguy.unwiredwiring.UnwiredWiring;
import thatmartinguy.unwiredwiring.block.ModBlocks;

public class ModItems
{
	//Normal wrenches
	public static ItemWrench wrenchRed;
	
	//Creative wrenches
	public static ItemWrench wrenchRedCreative;
	
	//Other
	public static ItemBase itemRedstoneLens;
	
	public static Item itemRedstoneGlasses;
	
	//EnumArmorMaterial without stats
	public static ArmorMaterial clothing;
	
	public static void init()
	{
		clothing = EnumHelper.addArmorMaterial("clothing", "", Integer.MAX_VALUE, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		
		
		
		
		wrenchRed = new ItemWrench("wrenchRed", "wrenchRed", ModBlocks.wireRed);
		
		wrenchRedCreative = new ItemCreativeWrench("wrenchRedCreative", "wrenchRedCreative", ModBlocks.wireRedCreative);
		
		itemRedstoneLens = new ItemRedstoneLens("itemRedstoneLens", "ItemRedstoneLens");
		
		itemRedstoneGlasses = new ItemRedstoneGlasses("itemRedstoneGlasses", "ItemRedstoneGlasses");
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(wrenchRed);
		
		registerItem(wrenchRedCreative);
		
		registerItem(itemRedstoneLens);
		
		registerItem(itemRedstoneGlasses);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.register(item);
	}
}
