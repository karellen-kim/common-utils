package io.github.karellen.utils.collections.models;

/**
 * @author karellen
 */
public class Item {
    public Integer id;
    public String name;
    public Integer categoryId;

    public Item(Integer id, String name, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }
}
