package br.com.rotasescolar.model;

import java.io.Serializable;

/**
 * Created by gustavosantorio on 12/07/17.
 */

public class Route implements Serializable {

    private int id;
    private String name;
    private boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
