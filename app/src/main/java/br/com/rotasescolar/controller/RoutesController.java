package br.com.rotasescolar.controller;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.vo.RoutesVO;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class RoutesController {

    public void getRoutes(){
//        Implementar chamada
        List<Route> routes = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            Route route = new Route();
            route.setId(i);
            route.setName("Rota " + i);
            routes.add(route);
        }
        RoutesVO vo = new RoutesVO();
        vo.setRoutes(routes);
        EventBus.getDefault().post(vo);
    }
}
