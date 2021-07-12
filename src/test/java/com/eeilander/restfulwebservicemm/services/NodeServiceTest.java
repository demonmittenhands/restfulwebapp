package com.eeilander.restfulwebservicemm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.eeilander.restfulwebservicemm.entities.NodeEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class NodeServiceTest {
    @Autowired
    NodeService nodeService;

    @Test
    public void canAddAndReturnASingleNode() {
        String testNodeName = "TestNode";
        NodeEntity testNode = new NodeEntity();
        testNode.setNodeName(testNodeName);

        nodeService.addSingleNode(testNode);
        
        assertEquals(testNodeName, nodeService.findSingleNodeByNodeName(testNodeName).getName());
    }

    @Test
    public void returnsCorrectNodesForAGivenLevel() {
        String parentNodeName = "parentNode";
        NodeEntity parentNode = new NodeEntity();
        parentNode.setNodeName(parentNodeName);
        nodeService.addSingleNode(parentNode);

        

        String childNodeName = "childNode";
        NodeEntity childNode = new NodeEntity();
        childNode.setParentNode(nodeService.findSingleNodeByNodeName(parentNodeName));
        childNode.setNodeName(childNodeName);
        nodeService.addSingleNode(childNode);

        assertEquals(childNodeName, nodeService.findSingleNodeByNodeName(childNodeName).getName());

        assert(nodeService.findNodesForALevel(parentNodeName, 1).contains(childNode));
    }

}
