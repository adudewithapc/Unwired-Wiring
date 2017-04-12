package thatmartinguy.unwiredwiring;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thatmartinguy.unwiredwiring.item.ModItems;
import thatmartinguy.unwiredwiring.item.baubles.ModBaubleItems;

public class ModCrafting
{
	public static void init()
	{
		addWrenchRecipe(new ItemStack(ModItems.wrenchRed), "Red");
		
		GameRegistry.addRecipe(new ItemStack(ModItems.itemRedstoneLens), " R ", "RGR", " R ", 'R', Items.REDSTONE, 'G', Blocks.GLASS_PANE);
		
		if(UnwiredWiring.isBaublesLoaded)
		{
			GameRegistry.addRecipe(new ItemStack(ModBaubleItems.baubleRedstoneGlasses), "LGL", 'L', ModItems.itemRedstoneLens, 'G', Items.GOLD_INGOT);
			GameRegistry.addShapelessRecipe(new ItemStack(ModBaubleItems.baubleRedstoneGlasses), ModItems.itemRedstoneGlasses);
		}
		else
		{
			GameRegistry.addRecipe(new ItemStack(ModItems.itemRedstoneGlasses), "LGL", 'L', ModItems.itemRedstoneLens, 'G', Items.GOLD_INGOT);
		}
	}
	
	private static void addWrenchRecipe(ItemStack output, String color)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(output, "II", "RD", "RD", 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'D', "dye" + color));
	}
}
