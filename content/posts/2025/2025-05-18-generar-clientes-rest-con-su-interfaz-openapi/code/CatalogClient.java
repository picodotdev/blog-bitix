package io.github.picodotdev.catalog.client.api;

import io.github.picodotdev.catalog.client.invoker.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import io.github.picodotdev.catalog.client.model.ClientEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CatalogClient {
  /**
   * Get an event
   * Get an event
   * @param id Id of the event (required)
   * @return Call&lt;ClientEvent&gt;
   */
  @GET("events/v1/{id}")
  Call<ClientEvent> getEvent(
    @retrofit2.http.Path("id") Long id
  );

}
