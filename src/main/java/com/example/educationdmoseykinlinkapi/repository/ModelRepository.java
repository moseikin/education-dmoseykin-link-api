package com.example.educationdmoseykinlinkapi.repository;

import com.example.educationdmoseykinlinkapi.node.model.ModelNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends Neo4jRepository<ModelNode, Long> {

    Optional<ModelNode> findByMongoId(String mongoId);

    @Query("match (Model{mongoId:$modelMongoId}), (Class{mongoId:$classMongoId}) " +
            "where not exists ((Model)-[:RELATIONSHIP]->()) " +
            "and not exists (()-[:RELATIONSHIP]->(Class)) " +
            "create (Model)-[:RELATIONSHIP]->(Class)")
    void createRelationship(@Param("modelMongoId") String modelMongoId, @Param("classMongoId") String classMongoId);
}
