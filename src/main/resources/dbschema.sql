DROP TABLE IF EXISTS node;


-- id, parent_node_id (nullable, foreign key of another id), node_name 
-- 
CREATE TABLE node (
    PRIMARY KEY id INT AUTO_INCREMENT,
    node_name VARCHAR(250) NOT NULL,
    parent_node_id INT,
    FOREIGN KEY (parent_node_id) REFERENCES node(id) ON DELETE CASCADE,
    UNIQUE KEY tree_name (name)
);

GO;

INSERT INTO node ()
VALUES ("Products", NULL)
GO;

INSERT INTO node ()
VALUES ("Electronics", (SELECT id FROM node WHERE node_name LIKE '%Product%'))
GO;

INSERT INTO node ()
VALUES ("Palm 505", (SELECT id FROM node WHERE node_name LIKE '%Electronics%')),
("TV", (SELECT id FROM node WHERE node_name LIKE '%Electronics%'))
GO;