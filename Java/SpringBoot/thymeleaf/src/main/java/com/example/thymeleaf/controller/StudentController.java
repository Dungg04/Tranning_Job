package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.Student;
import com.example.thymeleaf.reponsitories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
@CrossOrigin
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/signup")
    public String showSignupForm(Student student) {
        return "add-student";
    }

    @GetMapping("/list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id")Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id: " + id));
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id")Long id, @Valid Student student, BindingResult result, Model model) {
        if(result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
         studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id")Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid student id " + id));
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return "Index";
    }

}
