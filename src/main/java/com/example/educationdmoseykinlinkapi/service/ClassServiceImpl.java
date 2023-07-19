package com.example.educationdmoseykinlinkapi.service;

import com.example.educationdmoseykinlinkapi.component.mapper.ClassMapper;
import com.example.educationdmoseykinlinkapi.exception.NodeNotFoundException;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassNode;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassRequest;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;
import com.example.educationdmoseykinlinkapi.repository.ClassRepository;
import com.example.educationdmoseykinlinkapi.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    public static final String CLASS_WITH_THIS_MONGO_ID_EXISTS = "ClassNode с mongoId = %s уже существует";
    public static final String MODEL_WITH_THIS_MONGO_ID_EXISTS = "ModelNode с mongoId = %s уже существует";
    private static final String NODE_NOT_FOUND = "Нода с id = %s не найдена";
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final ModelRepository modelRepository;

    // Если по mongoId находится ранее сохраненный ClassNode, бросаем исключение
    // Если присутствует вложенная ModelNode, то по mongoId получаем ее из БД.
    // Если нашли, то бросаем исключение
    @Override
    public ClassResponse save(ClassRequest classRequest) {
        checkIsClassNodePresent(classRequest.getMongoId());

        ClassNode classNode = classMapper.toClassNode(classRequest);

        if (classRequest.getModelRequest() != null) {
            checkIsModelNodePresent(classRequest.getModelRequest().getMongoId());
        }

        ClassNode savedClassNode = classRepository.save(classNode);
        return classMapper.toClassResponse(savedClassNode);
    }

    private void checkIsClassNodePresent(String mongoId) {
        classRepository.findByMongoId(mongoId)
                .ifPresent(classNode -> {
                    throw new IllegalStateException(String.format(CLASS_WITH_THIS_MONGO_ID_EXISTS, mongoId));
                });
    }

    private void checkIsModelNodePresent(String mongoId) {
        modelRepository.findByMongoId(mongoId)
                .ifPresent(modelNode -> {
                    throw new IllegalStateException(String.format(MODEL_WITH_THIS_MONGO_ID_EXISTS, mongoId));
                });
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
    public ClassResponse update(ClassRequest classRequest) {
        setModelNodeToNullIfPresent(classRequest);

        ClassNode classNode = getClassNodeByMongoId(classRequest.getMongoId());

        classNode.setTitle(classRequest.getTitle());
        ClassNode savedClassNode = classRepository.save(classNode);
        return classMapper.toClassResponse(savedClassNode);
    }

    // обновляем без связей. Чтобы обновить ModelNode, есть свой метод
    private void setModelNodeToNullIfPresent(ClassRequest classRequest) {
        if (classRequest.getModelRequest() != null) {
            classRequest.setModelRequest(null);
        }
    }

    private ClassNode getClassNodeByMongoId(String mongoId) {
        return classRepository.findByMongoId(mongoId).orElseThrow(
                () -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));
    }

    @Override
    public void delete(String mongoId) {
        ClassNode optionalClassNode = classRepository.findByMongoId(mongoId)
                .orElseThrow(() -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));
        classRepository.delete(optionalClassNode);
    }
}
