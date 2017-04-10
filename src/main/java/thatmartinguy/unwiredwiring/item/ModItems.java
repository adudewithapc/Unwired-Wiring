package thatmartinguy.unwiredwiring.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thatmartinguy.unwiredwiring.block.ModBlocks;

public class ModItems
{
	//Normal wrenches
	public static ItemWrench wrenchRed;
	
	//Creative wrenches
	public static ItemWrench wrenchRedCreative;
	
	public static void init()
	{
		wrenchRed = new ItemWrench("wrenchRed", "wrenchRed", ModBlocks.wireRed);
		
		wrenchRedCreative = new ItemCreativeWrench("wrenchRedCreative", "wrenchRedCreative", ModBlocks.wireRedCreative);
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(wrenchRed);
		
		registerItem(wrenchRedCreative);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.register(item);
	}
}
