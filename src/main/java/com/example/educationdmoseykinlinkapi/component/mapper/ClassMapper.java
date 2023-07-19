package com.example.educationdmoseykinlinkapi.component.mapper;

import com.example.educationdmoseykinlinkapi.node.clazz.ClassNode;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassRequest;
import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = ModelMapper.class)
public interface ClassMapper {

    @Mapping(source = "modelRequest", target = "modelNode")
    ClassNode toClassNode(ClassRequest classRequest);

    @Mapping(source = "modelNode", target = "modelResponse")
    ClassResponse toClassResponse(ClassNode classNode);
}
