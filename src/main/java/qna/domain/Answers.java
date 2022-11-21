package qna.domain;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    protected Answers() {
    }

    public void add(Answer answer){
        if( !this.answers.contains(answer)){
            answers.add(answer);
        }
    }

    public boolean validateWriterSameLoginUser(User loginUser){
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }


    public boolean isEmpty() {
        return answers.isEmpty();
        
    }

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
