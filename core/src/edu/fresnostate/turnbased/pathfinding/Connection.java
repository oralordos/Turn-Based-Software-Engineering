package edu.fresnostate.turnbased.pathfinding;

public class Connection
{
	public final int	cost;
	public final int	fromNode;
	public final int	toNode;

	public Connection (int cost, int fromNode, int toNode)
	{
		this.cost = cost;
		this.fromNode = fromNode;
		this.toNode = toNode;
	}
}
