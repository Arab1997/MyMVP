package my.mymvp.screens.employees;


import android.widget.Toast;

import androidx.core.util.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.disposables.Disposable;

import my.mymvp.api.ApiFactory;
import my.mymvp.api.ApiService;
import my.mymvp.pojo.EmployeeResponse;
import rx.schedulers.Schedulers;

public class EmployeeListPresenter {


    private CompositeDisposable compositeDisposable;
    private EmployeesListView view;

    public EmployeeListPresenter(EmployeesListView view) {
        this.view = view;
    }
    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getEmployees()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())// kakom patoke vsyo delat
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        view.showData(employeeResponse.getEmployees());
                    }
                }, new io.reactivex.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }



    public void disposeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}


