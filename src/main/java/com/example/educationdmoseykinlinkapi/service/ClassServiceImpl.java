package com.example.educationdmoseykinlinkapi.service;

import com.example.educationdmoseykinlinkapi.component.mapper.ClassMapper;
import com.example.educationdmoseykinlinkapi.exception.NodeNotFoundException;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassNode;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassRequest;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;
import com.example.educationdmoseykinlinkapi.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassServiceImpl implements ClassService {
    private static final String NODE_NOT_FOUND = "Нода с id = %s не найдена";
    private static final String NODE_WITH_MONGO_ID_EXISTS = "Нода с mongoId = %s уже есть в БД";

    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    @Override
    @Transactional
    public ClassResponse save(ClassRequest classRequest) {
        classRepository.findByMongoId(classRequest.getMongoId())
                .ifPresent(classNode -> {
                    throw new IllegalStateException(String.format(NODE_WITH_MONGO_ID_EXISTS, classRequest.getMongoId()));
                });
        ClassNode classNode = classMapper.toClassNode(classRequest);

        ClassNode savedClassNode = classRepository.save(classNode);
        return classMapper.toClassResponse(savedClassNode);
    }

    @Override
    public ClassResponse getByMongoId(String mongoId) {
        ClassNode optionalClassNode = classRepository.findByMongoId(mongoId)
                .orElseThrow(() -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));

        return classMapper.toClassResponse(optionalClassNode);
    }

    @Override
    public List<ClassResponse> getAll() {
        List<ClassNode> classNodes = classRepository.findAll();
        return classNodes.stream().map(classMapper::toClassResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClassResponse update(ClassRequest classRequest) {
        ClassNode classNode = getClassNodeByMongoId(classRequest.getMongoId());

        classNode.setTitle(classRequest.getTitle());
        ClassNode savedClassNode = classRepository.save(classNode);
        return classMapper.toClassResponse(savedClassNode);
    }

    private ClassNode getClassNodeByMongoId(String mongoId) {
        return classRepository.findByMongoId(mongoId).orElseThrow(
                () -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));
    }

    @Override
    @Transactional
    public void delete(String mongoId) {
        ClassNode optionalClassNode = classRepository.findByMongoId(mongoId)
                .orElseThrow(() -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));
        classRepository.delete(optionalClassNode);
    }

    @Override
    public ClassResponse getByRelatedModelMongoId(String modelMongoId) {
        ClassNode classNode = classRepository.findByModelNodesMongoId(modelMongoId);
        return classMapper.toClassResponse(classNode);
    }
}
