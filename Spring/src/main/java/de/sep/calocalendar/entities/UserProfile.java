package de.sep.calocalendar.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private int gender;
    private int age;
    private float weight;
    private int levelOfPhysicalActivity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_id", referencedColumnName = "id")
    private Goal goal;
}
