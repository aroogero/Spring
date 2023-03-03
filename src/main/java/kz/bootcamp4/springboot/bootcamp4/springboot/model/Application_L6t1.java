package kz.bootcamp4.springboot.bootcamp4.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_applications")
public class Application_L6t1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_fullName")
    private String fullName;

    @Column(name = "student_course")
    private String course;

    @Column(name = "student_phoneNumber")
    private String phoneNumber;

    @Column(name = "student_comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "apple_handled")
    private boolean handled;

    @Column(name = "student_link")
    private String link;
}
