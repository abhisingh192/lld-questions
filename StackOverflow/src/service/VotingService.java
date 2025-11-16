package service;

import entity.Answer;
import entity.Question;
import entity.User;
import repo.DataStore;

public class VotingService {

    private final DataStore store;

    public VotingService(DataStore store) {
        this.store = store;
    }

    public void voteQuestion(String qId, String userId, int delta) {
        Question q = store.getQuestions().get(qId);
        int votes = q.getVotes();
        votes += delta;
        store.getUsers().get(userId).addReputation(votes * 10);
    }

    public void voteAnswer(String aId, String userId, int delta) {
        Answer a = store.getAnswers().get(aId);
        int votes = a.getVotes();
        votes += delta;
        store.getUsers().get(userId).addReputation(delta * 5);
    }

}
