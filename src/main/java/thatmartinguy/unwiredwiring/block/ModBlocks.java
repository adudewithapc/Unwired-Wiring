package thatmartinguy.unwiredwiring.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thatmartinguy.unwiredwiring.tileentity.TileEntityHideable;
import thatmartinguy.unwiredwiring.tileentity.TileEntityWire;

public class ModBlocks
{
	public static BlockWire wireRed;
	
	public static BlockWire wireRedCreative;
	
	public static void init()
	{
		wireRed = new BlockWire("wireRed", "wireRed", Material.CIRCUITS);
		
		wireRedCreative = new BlockWire("wireRedCreative", "wireRedCreative", Material.CIRCUITS, true);
		
		registerBlocks();
		registerTileEntities();
	}
	
	private static void registerBlocks()
	{
		registerBlock(wireRed);
	}
	
	private static void registerTileEntities()
	{
		registerTileEntity(TileEntityHideable.class, "tileEntityHideable");
		registerTileEntity(TileEntityWire.class, "tileEntityWire");
	}
	
	private static void registerBlock(Block block)
	{
		GameRegistry.register(block);
	}
	
	private static void registerTileEntity(Class<? extends TileEntity> tileEntity, String id)
	{
		GameRegistry.registerTileEntity(tileEntity, id);
	}
}
