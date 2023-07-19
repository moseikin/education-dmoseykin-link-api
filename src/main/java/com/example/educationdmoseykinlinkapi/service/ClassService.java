package com.example.educationdmoseykinlinkapi.service;

import com.example.educationdmoseykinlinkapi.node.clazz.ClassRequest;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;

import java.util.List;

public interface ClassService {

    ClassResponse save(ClassRequest classRequest);

    ClassResponse getByMongoId(String mongoId);

    List<ClassResponse> getAll();

    ClassResponse update(ClassRequest classRequest);

    void delete(String mongoId);
}
