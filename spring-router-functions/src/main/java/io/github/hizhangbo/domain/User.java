package io.github.hizhangbo.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @author Bob
 * @date 2020-02-02 22:09
 */
@Data
@Document("user")
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 10,max = 100)
    private int age;
}
