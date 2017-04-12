package thatmartinguy.unwiredwiring.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thatmartinguy.unwiredwiring.tileentity.TileEntityWire;

public class BlockWire extends BlockHideable
{
	private boolean isCreativeWire;
	
	public BlockWire(String unlocalizedName, String registryName, Material material)
	{
		this(unlocalizedName, registryName, material, false);
	}
	
	public BlockWire(String unlocalizedName, String registryName, Material material, boolean isCreativeWire)
	{
		super(unlocalizedName, registryName, material);
		this.isCreativeWire = isCreativeWire;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityWire();
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return isCreativeWire ? 0 : 1;
	}
}
