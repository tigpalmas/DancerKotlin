package com.example.tiago.dancerkotlin.model;

import com.example.tiago.dancerkotlin.domain.Event;
import com.example.tiago.dancerkotlin.domain.Timeline;
import com.example.tiago.dancerkotlin.network.DancerProvider;
import com.example.tiago.dancerkotlin.network.DancerService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 17/12/2017.
 */

public class ModelLIstEvents implements MVP.ModelListEvents {
    private DancerService service;
    private MVP.PresenterListEvents presenter;

    public ModelLIstEvents(MVP.PresenterListEvents presenter) {
        DancerProvider provider = new DancerProvider();
        service = provider.getmTService();
        this.presenter = presenter;
    }

    @Override
    public void retrieveEvents(Timeline timeline) {
        getEvensFromServer(timeline);
    }

    private void getEvensFromServer(final Timeline timeline) {
        Call<List<Event>> call = service.getEvents(timeline.getSkip(), timeline);
       call.enqueue(new Callback<List<Event>>() {
           @Override
           public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    List<Event> events = response.body();
                    if(events.size()>0){
                        presenter.updateList(events);
                        presenter.showLoadProgresss(false, "");
                    }else{

                        if(timeline.getSkip() == 0 && timeline.getDate()==null){
                            presenter.showLoadProgresss(false, "Sem eventos cadastrados no momento");
                        }
                        if( timeline.getDate()!=null){
                            presenter.showLoadProgresss(false, "Sem eventos cadastrados para esse dia");
                        }
                    }
                }else{
                    presenter.showLoadProgresss(false, "error");
                }
           }

           @Override
           public void onFailure(Call<List<Event>> call, Throwable t) {
               presenter.showLoadProgresss(false, t.getMessage());
           }
       });
    }
}
