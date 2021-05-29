package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Suggestion;

public class SuggestionRepository {

    private static SuggestionRepository repository;
    private List<Suggestion> suggestionList;

    public static SuggestionRepository getInstance(){
        if(repository==null){
            repository = new SuggestionRepository();
        }

        return repository;
    }

    public List<Suggestion> getSuggestionList(){
        suggestionList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Suggestion  suggestion = new Suggestion();
            suggestion.setName("Suggestion "+i);
            suggestionList.add(suggestion);
        }

        return suggestionList;
    }

}
