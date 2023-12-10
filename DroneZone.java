import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DroneZone {
static int[][] grid;
    //Driver method
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        System.out.println("Enter Grid dimensions ");
        int x= s.nextInt();
        int y= s.nextInt();
        initialiseGrid(x,y);
        initialiseDrones();
        System.out.println("Enter Target Coordinates ");
        int targetX= s.nextInt();
        int targetY= s.nextInt();
        for(int i=1;i<=4;i++){
            findTarget(targetX,targetY,i);
        }
    }
//  Initialising 2D matrix with value 0
static void initialiseGrid(int x, int y){
    grid= new int[x][y];
    for(int i=0;i<x;i++){
        for(int j=0;j<y;j++){
            grid[i][j]=0;
        }
    }
}
//  Initial Drone positions
static void initialiseDrones(){
    Scanner s= new Scanner(System.in);
    for(int i=1;i<=4;i++){
        int x,y;
        do {
            System.out.println("Enter coordinates for drone " + i);
            x = s.nextInt();
            y = s.nextInt();
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                System.out.println("Coordinates out of bounds. Enter valid coordinates ");
            }
        }while (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) ;
                grid[x][y] = i;
        }
    }

//BFS traversal
    static void findTarget(int targetX, int targetY, int drone){
//    For moving in right, left, up, down directions
    int []dx= {1,-1,0,0};
    int []dy= {0,0,1,-1};
//  To keep track of previous path coordinates
    int [][]prevX= new int[grid.length][grid[0].length];
    int [][]prevY= new int[grid.length][grid[0].length];
//  To keep track of visited coordinates
    boolean[][] visited= new boolean[grid.length][grid[0].length];
//    Queue DS used for BFS traversal
    Queue<int[]> queue= new LinkedList<>();

    for(int i=0;i< grid.length;i++){
        for(int j=0;j<grid[0].length;j++){
            prevX[i][j]= -1;
            prevY[i][j]=-1;
        }
    }
//    If drone coordinates found, add them to queue
    for(int row=0; row<grid.length;row++){
        for(int col=0;col<grid[0].length;col++){
            if(grid[row][col]==drone){
                queue.offer(new int[]{row,col});
                visited[row][col]= true;
            }
        }
    }
//    Find path
    int steps=0;
    while(!queue.isEmpty()){
        int size= queue.size();
        for(int i=0;i<size;i++){
            int []curr= queue.poll();
            int x= curr[0];
            int y= curr[1];

            if(x== targetX && y==targetY){
                System.out.println("Shortest path taken by Drone "+drone+" to reach target ");
                printPath(prevX,prevY,targetX,targetY);
                System.out.println("Steps "+ steps);
                return;
            }
            for(int dir=0; dir<4;dir++){
                int newX= x+ dx[dir];
                int newY= y+ dy[dir];
                if(newX>=0 && newX< grid.length && newY>=0 && newY<grid[0].length
                && !visited[newX][newY] && grid[newX][newY]==0){
                    queue.offer(new int[]{newX,newY});
                    visited[newX][newY]= true;
                    prevX[newX][newY]= x;
                    prevY[newX][newY]=y;
                }
            }
        }
        steps++;
    }
            }
    //prints the shortest path to the target
    static  void printPath(int[][] prevX, int[][] prevY, int x, int y){
        if(x==-1 || y==-1){
            return;
        }
        printPath(prevX,prevY, prevX[x][y], prevY[x][y]);
        System.out.print("["+x+","+y+"]");
    }
}
//        int x=11;
//        int y= 14;
//        int targetX= 4;
//        int targetY= 11;
//    grid[1][1]= 1;
//    grid[7][3]= 2;
//    grid[7][9]= 3;
//    grid[10][4]= 4;






