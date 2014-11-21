package edu.fresnostate.turnbased.event;

/**
 * An enum of every possible event type. One unique entry for every
 * implementation of Event.
 *
 * @author Daniel
 *
 */
public enum EventType
{
	ANIMATION_FINISHED, ATTACK_UNIT, CREATE_UNIT, CURRENT_PLAYER_CHANGED,
	END_TURN, MOVE_UNIT, UNIT_ATTACKED, UNIT_CREATED, UNIT_DESTROYED,
	UNIT_MOVED, WINDOW_RESIZED
}
