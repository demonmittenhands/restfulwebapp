package com.eeilander.restfulwebservicemm.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.eeilander.restfulwebservicemm.entities.NodeEntity;
import com.eeilander.restfulwebservicemm.models.NodeModel;
import com.eeilander.restfulwebservicemm.repositories.NodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.description.type.TypeList.Generic.ForDetachedTypes;

@Service
public class NodeService {
    @Autowired
    NodeRepository nodeRepository;


    public NodeEntity addSingleNode(NodeEntity node) {
        nodeRepository.save(node);
        return node;
    }

    public NodeEntity findSingleNodeByNodeName(String name) {
        Optional<NodeEntity> nodeEntityMaybe = nodeRepository.findAllByName(name);
        if (nodeEntityMaybe.isPresent()) {
            NodeEntity node = nodeEntityMaybe.get();
            // List<NodeEntity> childNodes = nodeRepository.findChildNodesByParentNodeId(node.getId()).get().stream()
            //                                                                         // .map(thisNode -> thisNode.toNodeModel())
            //                                                                         .collect(Collectors.toList());
            // node.setChildNodes(childNodes);
            return node;
        } else {
            throw new Error("No matching node could be found.");
        }
    }

    @Transactional
    public void deleteNodeByNodeName(String name) {
        nodeRepository.deleteAllByName(name);
    }

    public List<NodeEntity> findNodesForALevel(String name, int level) {
        // check for valid number

        // nodeRepository.findAllByParentNodeByParentNode(name);
        NodeEntity parentNode = nodeRepository.findAllByName(name).get();
        
        // Assuming level 0 == the same node
        if (level == 0) {
            return Stream.of(parentNode).collect(Collectors.toList());
        }

        Set<NodeEntity> currentLevelNodes = Stream.of(parentNode).collect(Collectors.toSet());
        for (int i = 0; i < level; i++) {
            Set<NodeEntity> tempChildNodes = new HashSet<>();
            for (NodeEntity entity : currentLevelNodes) {
                // check getChildNodes for null
                Set<NodeEntity> theseNodes = nodeRepository.findAllByParentNodeId(entity.getId()).get();
                tempChildNodes.addAll(theseNodes);
            }
            currentLevelNodes = tempChildNodes;
        }


        return new ArrayList<>(currentLevelNodes);
    }

}
