import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private State state;

    public Node(Node parent, State state){
        this.parent = parent;
        this.state = state;
    }

    public Node(State state){
        this(null, state);
    }

    public List<Node> getChildren(){
        ArrayList<Node> children = new ArrayList<>();

        for (State childState: state.getChildren()) {
            children.add(new Node(this, childState));
        }

        return children;
    }


}
