package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Students_L3t1 {
    private Long id;
    private String name;
    private String surname;
    private int exam;
    private char mark;
}
