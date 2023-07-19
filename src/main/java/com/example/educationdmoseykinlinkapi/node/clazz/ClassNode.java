package com.example.educationdmoseykinlinkapi.node.clazz;

import com.example.educationdmoseykinlinkapi.node.model.ModelNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("Class")
public class ClassNode {

    @Id
    @GeneratedValue
    private Long id;
    private String mongoId;
    private String title;

    @Relationship(type = "RELATIONSHIP", direction = Relationship.Direction.INCOMING)
    private ModelNode modelNode;
}
