package com.example.tiago.dancerkotlin.model;

import android.util.Log;

import com.example.tiago.dancerkotlin.domain.Dancer;
import com.example.tiago.dancerkotlin.domain.ErrorModel;
import com.example.tiago.dancerkotlin.domain.Event;
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

public class ModelCadastrarDancer implements MVP.ModelRegisterDancer {
    private DancerService service;
    private MVP.PresenterRegisterDancer presenter;

    public ModelCadastrarDancer(MVP.PresenterRegisterDancer presenter) {
        DancerProvider provider = new DancerProvider();
        service = provider.getmTService();
        this.presenter = presenter;
    }

    @Override
    public void retriveStyles() {
        getStyles();
    }


    private void getStyles() {
        Call<List<Style>> call = service.getStyles();
        call.enqueue(new Callback<List<Style>>() {
            @Override
            public void onResponse(Call<List<Style>> call, Response<List<Style>> response) {
                if (response.isSuccessful()) {
                    List<Style> styles = response.body();
                    if (styles.size() > 0) {
                        presenter.updateListStyle(styles);
                        presenter.showLoadProgresss(false, "");
                    } else {
                        presenter.showLoadProgresss(false, "Sem eventos cadastrados no momento");
                    }
                } else {
                    presenter.showLoadProgresss(false, "error");
                }
            }

            @Override
            public void onFailure(Call<List<Style>> call, Throwable t) {
                presenter.showLoadProgresss(false, t.getMessage());
            }
        });
    }

    @Override
    public void createDancer(@NotNull Dancer dancer) {
        createDancerServer(dancer);
    }

    private void createDancerServer(Dancer dancer) {
        Call<Dancer> call = service.postDancer(dancer);
        call.enqueue(new Callback<Dancer>() {
            @Override
            public void onResponse(Call<Dancer> call, Response<Dancer> response) {
                if (response.isSuccessful()) {
                    Dancer dancer = response.body();
                    if (dancer != null) {
                        presenter.showLoadProgresss(false, "Dancer Cadastrado com sucesso");
                        presenter.dancerId(dancer);
                    } else {
                        presenter.showLoadProgresss(false, "Erro");
                    }
                } else {
                    ErrorModel errorModel = ErrorUtils.parseError(response);
                   presenter.showLoadProgresss(false, errorModel.getErrors().get(0).getMsg() + " " + errorModel.getErrors().get(0).getParam());

                }
            }

            @Override
            public void onFailure(Call<Dancer> call, Throwable t) {
                presenter.showLoadProgresss(false, t.getMessage());
            }
        });
    }


}
