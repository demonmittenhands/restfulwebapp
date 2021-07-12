package com.eeilander.restfulwebservicemm.repositories;

import java.util.List;
import java.util.Optional;

import com.eeilander.restfulwebservicemm.entities.NodeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, Integer>{

    Optional<NodeEntity> findAllByName(String name);

    // @Query("SELECT * FROM node WHERE parent_node_id = 'name'")
    Optional<List<NodeEntity>> findChildNodesByParentNodeId (int id);

}
