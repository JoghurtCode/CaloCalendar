package de.sep.calocalendar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Goal {

    @Id
    private Long id;

    private float goalWeight;
    private int goalSpeed;
}
