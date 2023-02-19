package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import java.util.ArrayList;

public class DBManager_L3t1 {
    private static final ArrayList<Students_L3t1> students = new ArrayList<>();
    private static Long id = 8L;
    static {
        students.add(Students_L3t1.builder()
                .id(1L)
                .name("ILiyas")
                .surname("Zhuanyshev")
                .exam(88)
                .mark('B')
                .build());
        students.add(Students_L3t1.builder()
                .id(2L)
                .name("Serik")
                .surname("Erikov")
                .exam(91)
                .mark('A')
                .build());
        students.add(Students_L3t1.builder()
                .id(3L)
                .name("Erik")
                .surname("Erikov")
                .exam(65)
                .mark('C')
                .build());
        students.add(Students_L3t1.builder()
                .id(4L)
                .name("Nurzhan")
                .surname("Bolatov")
                .exam(48)
                .mark('F')
                .build());
        students.add(Students_L3t1.builder()
                .id(5L)
                .name("Patrick")
                .surname("Zuckerberg")
                .exam(100)
                .mark('A')
                .build());
        students.add(Students_L3t1.builder()
                .id(6L)
                .name("Sabina")
                .surname("Assetova")
                .exam(33)
                .mark('F')
                .build());
        students.add(Students_L3t1.builder()
                .id(7L)
                .name("Madina")
                .surname("Adletova")
                .exam(77)
                .mark('B')
                .build());
    }
    public static ArrayList<Students_L3t1> getStudents() {
        return students;
    }

    public static void addStudents(Students_L3t1 student) {
        student.setId(id);
        if (student.getExam() > 89) {
            student.setMark('A');
        } else if (student.getExam() < 90 && student.getExam() > 76) {
            student.setMark('B');
        } else if (student.getExam() < 75 && student.getExam() > 59) {
            student.setMark('C');
        } else if (student.getExam() < 60 && student.getExam() > 49) {
            student.setMark('D');
        } else {
            student.setMark('F');
        }
        students.add(student);
        id++;
    }

}

