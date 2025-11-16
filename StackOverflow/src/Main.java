import entity.Answer;
import entity.Question;
import repo.DataStore;
import service.*;
import strategy.SearchContext;
import strategy.TagSearchStrategy;
import strategy.UserSearchStrategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        DataStore dataStore = new DataStore();

        UserService userService = new UserService(dataStore);
        QuestionService questionService = new QuestionService(dataStore);
        AnswerService answerService = new AnswerService(dataStore);
        CommentService commentService = new CommentService(dataStore);
        VotingService votingService = new VotingService(dataStore);

        // Users
        userService.createUser("u1", "Alice");
        userService.createUser("u2", "Bob");

        // Post a question
        Question q1 = questionService.postQuestion("u1", "What is polymorphism?", "Explain in Java.", Arrays.asList("java", "oop"));

        // Answer
        Answer a1 = answerService.postAnswer(q1.getId(), "u2", "Polymorphism allows different forms of methods.");

        // Comment
        commentService.postCommentOnAnswer(a1.getId(), "u1", "Thanks, clear!");

        // Voting
        votingService.voteQuestion(q1.getId(), "u2", 1);
        votingService.voteAnswer(a1.getId(), "u1", 1);

        // Search
        SearchContext search = new SearchContext(new TagSearchStrategy());
        System.out.println("Search by tag 'java':");
        for (Question q : search.executeSearch(dataStore, "java")) {
            System.out.println("  " + q.getTitle());
        }


        search = new SearchContext(new UserSearchStrategy());
        System.out.println("\nQuestions by user u1:");
        for (Question q : search.executeSearch(dataStore, "u1")) {
            System.out.println("  " + q.getTitle());
        }
    }
}