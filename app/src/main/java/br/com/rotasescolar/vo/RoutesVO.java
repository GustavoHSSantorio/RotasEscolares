package br.com.rotasescolar.vo;

import java.io.Serializable;
import java.util.List;

import br.com.rotasescolar.model.Route;

/**
 * Created by gustavosantorio on 19/07/17.
 */

public class RoutesVO implements Serializable {

    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
