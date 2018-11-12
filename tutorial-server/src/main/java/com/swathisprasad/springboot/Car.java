package com.swathisprasad.springboot;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode(callSuper = false)
public class Car extends ResourceSupport {
    private @NonNull String name;
    @Id
    @GeneratedValue
    private Long customerId;
}
