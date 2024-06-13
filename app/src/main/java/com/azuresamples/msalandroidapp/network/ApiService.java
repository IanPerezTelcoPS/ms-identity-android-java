package com.azuresamples.msalandroidapp.network;

import model.FilesResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiService {
    @GET("v1.0/me/drive/root/children/")
    Call<FilesResponseModel> FILES_RESPONSE_MODEL_CALL(@Header("Authorization") String token);

    @GET("v1.0/me/drive/items/{folder_id}/children")
    Call<FilesResponseModel> GET_FOLDER_CHILDREN(@Header("Authorization") String token, @Path("folder_id") String path);
}
