package me.toddydev.core.model.categories;

import lombok.*;
import me.toddydev.core.model.icon.Icon;
import me.toddydev.core.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private String id;
    private String name;
    private List<String> description;

    private Material material;
    private int data;

    public ItemStack stack() {
        return new ItemBuilder(material, data).name(name).lore(description).build();
    }
}
