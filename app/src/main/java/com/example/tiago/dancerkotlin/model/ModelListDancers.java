package com.example.tiago.dancerkotlin.model;

import com.example.tiago.dancerkotlin.domain.Dancer;
import com.example.tiago.dancerkotlin.domain.ErrorModel;
import com.example.tiago.dancerkotlin.domain.Style;
import com.example.tiago.dancerkotlin.network.DancerProvider;
import com.example.tiago.dancerkotlin.network.DancerService;
import com.example.tiago.dancerkotlin.utils.ErrorUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 17/12/2017.
 */

public class ModelListDancers implements MVP.ModelListDancers {
    private DancerService service;
    private MVP.PresenterListDancers presenter;

    public ModelListDancers(MVP.PresenterListDancers presenter) {
        DancerProvider provider = new DancerProvider();
        service = provider.getmTService();
        this.presenter = presenter;
    }


    @Override
    public void retrieveDancers() {
        getDancersServer();
    }



    private void getDancersServer() {
        Call<List<Dancer>> call = service.getDancers();
        call.enqueue(new Callback<List<Dancer>>() {
            @Override
            public void onResponse(Call<List<Dancer>> call, Response<List<Dancer>> response) {
                if (response.isSuccessful()) {
                    List<Dancer> dancers = response.body();
                    if (dancers != null) {
                        presenter.showLoadProgresss(false, "");
                    } else {
                        presenter.showLoadProgresss(false, "Sem Dancers Cadastrados no momento");
                    }
                } else {
                    ErrorModel errorModel = ErrorUtils.parseError(response);
                    presenter.showLoadProgresss(false, errorModel.getErrors().get(0).getMsg() + " " + errorModel.getErrors().get(0).getParam());

                }
            }

            @Override
            public void onFailure(Call<List<Dancer>> call, Throwable t) {
                presenter.showLoadProgresss(false, t.getMessage());
            }
        });

    }



}
