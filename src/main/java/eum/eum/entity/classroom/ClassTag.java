package eum.eum.entity.classroom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "class_tag")
@NoArgsConstructor
@AllArgsConstructor
public class ClassTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_id")
    private Long classId;
    private String content;
}
