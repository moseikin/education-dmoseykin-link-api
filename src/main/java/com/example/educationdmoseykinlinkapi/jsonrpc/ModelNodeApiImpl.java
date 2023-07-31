package com.example.educationdmoseykinlinkapi.jsonrpc;

import com.example.educationdmoseykinlinkapi.node.model.ModelRequest;
import com.example.educationdmoseykinlinkapi.node.model.ModelResponse;
import com.example.educationdmoseykinlinkapi.service.ModelService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AutoJsonRpcServiceImpl
@RequiredArgsConstructor
public class ModelNodeApiImpl implements ModelNodeApi {

    private final ModelService modelService;

    @Override
    public ModelResponse save(ModelRequest modelRequest) {
        return modelService.save(modelRequest);
    }

    @Override
    public ModelResponse getByMongoId(String mongoId) {
        return modelService.getByMongoId(mongoId);
    }

    @Override
    public List<ModelResponse> getAll() {
        return modelService.getAll();
    }

    @Override
    public ModelResponse update(ModelRequest modelRequest) {
        return modelService.update(modelRequest);
    }

    @Override
    public void delete(String mongoId) {
        modelService.delete(mongoId);
    }
}
