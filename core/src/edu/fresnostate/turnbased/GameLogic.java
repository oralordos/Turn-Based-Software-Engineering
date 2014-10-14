package edu.fresnostate.turnbased;

public class GameLogic implements EventListener
{
	public GameLogic ()
	{
		EventManager manager = EventManager.get ();
		manager.addListener (this, EventType.UNIT_DESTROYED);
		manager.addListener (this, EventType.CREATE_UNIT);
		manager.addListener (this, EventType.CURRENT_PLAYER_CHANGED);
	}

	@Override
	public void receiveEvent (Event e)
	{
		switch (e.getEventType ())
		{
		case UNIT_DESTROYED :
			handleDestroyUnit ((UnitDestroyedEvent) e);
			break;
		case CREATE_UNIT :
			handleCreateUnit ((CreateUnitEvent) e);
			break;
		case CURRENT_PLAYER_CHANGED :
			handleChangeCurrentPlayer((CurrentPlayerChangedEvent)e);
			break;
		default :
			break;
		}
	}

	private void handleChangeCurrentPlayer (CurrentPlayerChangedEvent e)
	{
		// TODO Auto-generated method stub
	}

	private void handleCreateUnit (CreateUnitEvent e)
	{
		// TODO Auto-generated method stub
	}

	private void handleDestroyUnit (UnitDestroyedEvent due)
	{
		// TODO Auto-generated method stub
	}
}
