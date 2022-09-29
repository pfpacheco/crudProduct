package com.envixo.challenge.service;

import com.envixo.challenge.entity.Product;
import com.envixo.challenge.repository.ProductRepository;
import com.envixo.challenge.service.handler.ProductHandler;
import com.envixo.challenge.service.intf.IProductService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ProductService implements IProductService {

    private static final Logger LOG = Logger.getLogger(ProductService.class);

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductHandler productHandler;

    @Override
    public JsonObject addOrUpdateProduct(String productJson) {
        Product product;
        JsonObject productResponse = null;
        if (productJson != null && !productJson.isEmpty()) {
            try {
                Gson gson = new Gson();
                product = productHandler.parseToEntity(productJson, gson);
                product = productRepository.save(product);
                productResponse = productHandler.parseToJson(product);
            } catch (Exception e) {
                LOG.error("Error in addOrUpdateProduct service {}", e);
            }
        } else {
            LOG.error("Empty request in addOrUpdateProduct service is not acceptable");
        }
        return productResponse;
    }

    @Override
    public JsonObject findById(Long id) {
        Product product;
        JsonObject productResponse = null;
        if (id != null && id !=0) {
            try {
                product = productRepository.findById(id);
                productResponse = productHandler.parseToJson(product);
            } catch (Exception e) {
                LOG.error("Error in findById service {}", e);
            }
        } else {
            LOG.error("Error in findById service, null or 0 id value is not acceptable");
        }
        return productResponse;
    }

    @Override
    public JsonObject findByCategory(String category) {
        Product product;
        JsonObject productResponse = null;
        if (category != null && !category.isEmpty()) {
            try {
                product = productRepository.findByCategory(category);
                productResponse = productHandler.parseToJson(product);
            } catch (Exception e) {
                LOG.error("Error in findByCategory service {}", e);
            }
        } else {
            LOG.error("Error in findByCategory service, null or empty category is not acceptable");
        }
        return productResponse;
    }

    @Override
    public JsonObject findByTag(String tag) {
        Product product;
        JsonObject productResponse = null;
        if (tag != null && !tag.isEmpty()) {
            try {
                product = productRepository.findByTag(tag);
                productResponse = productHandler.parseToJson(product);
            } catch (Exception e) {
                LOG.error("Error in findByTag service {}", e);
            }
        } else {
            LOG.error("Error in findByTag service, null or empty tag is not acceptable");
        }
        return productResponse;
    }

    @Override
    public JsonObject findAll() {
        JsonObject productResponse = new JsonObject();
        JsonArray productArray = new JsonArray();
        List<Product> productList;
        try {
            productList = productRepository.findAll();
            productList.forEach(product -> {
                JsonObject productJson = productHandler.parseToJson(product);
                productArray.add(productJson);
            });
            productResponse.add("products", productArray);
        } catch (Exception e) {
            LOG.error("Error in findAll service {}", e);
        }
        return productResponse;
    }

    @Override
    public JsonObject delete(String productJson) {
        Product product = null;
        Gson gson = new Gson();
        if (productJson !=null && !productJson.isEmpty()) {
            try {
                product = productHandler.parseToEntity(productJson, gson);
                productRepository.delete(product);
                LOG.info("object has been deleted");
            } catch (Exception e) {
                LOG.error("Error deleting product {}", e);
            }
        } else {
            LOG.error("Error in delete service, empty or null productJson is not acceptable");
        }
        assert product != null;
        return productHandler.parseToJson(product);
    }

    @Override
    public void delete(Long id) {
        if (id !=null && id != 0) {
            try {
                productRepository.delete(id);
                LOG.infof("Product with id {} has been deleted.", id);
            } catch (Exception e) {
                LOG.error("Error deleting product {}", e);
            }
        } else {
            LOG.error("Error in delete service, empty or null productJson is not acceptable");
        }
    }
}
