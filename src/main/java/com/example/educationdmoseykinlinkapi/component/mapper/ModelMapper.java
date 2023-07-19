package com.example.educationdmoseykinlinkapi.component.mapper;

import com.example.educationdmoseykinlinkapi.node.model.ModelNode;
import com.example.educationdmoseykinlinkapi.node.model.ModelRequest;
import com.example.educationdmoseykinlinkapi.node.model.ModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    ModelNode toModel(ModelRequest modelRequest);

    ModelResponse toModelResponse(ModelNode model);
}
