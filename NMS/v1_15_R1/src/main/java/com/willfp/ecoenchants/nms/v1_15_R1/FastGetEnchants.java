package com.willfp.ecoenchants.nms.v1_15_R1;

import com.willfp.ecoenchants.nms.api.FastGetEnchantsWrapper;
import net.minecraft.server.v1_15_R1.NBTBase;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagList;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_15_R1.util.CraftNamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class FastGetEnchants implements FastGetEnchantsWrapper {
    @Override
    public Map<Enchantment, Integer> getEnchantmentsOnItem(ItemStack itemStack) {
        net.minecraft.server.v1_15_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagList enchantmentNBT = nmsStack.getEnchantments();
        HashMap<Enchantment, Integer> foundEnchantments = new HashMap<>();

        for (NBTBase base : enchantmentNBT) {
            NBTTagCompound compound = (NBTTagCompound) base;
            String key = compound.getString("id");
            int level = '\uffff' & compound.getShort("lvl");

            Enchantment found = Enchantment.getByKey(CraftNamespacedKey.fromStringOrNull(key));
            if(found != null) foundEnchantments.put(found, level);
        }
        return foundEnchantments;
    }
}
