package maria.anikina.springsecurity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@Component
@ToString
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
