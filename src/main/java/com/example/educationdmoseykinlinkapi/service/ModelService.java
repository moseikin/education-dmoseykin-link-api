package com.example.educationdmoseykinlinkapi.service;

import com.example.educationdmoseykinlinkapi.node.model.ModelRequest;
import com.example.educationdmoseykinlinkapi.node.model.ModelResponse;

import java.util.List;

public interface ModelService {

    ModelResponse save(ModelRequest modelRequest);

    ModelResponse getByMongoId(String mongoId);

    List<ModelResponse> getAll();

    ModelResponse update(ModelRequest modelRequest);

    void delete(String mongoId);

    void createRelationShip(String modelMongoId, String classMongoId);
}
