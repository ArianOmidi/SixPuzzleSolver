// Code inspired by :
// SG, F. (2019). Solving the 8 puzzle problem using A* (star) algorithm.
// Retrieved 4 February 2021, from https://medium.com/@faramira.sg/solving-the-8-puzzle-problem-using-a-star-algorithm-5cf1db4cdb0f

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private State parent;
    private int emptyIndex;
    private int[] state;

    public State(int[] state){
        this(state, null);
    }

    public State(int[] state, State parent){
        this.parent = parent;
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

    public State getParent() {
        return parent;
    }

    private int[] getNeighbors() {
        switch (emptyIndex){
            case 0:
                return new int[]{1,3};
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
            State child = new State(Arrays.copyOf(this.state, this.state.length), this);
            child.swapValues(neighbors[i]);

            for (int j = 0; j <= children.size(); j++){
                if (j == children.size()){
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


    /* Functions */

    public void swapValues(int index){
        state[emptyIndex] = state[index];
        state[index] = 0;
        emptyIndex = index;
    }

    public void print(){
        for (int i = 0; i < state.length; i++){
            if (i == 3)
                System.out.print("\n");
            System.out.print(" " + state[i] + " ");
        }
        System.out.print("\n\n");
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;

        if (!(o instanceof State))
            return false;

        for (int i = 0; i < 6; i++){
            if (((State) o).getState()[i] != this.state[i]){
                return false;
            }
        }
        return true;
    }

}
