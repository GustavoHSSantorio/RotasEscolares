package br.com.rotasescolar.controller;

import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.RoutesVO;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 10/07/17.
 */

public class MapsController {

    public void getLatLongListByRouteId(int routeId){
//        implementar chamada
        List<Student> students = new ArrayList<>();
        if(routeId == 1){
            Student student = new Student();
            student.setLat(-35);
            student.setLng(135);
            students.add(student);
            student = new Student();
            student.setLat(-35);
            student.setLng(136);
            students.add(student);
            student = new Student();
            student.setLat(-35);
            student.setLng(137);
            students.add(student);
        }else{
            Student student = new Student();
            student.setLat(-35);
            student.setLng(121);
            students.add(student);
            student = new Student();
            student.setLat(-35);
            student.setLng(122);
            students.add(student);
            student = new Student();
            student.setLat(-35);
            student.setLng(123);
            students.add(student);
        }
        StudentVO vo = new StudentVO();
        vo.setStudents(students);
        EventBus.getDefault().post(vo);
    }

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
