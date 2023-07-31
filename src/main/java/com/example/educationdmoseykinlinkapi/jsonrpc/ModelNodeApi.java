package com.example.educationdmoseykinlinkapi.jsonrpc;

import com.example.educationdmoseykinlinkapi.node.model.ModelRequest;
import com.example.educationdmoseykinlinkapi.node.model.ModelResponse;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

@JsonRpcService("/model")
public interface ModelNodeApi {

    ModelResponse save(@JsonRpcParam(value = "request") ModelRequest modelRequest);

    ModelResponse getByMongoId(@JsonRpcParam(value = "mongoId") String mongoId);

    List<ModelResponse> getAll();

    ModelResponse update(@JsonRpcParam(value = "request") ModelRequest modelRequest);

    void delete(@JsonRpcParam(value = "mongoId") String mongoId);
}
