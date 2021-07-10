package com.eeilander.restfulwebservicemm.entities;
import javax.persistence.*;

import com.eeilander.restfulwebservicemm.models.NodeModel;

import lombok.Data;

@Entity
@Table (name = "node")
@Data
public class NodeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="node_name", length=250, nullable=false, unique=true)
    private String name;
    
    @Column(name="parent_node_id", nullable=true)
    private int parentNodeId;
    

    public NodeModel toNodeModel() {
        var nodeModel = new NodeModel();
        nodeModel.name = this.name;
        nodeModel.parentNodeId = this.parentNodeId;
        return nodeModel;
    }

    // SpringBootTest just won't acknowledge lombok so I'm stuck making these kinds of funcitons :(
    public void setNodeName(String name) {
        this.name = name;
    }

    public void setParentId(int id) {
        this.parentNodeId = id;
    }
}
