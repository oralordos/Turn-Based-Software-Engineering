package edu.fresnostate.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import edu.fresnostate.turnbased.event.EndTurnEvent;
import edu.fresnostate.turnbased.event.EventManager;

public class GuiManager implements Disposable
{
	
	
	private Stage			stage;	
	private TextureAtlas	atlas;	
	private Skin			skin;	
	private Table			table;	
	private TextButton		buttonEnd;
	private BitmapFont		white, black;			
	private Label			heading;
	private Game 			game;
	
	
	public GuiManager(InputMultiplexer multiplex)
	{
	
		stage = new Stage();
		multiplex.addProcessor(stage);
		
		atlas = new TextureAtlas ("ui/button.pack");
		skin = new Skin (atlas);
		table = new Table (skin);
		// table.debug();
		table.setBounds (0, 0, Gdx.graphics.getWidth (),
				Gdx.graphics.getHeight ());
		// Creating Fonts
		black = new BitmapFont (Gdx.files.internal ("font/black.fnt"), false);
		white = new BitmapFont (Gdx.files.internal ("font/white.fnt"), false);
		// Creating Buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle ();
		textButtonStyle.up = skin.getDrawable ("button.up");
		textButtonStyle.down = skin.getDrawable ("button.down");
		textButtonStyle.pressedOffsetX = 2;
		textButtonStyle.pressedOffsetY = - 2;
		textButtonStyle.font = black;
		buttonEnd = new TextButton ("End Turn", textButtonStyle);
		// Setting up Button START functionality
		buttonEnd.addListener (new ClickListener ()
		{
			@Override
			public void clicked (InputEvent event, float x, float y)
			{
				// TODO Needs to start the game
				EventManager.queueEvent(new EndTurnEvent());
			}
		});
		buttonEnd.pad (20);
		table.add (buttonEnd);
		stage.addActor (table);
		
		
	}
	
	public void update()
	{
		stage.act();
	}

	public void render (SpriteBatch batch)
	{
		
		stage.draw();
		
	}

	@Override
	public void dispose ()
	{
		white.dispose ();
		black.dispose ();
		skin.dispose ();
		atlas.dispose ();
		stage.dispose ();
	}
}
