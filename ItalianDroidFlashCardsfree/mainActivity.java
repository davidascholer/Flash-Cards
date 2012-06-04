package com.SIT.AbleApps.ItalianDroidFlashCardsfree;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class mainActivity extends Activity {
	// Game preference values
	public static final String FLASHCARD_PREFERENCES = "GamePrefs";
	public static final String FLASHCARD_PREFERENCES_CURRENT_CARD = "CurCard"; // Integer

	// XML Tag Names
	public static final String XML_TAG_CARD_BLOCK = "deck";
	public static final String XML_TAG_CARD = "card";
	public static final String XML_TAG_CARD_ATTRIBUTE_NUMBER = "number";
	public static final String XML_TAG_CARD_ATTRIBUTE_TEXT = "text";
	public static final String XML_TAG_CARD_ATTRIBUTE_IMAGEURL = "imageUrl";

	// Debugging
	public static final String DEBUG_TAG = "Flash Card Log";

	// Global Variables
	public static boolean[] cardDeckChecked;
	public static int NUMBER_OF_DECKS = 14;
	
	public static Drawable menuDrawable;
}

