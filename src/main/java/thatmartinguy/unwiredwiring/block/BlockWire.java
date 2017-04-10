package thatmartinguy.unwiredwiring.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import thatmartinguy.unwiredwiring.client.HideManager;
import thatmartinguy.unwiredwiring.item.ModItems;
import thatmartinguy.unwiredwiring.tileentity.TileEntityWire;
import thatmartinguy.unwiredwiring.util.Reference;

public class BlockWire extends Block implements ITileEntityProvider
{	
	//Should the wire be hidden?
	public static final IProperty<Boolean> HIDDEN = PropertyBool.create("hidden");
	
	private boolean isCreativeWire;
	
	public BlockWire(String unlocalizedName, String registryName, Material material)
	{
		this(unlocalizedName, registryName, material, false);
	}
	
	public BlockWire(String unlocalizedName, String registryName, Material material, boolean isCreativeWire)
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setDefaultState(getBlockState().getBaseState().withProperty(HIDDEN, false));
		this.isBlockContainer = true;
		this.setHardness(0.5f);
		this.isCreativeWire = isCreativeWire;
	}
	
	@Override
	public Block setUnlocalizedName(String name)
	{
		return super.setUnlocalizedName(Reference.MOD_ID + ":" + name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityWire(worldIn);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}
	
	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
	{
		super.eventReceived(state, worldIn, pos, id, param);
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		return tileEntity == null ? false : tileEntity.receiveClientEvent(id, param);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer.Builder(this).add(HIDDEN).build();
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		if(FMLCommonHandler.instance().getSide().isClient())
		{
			state = state.withProperty(HIDDEN, HideManager.shouldWireBeRevealed());
		}
		return state;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		IBlockState blockState = this.getDefaultState().withProperty(HIDDEN, true);
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		if(FMLCommonHandler.instance().getSide().isClient())
		{
			return state.getValue(HIDDEN) ? EnumBlockRenderType.INVISIBLE : EnumBlockRenderType.MODEL;
		}
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
	{
		return state.getValue(HIDDEN) ? new AxisAlignedBB(0, 0, 0, 0, 0, 0) : FULL_BLOCK_AABB;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return state.getValue(HIDDEN) ? new AxisAlignedBB(0, 0, 0, 0, 0, 0) : FULL_BLOCK_AABB;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.REDSTONE;
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return this.isCreativeWire ? 0 : 1;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullyOpaque(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	//==========================================================REDSTONE RELATED CODE=========================================================//
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		if(blockAccess.getTileEntity(pos) instanceof TileEntityWire)
		{
			TileEntityWire tileEntityWire = (TileEntityWire) blockAccess.getTileEntity(pos);
			return tileEntityWire.getPowerLevel();
		}
		return 0;
	}
	
	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return blockState.getWeakPower(blockAccess, pos, side);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		int powerLevel = worldIn.isBlockIndirectlyGettingPowered(pos);
		
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof TileEntityWire)
		{
			TileEntityWire tileEntityWire = (TileEntityWire) worldIn.getTileEntity(pos);
			tileEntityWire.setPowerLevel(powerLevel);
			worldIn.notifyNeighborsOfStateChange(pos, this, false);
		}
	}
	
	@Override
	public boolean canProvidePower(IBlockState state)
	{
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(playerIn.getHeldItem(hand).getItem() != null && playerIn.getHeldItem(hand).getItem() != ModItems.wrenchRedCreative && worldIn.getTileEntity(pos) instanceof TileEntityWire)
			System.out.println(((TileEntityWire) worldIn.getTileEntity(pos)).getPowerLevel());
		return true;
	}
}
