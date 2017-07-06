/************
 * Author: Michael "Mike" Ivan
 * Date: 7/5/17
 * GitHub: https://github.com/mikeivan
 ************/
 
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;


public class MazeSolver {

	private static int start_x;
	private static int start_y;
	private static char[][] maze;
	private static boolean[][] visited;
	
	public static void main(String[] args) {
		
		if(args.length != 1) {
			usage();
			return;
		}
		
		readFile(args[0]);
		solveMaze();
		//printAnswer();
		
	}
	
	public static void usage() {
		System.out.println("Usage: MazeSolver \"filename\"");
	}
	
	//reads in passed in file and initializes maze and visisted
	public static void readFile(String inFile) {
		
		try {
			
			URL path = MazeSolver.class.getResource(inFile);
			
			FileReader fr = new FileReader(path.getPath());
			BufferedReader br = new BufferedReader(fr);
			
			String inString = "";
			int width = 0;
			int height = 0;
			int line_number = 0;
			char temp;
			
			height = Integer.parseInt(br.readLine());
			width = Integer.parseInt(br.readLine());
			
			System.out.println("Maze dimensions: " + height + " x " + width);
			
			maze = new char[height][width];
			initVisited(height, width);
			
			while((inString = br.readLine()) != null) {
				//read in maze line by line
				//find start point
				
				//might want to read based on width parameter instead
				for(int i = 0; i < inString.length(); i++) {
					temp = inString.charAt(i);
					
					maze[line_number][i] = temp;
					
					if(temp == 'S') {
						start_x = line_number;
						start_y = i;
					}
				}
				line_number++;
			}
			
			System.out.println("Starting point: " + start_x + ", " + start_y);
			printMaze();
			br.close();
			
		} catch(Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
		
	}
	
	public static void initVisited(int height, int width) {
		visited = new boolean[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void solveMaze() {
		//Start at start coords, push neighbors, mark visited
		LinkedList<Entry<Integer, Integer>> queue = new LinkedList<Entry<Integer, Integer>>();
		
		Entry<Integer, Integer> current;
		ArrayList<Entry<Integer, Integer>> neighbors;
		
		queue.push(new SimpleEntry<Integer, Integer>(start_x, start_y));
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			System.out.println(current.getKey() + ", " + current.getValue());
			visited[current.getKey()][current.getValue()] = true;
			
			if(maze[current.getKey()][current.getValue()] == 'E') {
				return;
			}
			
			neighbors = findNeighbors(current);
			
			System.out.print("valid moves for: " + current.getKey() + ", " + current.getValue() + ": ");
			for(int i = 0; i < neighbors.size(); i++) {
				queue.push(neighbors.get(i));
				System.out.print("(" + neighbors.get(i).getKey() + ", " + neighbors.get(i).getValue() + ") ");
			}
			System.out.println();
			
		}
		
	}
	
	//finds all available moves and checks if moved there before
	public static ArrayList<Entry<Integer, Integer>> findNeighbors(Entry<Integer, Integer> root) {
		ArrayList<Entry<Integer, Integer>> ret = new ArrayList<Entry<Integer, Integer>>();
		
		//Check north move
		if(checkValidMove(root.getKey()-1, root.getValue())) {
					ret.add(new SimpleEntry<Integer, Integer>(root.getKey()-1, root.getValue()));
		}

		//Check east move
		if(checkValidMove(root.getKey(), root.getValue()+1)) {
					ret.add(new SimpleEntry<Integer, Integer>(root.getKey(), root.getValue()+1));
		}
		
		//Check south move
		if(checkValidMove(root.getKey()+1, root.getValue())) {
					ret.add(new SimpleEntry<Integer, Integer>(root.getKey()+1, root.getValue()));
		}
		
		//Check west move
		if(checkValidMove(root.getKey(), root.getValue()-1)) {
					ret.add(new SimpleEntry<Integer, Integer>(root.getKey(), root.getValue()-1));
		}
			
		
		
		return ret;
		
	}
	
	public static boolean checkValidMove(int height, int width) {
		
		//System.out.println("checking: " + height + ", " + width);
		//check if out of bounds first
		if( height < 0 || height >= maze.length ) {
			return false;
		}
		
		if( width < 0 || width >= maze[0].length ) {
			return false;
		}
		
		if( visited[height][width] == true ) {
			return false;
		}
		
		if( maze[height][width] == ' ' || maze[height][width] == 'E' ) {
			return true;
		}
		
		return false;
	}
	
	//maze.length gives the height
	//maze[0].length gives the width
	public static void printMaze() {
		
		if(maze.length > 0) {
			System.out.println();
			
			for(int i = 0; i < maze.length; i++) {
				for(int j = 0; j < maze[0].length; j++) {
					System.out.print(maze[i][j]);
				}
				System.out.println();
			}
		}
		
	}
	
	public static void printAnswer() {
		
	}
}
