package kursovaya20.kursovaya20.controller;

import kursovaya20.kursovaya20.ExaminerService;
import kursovaya20.kursovaya20.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/getQuestions")
    public List<Question> getQuestions(@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }
}