package binnie.extrabees.genetics;

import binnie.Binnie;
import binnie.core.IInitializable;
import binnie.extrabees.ExtraBees;
import binnie.extrabees.genetics.effect.BlockEctoplasm;
import binnie.extrabees.genetics.effect.ExtraBeesEffect;
import binnie.extrabees.genetics.items.ItemDictionary;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IGenome;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModuleGenetics implements IInitializable {

	@Override
	@SuppressWarnings("all")
	public void preInit() {
		ExtraBeesBranch.setSpeciesBranches();
		for (final ExtraBeesSpecies species : ExtraBeesSpecies.values()) {
			AlleleManager.alleleRegistry.registerAllele(species);
		}
		ExtraBees.dictionary = new ItemDictionary();
		ExtraBees.proxy.registerItem(ExtraBees.dictionary);
		GameRegistry.register(ExtraBees.ectoplasm = new BlockEctoplasm());
		GameRegistry.register(new ItemBlock(ExtraBees.ectoplasm).setRegistryName(ExtraBees.ectoplasm.getRegistryName()));
	}

	@Override
	public void init() {
		ExtraBeesEffect.doInit();
		ExtraBeesFlowers.doInit();
		ExtraBeesSpecies.doInit();
		ExtraBeeMutation.doInit();
	}

	@Override
	public void postInit() {
		/*int ebSpeciesCount = 0; TODO: Find out what this was for
		int ebTotalSpeciesCount = 0;
		for (final ExtraBeesSpecies species : ExtraBeesSpecies.values()) {
			++ebTotalSpeciesCount;
			if (!AlleleManager.alleleRegistry.isBlacklisted(species.getUID())) {
				++ebSpeciesCount;
			}
		}*/
		RecipeManagers.carpenterManager.addRecipe(100, Binnie.LIQUID.getFluidStack("water", 2000), ItemStack.EMPTY, new ItemStack(ExtraBees.dictionary), "X#X", "YEY", "RDR", '#', Blocks.GLASS_PANE, 'X', Items.GOLD_INGOT, 'Y', "ingotTin", 'R', Items.REDSTONE, 'D', Items.DIAMOND, 'E', Items.EMERALD);
	}

	public static IGenome getGenome(final IAlleleBeeSpecies allele0) {
		return Binnie.GENETICS.getBeeRoot().templateAsGenome(Binnie.GENETICS.getBeeRoot().getTemplate(allele0));
	}

}
