package com.example.educationdmoseykinlinkapi.node.clazz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClassRequest {

    private Long id;
    private String mongoId;
    private String title;
}
