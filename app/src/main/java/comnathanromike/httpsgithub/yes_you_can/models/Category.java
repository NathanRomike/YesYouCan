package comnathanromike.httpsgithub.yes_you_can.models;

import java.util.ArrayList;

public class Category {
    String name;
    int subcategoryCount;
    ArrayList<Subcategory> subcategories;

    public String getName() {
        return name;
    }

    public int getSubcategoryCount() {
        return subcategoryCount;
    }

    public ArrayList<Subcategory> getSubcategory() {
        return subcategories;
    }
}
