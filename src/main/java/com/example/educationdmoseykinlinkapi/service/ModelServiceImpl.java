package com.example.educationdmoseykinlinkapi.service;

import com.example.educationdmoseykinlinkapi.component.mapper.ModelMapper;
import com.example.educationdmoseykinlinkapi.exception.NodeNotFoundException;
import com.example.educationdmoseykinlinkapi.node.model.ModelNode;
import com.example.educationdmoseykinlinkapi.node.model.ModelRequest;
import com.example.educationdmoseykinlinkapi.node.model.ModelResponse;
import com.example.educationdmoseykinlinkapi.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelServiceImpl implements ModelService {
    public static final String MODEL_WITH_THIS_MONGO_ID_EXISTS = "ModelNode с mongoId = %s уже существует";
    private static final String NODE_NOT_FOUND = "Нода с id = %s не найдена";

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ModelResponse save(ModelRequest modelRequest) {
        checkIsModelNodePresent(modelRequest.getMongoId());

        ModelNode modelNode = modelMapper.toModel(modelRequest);
        ModelNode savedModelNode = modelRepository.save(modelNode);
        return modelMapper.toModelResponse(savedModelNode);
    }

    private void checkIsModelNodePresent(String mongoId) {
        modelRepository.findByMongoId(mongoId)
                .ifPresent(modelNode -> {
                    throw new IllegalStateException(String.format(MODEL_WITH_THIS_MONGO_ID_EXISTS, mongoId));
                });
    }

    @Override
    public ModelResponse getByMongoId(String mongoId) {
        ModelNode modelNode = modelRepository.findByMongoId(mongoId)
                .orElseThrow(() -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));
        return modelMapper.toModelResponse(modelNode);
    }

    @Override
    public List<ModelResponse> getAll() {
        List<ModelNode> modelNodes = modelRepository.findAll();
        return modelNodes.stream().map(modelMapper::toModelResponse).collect(Collectors.toList());
    }

    @Override
    public ModelResponse update(ModelRequest modelRequest) {
        ModelNode modelNode = modelRepository.findByMongoId(modelRequest.getMongoId())
                .orElseThrow(() -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, modelRequest.getMongoId())));
        modelNode.setTitle(modelRequest.getTitle());
        ModelNode savedModelNode = modelRepository.save(modelNode);
        return modelMapper.toModelResponse(savedModelNode);

    }

    @Override
    public void delete(String mongoId) {
        ModelNode modelNode = modelRepository.findByMongoId(mongoId)
                .orElseThrow(() -> new NodeNotFoundException(String.format(NODE_NOT_FOUND, mongoId)));
        modelRepository.delete(modelNode);
    }

    @Override
    public void createRelationShip(String modelMongoId, String classMongoId) {
        modelRepository.createRelationship(modelMongoId, classMongoId);
    }
}
