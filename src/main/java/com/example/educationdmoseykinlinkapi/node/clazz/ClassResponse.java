package com.example.educationdmoseykinlinkapi.node.clazz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassResponse {

    private final String classMongoId;
    private final String classTitle;
}
