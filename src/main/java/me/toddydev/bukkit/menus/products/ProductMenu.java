package me.toddydev.bukkit.menus.products;

import com.henryfabio.minecraft.inventoryapi.inventory.impl.paged.PagedInventory;
import com.henryfabio.minecraft.inventoryapi.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.item.supplier.InventoryItemSupplier;
import com.henryfabio.minecraft.inventoryapi.viewer.configuration.impl.ViewerConfigurationImpl;
import com.henryfabio.minecraft.inventoryapi.viewer.impl.paged.PagedViewer;
import me.toddydev.core.cache.Caching;
import me.toddydev.core.model.Product;
import me.toddydev.core.model.categories.Category;
import me.toddydev.core.utils.item.ItemBuilder;
import org.bukkit.Material;

import java.util.LinkedList;
import java.util.List;

public class ProductMenu extends PagedInventory {

    private Category category;

    public ProductMenu(Category category) {
        super(
                "products-menu",
                "Loja Virtual - " + category.getId(),
                9*3
        );

        this.category = category;
    }

    @Override
    protected void configureViewer(PagedViewer viewer) {
        ViewerConfigurationImpl.Paged config = viewer.getConfiguration();
        config.itemPageLimit(7);
    }

    @Override
    protected List<InventoryItemSupplier> createPageItems(PagedViewer pagedViewer) {
        List<InventoryItemSupplier> items = new LinkedList<>();

        List<Product> products = Caching.getProductCache().findByCategory(category);

        if (products.isEmpty()) {
            items.add(() -> InventoryItem.of(new ItemBuilder(Material.BARRIER, 0).name("§cNão existem itens disponiveis para compra.").build()));
        }

        for (Product product : products) {
            items.add(() -> InventoryItem.of(product.getIcon().stack()));
        }

        return items;
    }
}
