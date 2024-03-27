package com.pyhita.controller;

import com.pyhita.entity.Person;
import com.pyhita.request.ReqQueryPerson;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2023/12/26
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {


    @GetMapping("/listPersons")
    public List<Person> listPersons() {

        return null;
    }

    @PostMapping("/queryPersonById")
    public Person personById(@ParameterObject ReqQueryPerson queryPerson) {
        return null;
    }


}
