package kursovaya20.kursovaya20.controller;

import kursovaya20.kursovaya20.Question;
import kursovaya20.kursovaya20.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public void addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @PostMapping("/remove")
    public void removeQuestion(@RequestBody Question question) {
        questionService.removeQuestion(question);
    }

    @GetMapping("/getAll")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}