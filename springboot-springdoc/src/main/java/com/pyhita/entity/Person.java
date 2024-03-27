package com.pyhita.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: kante_yang
 * @Date: 2023/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Schema(description = "person name", example = "kante")
    private String name;
    @Schema(description = "person age", example = "10")
    private Integer age;

}
