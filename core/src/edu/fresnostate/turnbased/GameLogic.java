package edu.fresnostate.turnbased;

public class GameLogic implements EventListener
{
	public GameLogic ()
	{
		EventManager manager = EventManager.get ();
		manager.addListener (this, EventType.CREATE_UNIT);
	}

	@Override
	public void receiveEvent (Event e)
	{
		switch (e.getEventType ())
		{
		case CREATE_UNIT :
			handleCreateUnit ((CreateUnitEvent) e);
			break;
		case ATTACK_UNIT : 
			handleAttackUnit ((AttackUnitEvent) e);
			break;
		default :
			break;
		}
	}

	private void handleAttackUnit (AttackUnitEvent e)
	{
		// TODO Make sure the attacker's player is the current player
		Unit attacker = getUnit(e.attackerID);
		if(attacker.isInRange(e.targetID))
		{
		
			attacker.attack(e.targetID);
			EventManager.get ().queueEvent (new UnitAttacked(e.attackerID, e.targetID));
		}
	}

	private Unit getUnit (int unitID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private void handleCreateUnit (CreateUnitEvent e)
	{
		// TODO Auto-generated method stub
	}
}
