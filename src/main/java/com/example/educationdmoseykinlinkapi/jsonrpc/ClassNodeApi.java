package com.example.educationdmoseykinlinkapi.jsonrpc;

import com.example.educationdmoseykinlinkapi.node.clazz.ClassRequest;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

@JsonRpcService("/class")
public interface ClassNodeApi {

    ClassResponse save(@JsonRpcParam(value = "request") ClassRequest modelRequest);

    ClassResponse getByMongoId(@JsonRpcParam(value = "mongoId") String mongoId);

    List<ClassResponse> getAll();

    ClassResponse update(@JsonRpcParam(value = "request") ClassRequest classRequest);

    void delete(@JsonRpcParam(value = "mongoId") String mongoId);

}
