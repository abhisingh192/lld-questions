package service;

import entity.Answer;
import repo.DataStore;

import java.util.UUID;

public class AnswerService {

    private final DataStore store;

    public AnswerService(DataStore store) {
        this.store = store;
    }

    public Answer postAnswer(String qId, String userId, String text) {
        Answer ans = new Answer(UUID.randomUUID().toString(), text, userId, qId);
        store.getAnswers().put(ans.getId(), ans);
//        store.getQuestions().get(qId).answers.add(ans);
        return ans;
    }
}
