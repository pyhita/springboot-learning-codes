package com.pyhita.request;

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
public class ReqQueryPerson {

    @Schema(description = "person id", example = "1")
    private Integer personId;
}
