public class SixPuzzle {

    private State initState, goalState;
    private State curState, prevState;

    public SixPuzzle(int[] initState, int[] goalState){
        this.initState = this.curState = this.prevState = new State(initState);
        this.goalState = new State(goalState);
    }

    public boolean isGoal(){
        return curState.equals(goalState);
    }

//    public getChildren()



}
