import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int number; 
	static int V;
	static Node[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for(int test_case = 1; test_case <= T ; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			adjList = new Node[101];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i < V / 2 ; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
			}
			
			bfs(start);

			int max = 0;
			for (int current = 0; current < 101; current++) {
				for (Node temp = adjList[current] ; temp != null ; temp = temp.next) {
					 
					// do something
					if(temp.breadth == number) {
						max = Math.max(temp.data, max);
					}
				}	
			}
			System.out.println("#"+test_case+ " " +max);
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		
		queue.offer(start);
		visited[start] = true;
		int breadth = 0;
		
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			while(--qSize >= 0) {
				int current = queue.poll();
				for (Node temp = adjList[current] ; temp != null ; temp = temp.next) {
					if(adjList[current] != null && !visited[temp.data]) {
						temp.breadth = breadth + 1;
						queue.offer(temp.data);
						visited[temp.data] = true;
					}
				}
			}
			breadth++;
		}
		number = breadth - 1;
	}
	
	static class Node {
		int data;
		int breadth;
		Node next;
		
		public Node(int data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
	}
}
