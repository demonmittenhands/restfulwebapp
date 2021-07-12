package com.eeilander.restfulwebservicemm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.eeilander.restfulwebservicemm.entities.NodeEntity;
import com.eeilander.restfulwebservicemm.services.NodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public NodeEntity addParentNode(@RequestBody HashMap<String, String> body) {
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
     * @param body request body should contain "names": ["name1"...] for names of the nodes to add
     * @param parentNode the name of the parent node to add the child nodes to
     * @return the node that was created
     */
    @PostMapping(path = "/{nodeName}",
                    produces=MediaType.APPLICATION_JSON_VALUE, 
                    consumes=MediaType.APPLICATION_JSON_VALUE)
    public NodeEntity addNode(@PathVariable("nodeName") String nodeName, 
                                @RequestBody HashMap<String, Object> body) {
        // check the request
        List<String> names = new ArrayList<>();
        if (body.containsKey("names")) {
            names = (ArrayList<String>)body.get("names");
        } else {
            // good spot for logging or giving good input validation text;
        }


        // first check that the parent node is valid
        NodeEntity parentNode = nodeService.findSingleNodeByNodeName(nodeName);
        // add the parent node to the new nodes
        names.stream()
            .distinct()
            .map(name -> {NodeEntity n = new NodeEntity();
                            n.setName(name);
                            n.setParentNode(parentNode);
                            return n;
                        })
            .forEach(node -> nodeService.addSingleNode(node));                     

        // return the collection of new nodes
        return nodeService.findSingleNodeByNodeName(parentNode.getName());
    }

    /***
     * Returns the node and associated child nodes for a given node
     * @param nodeName the node we're looking for
     * @return  the node and the anscestor nodes
     */
    @GetMapping(path="/{nodeName}", produces=MediaType.APPLICATION_JSON_VALUE)
    public NodeEntity getNode(@PathVariable("nodeName") String name) {
        return nodeService.findSingleNodeByNodeName(name);
    }

    /***
     * Returns the nodes at a given level for a specified node
     * @param nodeName the node we're looking for
     * @param level the level we want to find nodes for
     * @return  the node and the child nodes at this level
     */
    @GetMapping(path="/{nodeName}/level/{level}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<NodeEntity> getNodesAtLevel(@PathVariable("nodeName") String name,
                                        @PathVariable("level") int level) {
        return nodeService.findNodesForALevel(name, level);
    }


    @DeleteMapping(path="/{nodeName}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteNode(@PathVariable("nodeName") String name) {
        nodeService.deleteNodeByNodeName(name);
        return ResponseEntity.noContent().build();
    }

}