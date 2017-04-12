package thatmartinguy.unwiredwiring.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import thatmartinguy.unwiredwiring.block.ModBlocks;
import thatmartinguy.unwiredwiring.item.ModItems;

public class ModModelManager
{
	public static void registerAllModels()
	{
		registerItemModels();
		//registerBlockModels();
	}
	
	private static void registerItemModels()
	{
		registerItemModel(ModItems.wrenchRed);
	}
	
	private static void registerBlockModels()
	{
		registerBlockModel(ModBlocks.wireRed);
	}
	
	private static void registerItemModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void registerBlockModel(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
	}
}
