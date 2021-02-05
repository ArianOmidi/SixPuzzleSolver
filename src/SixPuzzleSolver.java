import java.util.*;

public class SixPuzzleSolver {

    private State initState, goalState;
    private Queue<State> openQueue;
    private List<State> visitedList;

    public SixPuzzleSolver(int[] initState, int[] goalState){
        this.initState = new State(initState);
        this.goalState = new State(goalState);

        this.openQueue = new LinkedList<>();
        this.visitedList = new ArrayList<>();
    }

    public boolean isGoalState(State state){
        return state.equals(goalState);
    }

    public State getInitState() {
        return initState;
    }

    public State getGoalState() {
        return goalState;
    }


    public void printSolution(State goalState){
        List<State> solution = new ArrayList<>();
        State curState = goalState;

        while(curState != null){
            solution.add(0, curState);
            curState = curState.getParent();
        }

        for (State state: solution){
            state.print();
        }

        System.out.println("Depth: " + (solution.size() - 1));
    }



    public void breathFirstSearch(){
        State curState = initState;
        visitedList = new ArrayList<>();
        openQueue = new LinkedList<>();

        visitedList.add(initState);
        openQueue.add(curState);


        while (openQueue.size() != 0){
            curState = openQueue.poll();

            if (isGoalState(curState)){
                printSolution(curState);
                return;
            }

            List<State> children = curState.getChildren();

            for (int i = children.size() - 1; i >= 0; i--){
                State child = children.get(i);

                if(!visitedList.contains(child)){
                    visitedList.add(child);
                    openQueue.add(child);
                }
            }
        }
        System.out.println("No Solution");
    }

    public void depthFirstSearch(){
        visitedList = new ArrayList<>();

        depthFirstSearchRec(initState);
    }

    private void depthFirstSearchRec(State state){
        visitedList.add(state);

        if (isGoalState(state)){
            printSolution(state);
            return;
        }

        List<State> children = state.getChildren();
        for (State child : state.getChildren()) {
            if (!visitedList.contains(child)) {
                depthFirstSearchRec(child);
            }
        }
    }


    public static void main(String[] args) {
        int[] initState = new int[]{1,4,2,5,3,0};
        int[] goalState = new int[]{0,1,2,5,4,3};
        SixPuzzleSolver sp = new SixPuzzleSolver(initState, goalState);

//        sp.breathFirstSearch();
        sp.depthFirstSearch();
    }



}
