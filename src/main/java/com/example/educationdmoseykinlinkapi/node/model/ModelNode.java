package com.example.educationdmoseykinlinkapi.node.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("Model")
public class ModelNode {

    @Id
    @GeneratedValue
    private Long id;

    private String mongoId;

    private String title;
}
