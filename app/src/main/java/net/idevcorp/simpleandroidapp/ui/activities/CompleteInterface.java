package net.idevcorp.simpleandroidapp.ui.activities;


import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.models.QuestionModel;

public interface CompleteInterface {

    void onFindResult(AnswerModel result);
    void onFindQuestion(QuestionModel questionModel);


}
