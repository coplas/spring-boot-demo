package com.example.controllers;

import com.example.model.DummyModel;
import com.example.repository.DummyModelRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {


    @Autowired
    DummyModelRepository repository;

    @RequestMapping(value = "/")
    String index(Model model,
                 @QuerydslPredicate(root = DummyModel.class) Predicate predicate,
                 @PageableDefault(sort = { "lastName", "firstName" }) Pageable pageable) {

        model.addAttribute("users", repository.findAll(predicate, pageable));

        return "table";
    }
}
