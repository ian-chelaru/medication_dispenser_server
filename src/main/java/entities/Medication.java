package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "medication")
public class Medication
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "side_effects")
    private String sideEffects;

    @OneToMany(mappedBy = "medication")
    private Set<Prescription> prescriptionSet;
}
