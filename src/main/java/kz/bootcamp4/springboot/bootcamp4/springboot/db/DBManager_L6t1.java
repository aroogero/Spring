package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import java.util.ArrayList;

public class DBManager_L6t1 {
    private static final ArrayList<Courses_L6t1> courses = new ArrayList<>();
    static {
        courses.add(new Courses_L6t1(1,"Java Standard Edition"));
        courses.add(new Courses_L6t1(2,"Web Technologies"));
        courses.add(new Courses_L6t1(3,"Python Programming Language"));
        courses.add(new Courses_L6t1(4,"Java Pro"));
        courses.add(new Courses_L6t1(5,"UX/UI Design"));
        courses.add(new Courses_L6t1(6,"Django Framework"));
    }

    public static ArrayList<Courses_L6t1> getCourses() {
        return courses;
    }

}
