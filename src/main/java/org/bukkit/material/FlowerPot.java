package org.bukkit.material;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * Represents a flower pot.
 */
public class FlowerPot extends MaterialData {

    /**
     * Default constructor for a flower pot.
     */
    public FlowerPot() {
        super(Material.FLOWER_POT);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public FlowerPot(final int type) {
        super(type);
    }

    public FlowerPot(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public FlowerPot(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public FlowerPot(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Get the material in the flower pot
     *
     * @return material MaterialData for the block currently in the flower pot
     *     or null if empty
     */
    public MaterialData getContents() {
        switch (getData()) {
            case 1:
                return new MaterialData(Material.RED_ROSE);
            case 2:
                return new MaterialData(Material.YELLOW_FLOWER);
            case 3:
                return new Tree(TreeSpecies.GENERIC);
            case 4:
                return new Tree(TreeSpecies.REDWOOD);
            case 5:
                return new Tree(TreeSpecies.BIRCH);
            case 6:
                return new Tree(TreeSpecies.JUNGLE);
            case 7:
                return new MaterialData(Material.RED_MUSHROOM);
            case 8:
                return new MaterialData(Material.BROWN_MUSHROOM);
            case 9:
                return new MaterialData(Material.CACTUS);
            case 10:
                return new MaterialData(Material.DEAD_BUSH);
            case 11:
                return new LongGrass(GrassSpecies.FERN_LIKE);
            default:
                return null;
        }
    }

    /**
     * Set the contents of the flower pot
     *
     * @param materialData MaterialData of the block to put in the flower pot.
     */
    public void setContents(MaterialData materialData) {
        Material mat = materialData.getItemType();

        switch (mat) {
            case RED_ROSE:
                setData((byte) 1);
                break;
            case YELLOW_FLOWER:
                setData((byte) 2);
                break;
            case RED_MUSHROOM:
                setData((byte) 7);
                break;
            case BROWN_MUSHROOM:
                setData((byte) 8);
                break;
            case CACTUS:
                setData((byte) 9);
                break;
            case DEAD_BUSH:
                setData((byte) 10);
                break;
            case SAPLING: {
                TreeSpecies species = ((Tree) materialData).getSpecies();
                switch (species) {
                    case GENERIC:
                        setData((byte) 3);
                        break;
                    case REDWOOD:
                        setData((byte) 4);
                        break;
                    case BIRCH:
                        setData((byte) 5);
                        break;
                    default:
                        setData((byte) 6);
                        break;
                }
                break;
            }
            case LONG_GRASS: {
                GrassSpecies species = ((LongGrass) materialData).getSpecies();
                if (species == GrassSpecies.FERN_LIKE) {
                    setData((byte) 11);
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " containing " + getContents();
    }

    @Override
    public FlowerPot clone() {
        return (FlowerPot) super.clone();
    }
}
