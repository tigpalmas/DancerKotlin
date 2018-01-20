package com.example.tiago.dancerkotlin.presenter;


import com.example.tiago.dancerkotlin.domain.Event;
import com.example.tiago.dancerkotlin.domain.Timeline;
import com.example.tiago.dancerkotlin.model.MVP;
import com.example.tiago.dancerkotlin.model.ModelLIstEvents;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/12/2017.
 */

public class PresenterListEvent implements MVP.PresenterListEvents {
    private MVP.ModelListEvents model;
    private MVP.ViewListEvents view;
    private List<Event> mEvent = new ArrayList<>();

    public PresenterListEvent() {
        model = new ModelLIstEvents(this);
    }

    @Override
    public void retrieveEvents(@NotNull Timeline timeline) {
        model.retrieveEvents(timeline);
    }

    @Override
    public void updateList(@NotNull List<Event> events) {
        mEvent.addAll(events);
        view.updateList();
    }

    @Override
    public void setView(@NotNull MVP.ViewListEvents view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

    @NotNull
    @Override
    public List<Event> getEvents() {
        return this.mEvent;
    }

    @Override
    public void clearList() {
        mEvent.clear();
    }
}
