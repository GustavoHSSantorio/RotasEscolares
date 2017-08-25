package br.com.rotasescolar.model;

import java.util.List;

/**
 * Created by gustavosantorio on 15/08/17.
 */

public class StudentGroup {
    public String name;
    public List<Student> students;

    public StudentGroup(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }
}