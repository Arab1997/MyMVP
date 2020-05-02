package my.mymvp.api;






import io.reactivex.Observable;

import my.mymvp.pojo.EmployeeResponse;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees(); // resultat zaprosa   Observable io.reactivex
}
