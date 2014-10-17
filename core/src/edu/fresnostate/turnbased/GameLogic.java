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
		// TODO Auto-generated method stub
		
	}

	private void handleCreateUnit (CreateUnitEvent e)
	{
		// TODO Auto-generated method stub
	}
}
