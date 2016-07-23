package comnathanromike.httpsgithub.yes_you_can.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstructablesService {
    public static final String BASE_URL = "https://devru-instructables.p.mashape.com/json-api/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();



}
