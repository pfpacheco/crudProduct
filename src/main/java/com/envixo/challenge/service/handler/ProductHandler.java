package com.envixo.challenge.service.handler;

import com.envixo.challenge.entity.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProductHandler {

    public JsonObject parseToJson(Product product) {
        JsonObject productJson = new JsonObject();
        productJson.addProperty("id", product.getId());
        productJson.addProperty("title", product.getTitle());
        productJson.addProperty("description", product.getDescription());
        productJson.addProperty("img_path", product.getPath());
        productJson.addProperty("status", product.getStatus());
        productJson.addProperty("category", product.getCategory());
        productJson.addProperty("tag", product.getTag());
        productJson.addProperty("price", product.getPrice());
        productJson.addProperty("price_comparison", product.getPriceComparison());
        return productJson;
    }

    public Product parseToEntity(String jsonString, Gson gson) {
        Product product;
        JsonObject productJson = JsonParser.parseString(jsonString).getAsJsonObject();
        product = gson.fromJson(productJson, Product.class);
        return product;
    }

    public JsonObject parseErrorMessage(String message, int code) {
        JsonObject error = new JsonObject();
        error.addProperty("message", message);
        error.addProperty("code", code);
        return error;
    }
}


