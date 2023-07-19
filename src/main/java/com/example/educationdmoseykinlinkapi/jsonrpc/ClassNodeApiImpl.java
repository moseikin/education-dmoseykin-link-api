package com.example.educationdmoseykinlinkapi.jsonrpc;

import com.example.educationdmoseykinlinkapi.node.clazz.ClassRequest;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;
import com.example.educationdmoseykinlinkapi.service.ClassService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AutoJsonRpcServiceImpl
@RequiredArgsConstructor
public class ClassNodeApiImpl implements ClassNodeApi {

    private final ClassService classService;

    @Override
    public ClassResponse save(ClassRequest classRequest) {
        return classService.save(classRequest);
    }

    @Override
    public ClassResponse getByMongoId(String mongoId) {
        return classService.getByMongoId(mongoId);
    }

    @Override
    public List<ClassResponse> getAll() {
        return classService.getAll();
    }

    @Override
    public ClassResponse update(ClassRequest classRequest) {
        return classService.update(classRequest);
    }

    @Override
    public void delete(String mongoId) {
        classService.delete(mongoId);
    }
}
