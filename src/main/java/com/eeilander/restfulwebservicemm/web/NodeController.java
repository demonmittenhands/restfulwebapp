package com.eeilander.restfulwebservicemm.web;

import com.eeilander.restfulwebservicemm.entities.NodeEntity;
import com.eeilander.restfulwebservicemm.models.NodeModel;
import com.eeilander.restfulwebservicemm.services.NodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/node")
public class NodeController {

    @Autowired
    NodeService nodeService;

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, 
                    consumes=MediaType.APPLICATION_JSON_VALUE)
    public NodeModel addNode(@RequestBody NodeEntity node) {
        return nodeService.addSingleNode(node);
    }

    @GetMapping(path="/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
    public NodeModel getNode(@PathVariable("name") String name) {
        return nodeService.findSingleNodeByNodeName(name);
    }

}