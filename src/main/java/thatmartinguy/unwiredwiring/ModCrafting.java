package thatmartinguy.unwiredwiring;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thatmartinguy.unwiredwiring.item.ModItems;

public class ModCrafting
{
	public static void init()
	{
		addWrenchRecipe(new ItemStack(ModItems.wrenchRed), "Red");
	}
	
	private static void addWrenchRecipe(ItemStack output, String color)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(output, "II", "RD", "RD", 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'D', "dye" + color));
	}
}
