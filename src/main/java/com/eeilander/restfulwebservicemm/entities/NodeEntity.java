package com.eeilander.restfulwebservicemm.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.eeilander.restfulwebservicemm.models.NodeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="parent_node_id", nullable = true)
    private NodeEntity parentNode;
    
    @JsonIgnore
    @OneToMany(mappedBy="parentNode")
    private Set<NodeEntity> childNodes = new HashSet<NodeEntity>();
    


    public void setParentNode(NodeEntity parentNode) {
        this.parentNode = parentNode;
    }
    
    public Set<NodeEntity> getChildNodes() {
        return null;
    }

    public NodeModel toNodeModel() {
        var nodeModel = new NodeModel();
        nodeModel.id = this.id;
        nodeModel.name = this.name;
        // nodeModel.parentNodeId = this.parentNodeId;
        return nodeModel;
    }

    public void setNodeName(String name) {
        this.name = name;
    }

}
