package edu.fresnostate.turnbased;

public class GameLogic implements EventListener
{
	public GameLogic ()
	{
		EventManager manager = EventManager.get ();
		manager.addListener (this, EventType.DestroyUnit);
		manager.addListener (this, EventType.CreateUnit);
		manager.addListener (this, EventType.ChangeCurrentPlayer);
	}

	@Override
	public void receiveEvent (Event e)
	{
		switch (e.getEventType ())
		{
		case DestroyUnit :
			handleDestroyUnit ((DestroyUnitEvent) e);
			break;
		case CreateUnit :
			handleCreateUnit ((CreateUnitEvent) e);
			break;
		case ChangeCurrentPlayer :
			handleChangeCurrentPlayer((ChangeCurrentPlayerEvent)e);
			break;
		default :
			break;
		}
	}

	private void handleChangeCurrentPlayer (ChangeCurrentPlayerEvent e)
	{
		// TODO Auto-generated method stub
	}

	private void handleCreateUnit (CreateUnitEvent e)
	{
		// TODO Auto-generated method stub
	}

	private void handleDestroyUnit (DestroyUnitEvent due)
	{
		// TODO Auto-generated method stub
	}
}
