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
	UNIT_DESTROYED, CREATE_UNIT, WINDOW_RESIZED, CURRENT_PLAYER_CHANGED,
	ATTACK_UNIT, UNIT_ATTACKED, UNIT_CREATED, END_TURN
}
