import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class State implements Comparable<State> {

    private int emptyIndex;
    private int[] state;

    public State(int[] state){
        this.state = state;

        for (int i = 0; i < this.state.length; i++){
            if (this.state[i] == 0){
                this.emptyIndex = i;
                break;
            }
        }
    }

    /* Getters */

    public int getEmptyIndex() {
        return emptyIndex;
    }

    public int[] getState() {
        return state;
    }

    private int[] getNeighbors() {
        switch (emptyIndex){
            case 0:
                return new int[]{1,4};
            case 1:
                return new int[]{0,2,4};
            case 2:
                return new int[]{1,5};
            case 3:
                return new int[]{0,4};
            case 4:
                return new int[]{1,3,5};
            case 5:
                return new int[]{2,4};
            default:
                return new int[]{};
        }
    }

    public List<State> getChildren(){
        int[] neighbors = getNeighbors();
        ArrayList<State> children = new ArrayList<>();

        for (int i = 0; i < neighbors.length; i++){
            State child = new State(this.state);
            child.swapValues(neighbors[i]);

            for (int j = 0; j <= children.size(); j++){
                if (i == children.size()){
                    children.add(child);
                    break;
                } else if (children.get(j).getState()[emptyIndex] > child.getState()[emptyIndex]){
                    children.add(j, child);
                    break;
                }
            }
        }

        return children;
    }

    public void swapValues(int index){
        int tmp = state[index];

        state[emptyIndex] = state[index];
        state[index] = 0;
    }

    public boolean equals(State state1){
        for (int i = 0; i < 6; i++){
            if (state1.getState()[i] != this.state[i]){
                return false;
            }
        }
        return true;
    }

}
