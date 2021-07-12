package com.eeilander.restfulwebservicemm.models;

import java.util.List;

import lombok.Data;

@Data
public class NodeModel {
    public int parentNodeId;
    public int id;
    public String name;
    public List<NodeModel> childNodes;

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setChildNodes(List<NodeModel> childNodes) {
        this.childNodes = childNodes;
    }
}
