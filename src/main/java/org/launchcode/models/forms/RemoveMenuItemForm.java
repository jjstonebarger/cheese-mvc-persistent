
package org.launchcode.classes.cheesemvc.models.forms;

import org.launchcode.classes.cheesemvc.models.Cheese;
import org.launchcode.classes.cheesemvc.models.Menu;

public class RemoveMenuItemForm {

    private int menuId;

    private int cheeseId;

    private Iterable<Cheese> cheeses;

    private Menu menu;

    public RemoveMenuItemForm(){}

    public RemoveMenuItemForm(Iterable<Cheese> cheeses, Menu menu) {
        this.cheeses = cheeses;
        this.menu = menu;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public Menu getMenu() {
        return menu;
    }
}
