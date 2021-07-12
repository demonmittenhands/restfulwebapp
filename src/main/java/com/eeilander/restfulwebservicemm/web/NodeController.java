package com.eeilander.restfulwebservicemm.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    
    /**
    * This request will add a root node as no {parentID} is added in the request
    * @param node the name of the node to add
    * @return the node that was created
    */
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, 
                    consumes=MediaType.APPLICATION_JSON_VALUE)
    public NodeModel addParentNode(@RequestBody HashMap<String, String> body) {
        String name = "";
        if (body.containsKey("name")) {
            name = body.get("name");
        } else {
            // good spot for logging or giving good input validation text;
        }

        NodeEntity node = new NodeEntity();
        node.setName(name);
        return nodeService.addSingleNode(node);
    }

    /**
     * This request will add child nodes to the specified parent node
     * @param names the names of the nodes to add
     * @param parentNode the name of the parent node to add the child nodes to
     * @return the node that was created
     */
    @PostMapping(path = "/{nodeName}",
                    produces=MediaType.APPLICATION_JSON_VALUE, 
                    consumes=MediaType.APPLICATION_JSON_VALUE)
    public NodeModel addNode(@PathVariable("nodeName") String nodeName, 
                                @RequestBody HashMap<String, Object> body) {
        // check the request
        List<String> names = new ArrayList<>();
        if (body.containsKey("names")) {
            names = (ArrayList<String>)body.get("names");
        } else {
            // good spot for logging or giving good input validation text;
        }


        // first check that the parent node is valid
        NodeModel parentNode = nodeService.findSingleNodeByNodeName(nodeName);
        // add each new name to the parent node
        names.stream()
            .distinct()
            .map(name -> {NodeEntity n = new NodeEntity();
                            n.setName(name);
                            n.setParentId(parentNode.getId());
                            return n;
                        })
            .forEach(node -> nodeService.addSingleNode(node));                     

        // return the collection of new nodes
        return nodeService.findSingleNodeByNodeName(parentNode.getName());
    }

    /***
     * Returns the node and associated child nodes for a given node
     * @param nodeName the node we're looking for
     * @return  the node and the child nodes at this level
     */
    @GetMapping(path="/{nodeName}", produces=MediaType.APPLICATION_JSON_VALUE)
    public NodeModel getNode(@PathVariable("nodeName") String name) {
        return nodeService.findSingleNodeByNodeName(name);
    }


}