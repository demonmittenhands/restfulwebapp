package com.eeilander.restfulwebservicemm.services;

import java.util.Optional;

import com.eeilander.restfulwebservicemm.entities.NodeEntity;
import com.eeilander.restfulwebservicemm.models.NodeModel;
import com.eeilander.restfulwebservicemm.repositories.NodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService {
    @Autowired
    NodeRepository nodeRepository;


    public NodeModel addSingleNode(NodeEntity node) {
        nodeRepository.save(node);
        return node.toNodeModel();
    }

    public NodeModel findSingleNodeByNodeName(String name) {
        Optional<NodeEntity> nodeEntityMaybe = nodeRepository.findAllByName(name);
        if (nodeEntityMaybe.isPresent()) {
            return nodeEntityMaybe.get().toNodeModel();
        } else {
            throw new Error("No matching node could be found.");
        }
    }

}
