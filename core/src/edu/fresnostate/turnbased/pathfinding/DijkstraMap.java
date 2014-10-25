package edu.fresnostate.turnbased.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public abstract class DijkstraMap
{
	private static Comparator <NodeRecord>	comp	=
															new Comparator <NodeRecord> ()
															{
																@Override
																public
																		int
																		compare (
																				NodeRecord o1,
																				NodeRecord o2)
																{
																	return o1.costSoFar < o2.costSoFar
																			? - 1
																			: (o1.costSoFar == o2.costSoFar
																					? 0
																					: 1);
																}
															};

	public static List <List <Integer>> createMap (Graph graph, int start,
			int maxCost)
	{
		NodeRecord startRecord = new NodeRecord (start, null, 0);

		Queue <NodeRecord> open = new PriorityQueue <NodeRecord> (16, comp);
		Queue <NodeRecord> closed = new LinkedList <NodeRecord> ();

		open.add (startRecord);

		while ( ! open.isEmpty ())
		{
			NodeRecord current = open.poll ();

			Connection [ ] connections = graph.getConnections (current.node);

			for (Connection connection : connections)
			{
				int endNode = connection.toNode;
				int endNodeCost = current.costSoFar + connection.cost;

				if (endNodeCost > maxCost)
				{
					continue;
				}

				if (findNode (closed, endNode) == null)
				{
					NodeRecord endNodeRecord = findNode (open, endNode);

					if (endNodeRecord == null)
					{
						endNodeRecord =
								new NodeRecord (endNode, connection,
										endNodeCost);
					}
					else if (endNodeRecord.costSoFar > endNodeCost)
					{
						open.remove (endNodeRecord);
						endNodeRecord.connection = connection;
						endNodeRecord.costSoFar = endNodeCost;
					}
					else
					{
						continue;
					}

					open.add (endNodeRecord);
				}
			}
		}

		List <List <Integer>> paths = new ArrayList <List <Integer>> ();
		for (NodeRecord record : closed)
		{
			List <Integer> path = new ArrayList <Integer> ();
			NodeRecord currentRecord = record;
			int currentNode = record.node;
			while (currentNode != start)
			{
				path.add (currentNode);
				currentNode = currentRecord.node;
				currentRecord = findNode (closed, currentNode);
			}
			Collections.reverse (path);
			paths.add (path);
		}

		return paths;
	}

	private static NodeRecord findNode (final Queue <NodeRecord> list,
			final int node)
	{
		for (NodeRecord record : list)
		{
			if (record.node == node)
			{
				return record;
			}
		}
		return null;
	}
}
