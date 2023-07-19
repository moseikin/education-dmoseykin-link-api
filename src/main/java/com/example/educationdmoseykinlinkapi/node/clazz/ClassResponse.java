package com.example.educationdmoseykinlinkapi.node.clazz;

import com.example.educationdmoseykinlinkapi.node.model.ModelResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassResponse {

    private final Long id;
    private final String mongoId;
    private final String title;
    private final ModelResponse modelResponse;
}
