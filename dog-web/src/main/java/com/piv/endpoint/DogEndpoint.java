package com.piv.endpoint;

import com.piv.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DogEndpoint {
    @Autowired
    private DogRepository dogRepository;
}
