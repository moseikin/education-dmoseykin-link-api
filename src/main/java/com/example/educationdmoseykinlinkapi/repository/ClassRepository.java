package com.example.educationdmoseykinlinkapi.repository;

import com.example.educationdmoseykinlinkapi.node.clazz.ClassNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends Neo4jRepository<ClassNode, Long> {

    Optional<ClassNode> findByMongoId(String mongoId);

    @Query("match (Model{mongoId:$modelMongoId})-[:RELATIONSHIP]->(c:Class) RETURN c")
    ClassNode findByModelNodesMongoId(String modelMongoId);
}
