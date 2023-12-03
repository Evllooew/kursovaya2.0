package kursovaya20.kursovaya20.Service;

import kursovaya20.kursovaya20.Question;
import kursovaya20.kursovaya20.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> javaQuestions = new HashSet<>();

    @Override
    public void addQuestion(Question question) {
        javaQuestions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        javaQuestions.remove(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(javaQuestions);
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestions.isEmpty()) {
            throw new RuntimeException("No Java questions available");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(javaQuestions.size());
        List<Question> questionList = new ArrayList<>(javaQuestions);
        return questionList.get(randomIndex);
    }
}