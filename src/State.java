import java.util.List;

public class State {

    private int emptyPos;
    private int[] state;

    public State(int[] state){
        this.state = state;

        for (int i = 0; i < this.state.length; i++){
            if (this.state[i] == 0){
                emptyPos = i;
                break;
            }
        }
    }

    public State(int[][] state){
        int[] arr = new int[6];

        for (int i = 0; i < 6; i++){
            arr[i] = state[i % 2][i % 3];
        }

        new State(arr);
    }

    public int getEmptyPos() {
        return emptyPos;
    }

    public int[] getState() {
        return state;
    }

    public int[] getNeighbors() {
        switch (emptyPos){
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

    public boolean equals(State state1){
        for (int i = 0; i < 6; i++){
            if (state1.getState()[i] != this.state[i]){
                return false;
            }
        }
        return true;
    }

}
