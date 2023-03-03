package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student_L6t1 {

    private Long id;
    private String fullName;
    private String course;
    private String phoneNumber;
    private String comment;
    private boolean handled;
    private String link;
}
