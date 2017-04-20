/**
 * @author Shavindya Kariyawasam
 */

public class Node {

	int x, y;
	double s_distance, cost;
	Node parent = null;
	boolean isVisited, isBlocked;

	public Node(int x, int y, double s_distance) {
		this.x = x;
		this.y = y;
		this.s_distance = s_distance;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// --------------------------------------------------------------------------------
	public double getS_distance() {
		return s_distance;
	}

	public void setS_distance(double s_distance) {
		this.s_distance = s_distance;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
