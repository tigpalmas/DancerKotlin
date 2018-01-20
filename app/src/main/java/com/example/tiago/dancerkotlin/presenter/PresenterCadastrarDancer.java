package com.example.tiago.dancerkotlin.presenter;


import com.example.tiago.dancerkotlin.domain.Dancer;
import com.example.tiago.dancerkotlin.domain.Event;
import com.example.tiago.dancerkotlin.domain.Style;
import com.example.tiago.dancerkotlin.model.MVP;
import com.example.tiago.dancerkotlin.model.ModelCadastrarDancer;
import com.example.tiago.dancerkotlin.model.ModelLIstEvents;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/12/2017.
 */

public class PresenterCadastrarDancer implements MVP.PresenterRegisterDancer {
    private MVP.ModelRegisterDancer model;
    private MVP.ViewRegisterDancer view;
    private List<Event> mEvent = new ArrayList<>();
    private List<Style> mStyles = new ArrayList<>();

    public PresenterCadastrarDancer() {
        model = new ModelCadastrarDancer(this);
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }



    @Override
    public void retriveStyles() {
        model.retriveStyles();
    }

    @Override
    public void updateListStyle(@NotNull List<Style> styles) {
        mStyles.addAll(styles);
        view.updateListStyle();
    }

    @Override
    public void setView(@NotNull MVP.ViewRegisterDancer view) {
        this.view = view;
    }

    @NotNull
    @Override
    public List<Style> getStyles() {
        return mStyles;
    }

    @Override
    public void createDancer(@NotNull Dancer dancer) {
        model.createDancer(dancer);
    }

    @Override
    public void dancerId(@NotNull Dancer dancerId) {
        view.dancerId(dancerId);
    }
}
