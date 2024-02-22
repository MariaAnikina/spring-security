package maria.anikina.springsecurity.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "news")
@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "У новости должно быть название")
	private String name;
	private String description;
	private LocalDate created = LocalDate.now();
}
