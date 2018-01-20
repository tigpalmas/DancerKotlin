package com.example.tiago.dancerkotlin.presenter;


import com.example.tiago.dancerkotlin.domain.Dancer;
import com.example.tiago.dancerkotlin.domain.Timeline;
import com.example.tiago.dancerkotlin.model.MVP;
import com.example.tiago.dancerkotlin.model.ModelListDancers;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/12/2017.
 */

public class PresenterListDancer implements MVP.PresenterListDancers {
    private MVP.ModelListDancers model;
    private MVP.ViewListDancers view;
    private List<Dancer> mDancer = new ArrayList<>();

    public PresenterListDancer() {
        model = new ModelListDancers(this);
    }

    @Override
    public void retrieveDancers() {
        model.retrieveDancers();
    }

    @Override
    public void updateList(@NotNull List<Dancer> Dancers) {
        mDancer.addAll(Dancers);
        view.updateList();
    }

    @Override
    public void setView(@NotNull MVP.ViewListDancers view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

    @NotNull
    @Override
    public List<Dancer> getDancers() {
        return this.mDancer;
    }

    @Override
    public void clearList() {
        mDancer.clear();
    }
}
