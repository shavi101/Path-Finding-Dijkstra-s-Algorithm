import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Shavindya Kariyawasam
 * @author 16084809/1
 * @author 2015280
 */
public class Path {
	Node[][] nodes;
	int method, length;
	Double totalCost = 0.0;
	Node a, b, currentNode, next;
	PriorityQueue<Node> visitedNodesQ;
	ArrayList<Node> finalPath;
	Comparator<Node> comparator;
	boolean b_reached;
	Double totalDistance;

	public Path() {

	}

	ArrayList<Node> shortestPath(boolean[][] grid, Node a, Node b, int method) throws InterruptedException {
		this.a = a;
		this.b = b;
		this.method = method;

		length = grid.length;
		nodes = new Node[length][length];

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				nodes[i][j] = new Node(i, j, Double.POSITIVE_INFINITY);
				if (!grid[i][j]) {
					nodes[i][j].isBlocked = true;
				}
			}
		}

		// a.s_distance = 0;
		comparator = (leftNode, rightNode) -> {
			if (leftNode.s_distance - (rightNode.s_distance) > 0) {
				return 1;
			} else
				return -1;
		};

		visitedNodesQ = new PriorityQueue<Node>(length, comparator);
		visitedNodesQ.add(a);

		// compare method is implemented at the end of the class
		while (visitedNodesQ.size() > 0) {

			currentNode = visitedNodesQ.poll();

			// UP
			if (currentNode.x - 1 >= 0) {

				// #1-Up
				next = nodes[currentNode.x - 1][currentNode.y];
				compare(currentNode, next, visitedNodesQ, 1.0);
				if (b_reached) {
					break;
				}

				// #2-Left
				if (currentNode.y - 1 > 0) {

					next = nodes[currentNode.x - 1][currentNode.y - 1];

					if (method == 0)
						compare(currentNode, next, visitedNodesQ, 1.4);
					// else if (method == 1)
					// compare(currentNode, next, visitedNodesQ, 2.0);
					else if (method == 2)
						compare(currentNode, next, visitedNodesQ, 1.0);
					if (b_reached) {
						break;
					}
				}

				// #3-right
				if (currentNode.y + 1 < length) {
					next = nodes[currentNode.x - 1][currentNode.y + 1];

					if (method == 0)
						compare(currentNode, next, visitedNodesQ, 1.4);
					// else if (method == 1)
					// compare(currentNode, next, visitedNodesQ, 2.0);
					else if (method == 2)
						compare(currentNode, next, visitedNodesQ, 1.0);
					if (b_reached) {
						break;
					}
				}
			}

			// DOWN-
			if (currentNode.x + 1 < length) {
				// #4-Down
				next = nodes[currentNode.x + 1][currentNode.y];
				compare(currentNode, next, visitedNodesQ, 1.0);
				if (b_reached) {
					break;
				}
				// #5-Left
				if (currentNode.y - 1 >= 0) {
					next = nodes[currentNode.x + 1][currentNode.y - 1];

					if (method == 0)
						compare(currentNode, next, visitedNodesQ, 1.4);
					// else if (method == 1)
					// compare(currentNode, next, visitedNodesQ, 2.0);
					else if (method == 2)
						compare(currentNode, next, visitedNodesQ, 1.0);
					if (b_reached) {
						break;
					}
				}

				// #6-Right
				if (currentNode.y + 1 < length) {
					next = nodes[currentNode.x + 1][currentNode.y + 1];
					if (method == 0)
						compare(currentNode, next, visitedNodesQ, 1.4);
					// else if (method == 1)
					// compare(currentNode, next, visitedNodesQ, 2.0);
					else if (method == 2)
						compare(currentNode, next, visitedNodesQ, 1.0);
					if (b_reached) {
						break;
					}
				}

				currentNode.isVisited = true;
			}

			// RIGHT
			if (currentNode.y + 1 < length) {
				next = nodes[currentNode.x][currentNode.y + 1];
				compare(currentNode, next, visitedNodesQ, 1.0);
				if (b_reached) {
					break;
				}
			}
			// LEFT
			if (currentNode.y - 1 > 0) {
				next = nodes[currentNode.x][currentNode.y - 1];
				compare(currentNode, next, visitedNodesQ, 1.0);
				if (b_reached) {
					break;
				}
			}

		}

		finalPath = new ArrayList<>();

		// Checking if the ending point has been reached
		if ((nodes[b.x][b.y].s_distance != Double.POSITIVE_INFINITY)) {

			// reversing to identify the correct path
			Node current = nodes[b.x][b.y];
			finalPath.add(current);

			while (current.getParent() != null) {
				finalPath.add(current.getParent());
				current = current.getParent();
			}
		} else
			System.out.println("No Path found!");
		if (method == 0)
			System.out.println("Euclidean--------------");
		else if (method == 1)
			System.out.println("Manhatten--------------");
		else
			System.out.println("Chebyshev--------------");
		System.out.println("Total Cost is:" + totalDistance);

		for (Node node : finalPath) {
			if (method == 0)
				StdDraw.setPenColor(Color.YELLOW);
			else if (method == 1)
				StdDraw.setPenColor(Color.CYAN);
			else
				StdDraw.setPenColor(Color.RED);
			StdDraw.filledSquare(node.y, length - node.x - 1, .5);
			// TimeUnit.MILLISECONDS.sleep(200);

		}

		StdDraw.setPenColor(Color.BLACK);
		Collections.reverse(finalPath);// making the path in the correct way
		return finalPath;
	}

	public void compare(Node current, Node next, PriorityQueue<Node> queue, Double cost) {

		totalDistance = current.s_distance + cost;

		if (!next.isBlocked && !next.isVisited && next.s_distance > totalDistance) {
			next.setS_distance(totalDistance);
			next.setParent(current);
			queue.add(next);
			// currentNode.x

			totalCost += cost;
			////
		}
		if (nodes[b.x][b.y].s_distance != Double.POSITIVE_INFINITY) {
			b_reached = true;
		}
	}

	public Double manhatten(Node p1, Node p2) {
		return Math.pow(Math.pow(p1.getX() - p2.getX(), 1.0) + Math.pow(p1.getY() - p2.getY(), 1), 1.0);
	}

	public double euclidean(Node p1, Node p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}

	public double chebyshev(Node p1, Node p2) {
		return Math.max(Math.pow(p1.getX() - p2.getX(), 1), Math.pow(p1.getY() - p2.getY(), 1));
	}

}
	