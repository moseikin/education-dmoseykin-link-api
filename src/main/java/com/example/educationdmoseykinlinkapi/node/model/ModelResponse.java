package com.example.educationdmoseykinlinkapi.node.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModelResponse {

    private final Long id;
    private final String mongoId;
    private final String title;
}
