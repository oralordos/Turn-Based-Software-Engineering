package edu.fresnostate.turnbased.event;

public class AnimationFinishedEvent implements Event
{
	@Override
	public EventType getEventType ()
	{
		return EventType.ANIMATION_FINISHED;
	}
}
