package edu.fresnostate.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MainMenuState implements GameState
{
	private Stage			stage;	// done
	private TextureAtlas	atlas;	// done
	private Skin			skin;	// done
	private Table			table;	// done
	private TextButton		buttonPlay, buttonExit;
	private BitmapFont		white, black;			// done
	private Label			heading;

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor (0, 0, 0, 1);
		Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
		// Table.drawDebug(stage);
		stage.draw ();
	}

	@Override
	public void enter ()
	{
		stage = new Stage ();
		Gdx.input.setInputProcessor (stage);
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
		buttonPlay = new TextButton ("PLAY", textButtonStyle);
		// Setting up Button START functionality
		buttonPlay.addListener (new ClickListener ()
		{
			@Override
			public void clicked (InputEvent event, float x, float y)
			{
				// TODO Needs to start the game
			}
		});
		buttonPlay.pad (20);
		// Setting up Button Exit functionality
		buttonExit = new TextButton ("EXIT", textButtonStyle);
		buttonExit.addListener (new ClickListener ()
		{
			@Override
			public void clicked (InputEvent event, float x, float y)
			{
				Gdx.app.exit ();
			}
		});
		buttonExit.pad (20);
		// Creating Heading
		LabelStyle headingStyle = new LabelStyle (white, Color.WHITE);
		heading = new Label ("THE RPG GAME", headingStyle);
		heading.setFontScale (2);
		// Putting Stuff Together
		table.add (heading);
		table.row ();
		table.add (buttonPlay);
		table.row ();
		table.add (buttonExit);
		stage.addActor (table);
	}

	@Override
	public void exit ()
	{
		white.dispose ();
		black.dispose ();
		skin.dispose ();
		atlas.dispose ();
		stage.dispose ();
	}

	@Override
	public void resize (int width, int height)
	{
	}

	@Override
	public void update ()
	{
		stage.act ();
	}
}
