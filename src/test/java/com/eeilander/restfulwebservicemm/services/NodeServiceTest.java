package com.eeilander.restfulwebservicemm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.eeilander.restfulwebservicemm.*;
import com.eeilander.restfulwebservicemm.entities.NodeEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;



@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
// @ComponentScan("com.eeilander")
// @AutoConfigureMockMvc
// @SpringJUnitConfig
// @ContextConfiguration(classes = {NodeService.class})
// @AutoConfigureMockMvc
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

}
