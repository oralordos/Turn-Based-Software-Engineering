package edu.fresnostate.turnbased.pathfinding;

public class NodeRecord
{
	public final int	node;
	public Connection	connection;
	public int			costSoFar;

	public NodeRecord (int node, Connection connection, int costSoFar)
	{
		this.node = node;
		this.connection = connection;
		this.costSoFar = costSoFar;
	}
}
