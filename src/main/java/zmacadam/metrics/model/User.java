package zmacadam.metrics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "metrics")
public class User {

    @Id
    @Column(name = "user_id")
    private String phoneNumber;
    @Column(name = "user_name")
    @Length(min = 5, message = "Your username must have at least 5 characters")
    @NotEmpty(message = "Please provide a username")
    private String userName;
    @Column(name = "password")
    @Length(min = 5, message = "Your username must have at least 5 characters")
    @NotEmpty(message = "Please provide a password")
    private String password;
    @Column(name = "first_name")
    @NotEmpty(message = "Please provide your first name")
    private String first;
    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your last name")
    private String last;
    @Column(name = "age")
    @NotEmpty(message = "Please provide your age")
    private int age;
    @Column(name = "gender")
    @NotEmpty(message = "Please provide your gender")
    private String gender;
    @Column(name = "weight")
    @NotEmpty(message = "Please provide your weight")
    private int weight;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
