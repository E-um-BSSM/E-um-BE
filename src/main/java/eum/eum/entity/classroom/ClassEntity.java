package eum.eum.entity.classroom;

import eum.eum.entity.certification.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "classes")
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "class_code")
    private String classCode;
    private String description;
    private String difficulty;

    @Column(name = "access_scope")
    private String accessScope; // public | unlisted | private

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy; // user_id
    private String status; // active | archived

    @Column(name = "created_at")
    private LocalDateTime createdAt =  LocalDateTime.now();

}
