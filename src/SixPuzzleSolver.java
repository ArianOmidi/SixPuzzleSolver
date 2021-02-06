// Code inspired by :
// SG, F. (2019). Solving the 8 puzzle problem using A* (star) algorithm.
// Retrieved 4 February 2021, from https://medium.com/@faramira.sg/solving-the-8-puzzle-problem-using-a-star-algorithm-5cf1db4cdb0f

import java.util.*;

public class SixPuzzleSolver {
    private final State initState, goalState;
    private List<State> visitedList;

    public SixPuzzleSolver(int[] initState, int[] goalState){
        this.initState = new State(initState);
        this.goalState = new State(goalState);
    }


    /* Checkers */

    public boolean isGoalState(State state){
        return state.equals(goalState);
    }


    /* Helper Methods */

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

        System.out.println("Number of Moves: " + (solution.size() - 1) + "\n");
    }


    /* Search Functions */

    public void breathFirstSearch(){
        Queue<State> openQueue = new LinkedList<>();
        visitedList = new ArrayList<>();
        State curState = initState;

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

    public void uniformCostSearch(){
        breathFirstSearch();
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

        for (State child : state.getChildren()) {
            if (!visitedList.contains(child)) {
                depthFirstSearchRec(child);
            }
        }
    }

    public void iterativeDeepeningSearch(int maxDepth){
        for (int i=0; i < maxDepth; i++){
            if (depthLimitedSearch(initState, i)){
                return;
            }
        }

        System.out.println("No Solution");
    }

    private boolean depthLimitedSearch(State state, int limit){
        if (isGoalState(state)) {
            printSolution(state);
            return true;
        }

        if (limit <= 0){
            return false;
        }

        for (State child : state.getChildren()) {
            if (depthLimitedSearch(child, limit - 1)) {
                return true;
            }
        }

        return false;
    }


    /* Main Function */

    public static void main(String[] args) {
        int[] initState = new int[]{1,4,2,5,3,0};
        int[] goalState = new int[]{0,1,2,5,4,3};
        SixPuzzleSolver puzzleSolver = new SixPuzzleSolver(initState, goalState);

        puzzleSolver.breathFirstSearch();
        puzzleSolver.uniformCostSearch();
        puzzleSolver.depthFirstSearch();
        puzzleSolver.iterativeDeepeningSearch(200);
    }



}
