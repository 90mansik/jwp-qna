package qna.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class QuestionRepositoryTest {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        Question expected = new Question("제목", "내용");
        Question actual = questionRepository.save(expected);
        assertAll(
                () -> assertThat(expected.getContents()).isEqualTo(actual.getContents()),
                () -> assertThat(expected.getTitle()).isEqualTo(actual.getTitle())
        );
    }

    @Test
    void findAll() {
        Question expected = new Question("제목", "내용");
        questionRepository.save(expected);
        List<Question> actual = questionRepository.findAll();
        assertThat(actual.get(0).getContents()).isNotNull();
        assertThat(actual.get(0).getCreatedAt()).isNotNull();
        System.out.println(actual.get(0).toString());
    }

    @Test
    void findByDeletedFalse() {
        Question expected = new Question("제목", "내용");
        questionRepository.save(expected);
        List<Question> actual = questionRepository.findByDeletedFalse();
        assertThat(actual.get(0).getContents()).isNotNull();
    }

    @Test
    void findByWriterId() {
        User user = userRepository.save(UserTest.JAVAJIGI);
        Question expected = new Question("제목", "내용").writeBy(user);
        questionRepository.save(expected);
        List<Question> actual = questionRepository.findByWriterId(user);
        assertThat(actual.get(0)).isEqualTo(expected);
    }
}
