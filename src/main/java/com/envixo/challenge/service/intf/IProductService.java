package com.envixo.challenge.service.intf;

import com.google.gson.JsonObject;
import io.quarkus.vertx.http.runtime.devmode.Json;

public interface IProductService {

    JsonObject addOrUpdateProduct(String productJson);

    JsonObject findById(Long id);

    JsonObject findByCategory(String category);

    JsonObject findByTag(String tag);

    JsonObject findAll();

    JsonObject delete(String productSJson);

    void delete(Long id);
}
