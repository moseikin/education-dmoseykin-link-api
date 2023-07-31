package com.example.educationdmoseykinlinkapi.node.model;

import com.example.educationdmoseykinlinkapi.node.clazz.ClassResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModelResponse {


    private final Long id;

    private final String mongoId;

    private final String title;

    private final ClassResponse classResponse;
}
