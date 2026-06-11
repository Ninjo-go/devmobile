package com.example.lab20;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
// 403f2e has created the ContactApi interface
public interface ContactApi {
    // 403f2e has handeld the insertContact call
    @POST("insertContact.php")
    Call<ApiResponse> insertContact(@Body Contact contact);
    // 403f2e has handeld the getAllContacts call
    @GET("getAllContacts.php")
    Call<List<Contact>> getAllContacts();
    // 403f2e has handeld the searchContacts call
    @GET("searchContact.php")
    Call<List<Contact>> searchContacts(@Query("keyword") String keyword);
}