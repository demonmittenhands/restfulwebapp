# Restful WebApp
A Restful Webapp using Spring/SpringBoot and H2 in memory database. This app will allow you to create root and child nodes, delete nodes, and get nodes with ancestry, or get nodes at a given level.


# Endpoints
all have base context of 'localhost:8080/api'
GET: /{nodeName}     
    - Returns the node and all direct anscestors

GET: /{nodeName}/level/{level}
    - Returns the descendant nodes on level {level} for a given node  

POST: /
    body: {name: "<new node name>"}
    - Creates a new base node, as the parent node wasn't supplied in the URL

POST: /{nodeName}
    body: {names: ["<new node name>"]}
    - Creates new child nodes for the the given parent node

DELETE: /{nodeName}
    - Deletes the given node and all descendants

H2 database on 'localhost:8080/api/h2'. username: 'sa'. password: 'password'


# Questions/Assumptions
- How big can a node name be? (assumed 250 characters)
Can it contain all foreign characters? 
The "Provide basic input validation and return meaningful errors" requirement. 
    What constitues a valid input? I'm only checking for duplicate nodes, but I'd want some more info there.

Could child nodes have the same name as parent nodes? 

Couldn't get GSON or JSON libraries to play nice with my controller so I'm doing some not great casting to HashMaps. 
It works and I'm sure I'm missing an import or configuration for VS code

Lombok won't work inside of tests, so even though I've used @Setter/@Getter/@Data I'm stuck writing getters/setters myself anyway.



# Stuff I would change:

- both POST requests take different parameter lists. one is "name" one is "names"[]. 
  I don't think it would have been a lot of effort to make it use the same, but lack of time
    
- requests aren't using JSON or GSON. I just couldn't get it to cooperate inside VSCode, so I'm doing this not superb and unchecked casting with HashMaps.

- nice build in JPA methods like '<repository>.findAllByName()' won't work through VSCode by default. 
  Not a huge issue since they only need to be added to the repository interface anyway 'Set<NodeEntity> findAllByName(String name)' but it slows everything down a little.

- didn't pay much attention to Set/List/Array when starting out and it's cost some time doins some conversions. JPA might do better with Sets, but I tend to use Lists.

- I was using an entity for data passed in and a model for data coming back out, but there were some conflicts (mostly with persisted child/parent relationship) so I abandonded that.

- Test converage is pretty low. Only one mockMVC test but I'd want to resolve my JSON issues before doing more.

- Ran out of time before getting logging or much input validation working. 

