package com.example.menu.test;


import com.example.menu.object.Person;
import com.example.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AppController {

  @Autowired
  MenuService menuService;

  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("hello", "Hello World!!!");
    return "test/hello"; // 要導入的html
  }

  @GetMapping("/person")
  public String showGender(Model model) {
    model.addAttribute("gender", "female");
    return "test/person";
  }

  @GetMapping("/list")
  public String listNumber(Model model) {
    List<String> list = new ArrayList<>();
    for (int i = 0; i <= 10; i++) {
      list.add("This is ArrayList" + i);
    }
    model.addAttribute("listaaa", list);
    return "test/list";
  }

  @GetMapping("/form")
  public String form(Model model) {
    Person person = new Person(); // 將Person 實體化
    model.addAttribute("person", person);
    return "test/form"; // 導至form.html
  }

  @PostMapping("/add")
  public String add(@ModelAttribute Person person111, Model model) {
    model.addAttribute("person111", person111);
    return "test/add";
  }
}
