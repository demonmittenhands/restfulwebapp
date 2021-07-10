package com.eeilander.restfulwebservicemm.models;

import lombok.Data;

@Data
public class NodeModel {
    public int parentNodeId;
    public String name;

    public String getName() {
        return name;
    }
}
