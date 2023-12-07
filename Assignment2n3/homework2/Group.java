package homework2;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;


public  class Group extends DefaultMutableTreeNode implements Component {

    private String groupID;  // Unique identifier for the group
    private ArrayList<Component> componentList = new ArrayList<>(); //To store the user and group nodes
    private long creationTime; //To store the group creation time

    // Constructor
    public Group(String groupID) {
        super(groupID);
        this.groupID = groupID;

    }

    public ArrayList<Component> getComponentList() {
        return componentList;
    }
    public void setList(ArrayList<Component> componentArrayList) {
        this.componentList = componentArrayList;
    }

    // Accept a visitor and applies the visit methods, then accept the visitor for each component in the list
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
       for(Component C : componentList) {
           C.accept(visitor);
       }

    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }


}