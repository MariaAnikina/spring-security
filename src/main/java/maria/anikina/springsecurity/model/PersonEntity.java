package maria.anikina.springsecurity.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	@ToString.Exclude
	private String password;
	private String fullName;
	private int salary;
	private String role;
}
