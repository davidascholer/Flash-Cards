package com.SIT.AbleApps.ItalianDroidFlashCardsfree;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends mainActivity  {

	
	// public static final int TEXTBUBBLE_CLICK_ID = 0;
	// public static final int another option = 1;
	// If the user closes the bubble, it should be closed when the activity is
	// called again
	// boolean bubbleVisible = true;
	private static final int sMenuResource = R.menu.options_menu;
	private Menu mMenu;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		// Set fullscreen
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);

		Resources res = getResources();
		LinearLayout image = (LinearLayout) findViewById(R.id.LinLayout);
		if(menuDrawable == null){
		menuDrawable = res.getDrawable(R.drawable.a1);//change here
		}
		image.setBackgroundDrawable(menuDrawable);
		
		
		final ListView listView = (ListView) findViewById(R.id.list);
		final String[] cardDeckList = new String[NUMBER_OF_DECKS];
		cardDeckList[0] = "Animals";
		cardDeckList[1] = "Body";

		cardDeckList[2] = "Colors";

		cardDeckList[3] = "Conjugation Practice";

		cardDeckList[4] = "Describing People";
		cardDeckList[5] = "Family";

		cardDeckList[6] = "Foods";

		cardDeckList[7] = "Fruits and Vegetables";
		cardDeckList[8] = "Greetings";

		cardDeckList[9] = "In The City";

		cardDeckList[10] = "Months and Seasons";

		cardDeckList[11] = "Numbers";
		
		cardDeckList[12] = "Traveling";
		
		cardDeckList[13] = "Weather";

		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, cardDeckList));
		// listView.setAdapter(adapt);

		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// set cardDeckChecked boolean array to true for all values
		cardDeckChecked = new boolean[NUMBER_OF_DECKS];
		for (int i = 0; i < NUMBER_OF_DECKS; i++) {
			cardDeckChecked[i] = false;
			listView.setItemChecked(i, false);
		}

		
		
		// alertDialog = new AlertDialog.Builder(this).create();
		// alertDialog.setTitle("Alert 1");
		// alertDialog.setMessage("This is an alert");
		// alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int which) {
		// return;
		// } });
		TextView myMsg = new TextView(this);
		myMsg.setText("Select a Card Deck");
		myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
	
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(myMsg).setCancelable(true)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						return;
					}
				});
		final AlertDialog alert = builder.create();

		ImageButton goFullScreen = (ImageButton) findViewById(R.id.fullScreenImageButton);
		goFullScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				boolean noneSelected = true;

				for (int i = 0; i < NUMBER_OF_DECKS; i++) {
					if (cardDeckChecked[i] == true) {
						noneSelected = false;
						break;
					} else {
						noneSelected = true;
					}
				}

				if (noneSelected == true) {
					alert.show();
				} else {
				
					Intent myIntent = new Intent(view.getContext(),
							FullScreenMode.class);
					startActivityForResult(myIntent, 0);
				}
			}
		});

		// Create a message handling object as an anonymous class.
		OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				// check if check changed
				// if (listView.isItemChecked(0) != cardDeckChecked[0]) {
				// cardDeckChecked[0] = listView.isItemChecked(0);
				// // check/uncheck all
				// if (listView.isItemChecked(0) == false) {
				// for (int i = 1; i < deckLength; i++) {
				// listView.setItemChecked(i, false);
				// }
				// } else {
				// for (int i = 0; i < deckLength; i++) {
				// listView.setItemChecked(i, true);
				// }
				// }
				// }

				// set the global array (not including 0 as this is only for
				// selecting all)
				for (int i = 0; i < NUMBER_OF_DECKS; i++) {
					if (listView.isItemChecked(i) != cardDeckChecked[i]) {
						cardDeckChecked[i] = listView.isItemChecked(i);
					}
				}
			}
		};
		// Now hook into our object and set its onItemClickListener member
		// to our class handler object.
		ListView lv = (ListView) findViewById(R.id.list);
		lv.setOnItemClickListener(mMessageClickedHandler);
	}


	//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Hold on to this
		mMenu = menu;

		// Inflate the currently selected menu XML resource.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(sMenuResource, menu);

		MenuItem em = menu.findItem(R.id.englishmode_true);
		em.setVisible(false);
		em.setEnabled(false);
		MenuItem fm = menu.findItem(R.id.englishmode_false);
		fm.setVisible(false);
		fm.setEnabled(false);
		MenuItem speak = menu.findItem(R.id.speak);
		speak.setVisible(false);
		speak.setEnabled(false);
		MenuItem shfl = menu.findItem(R.id.shuffle);
		shfl.setVisible(false);
		shfl.setEnabled(false);
		MenuItem sbg = menu.findItem(R.id.select_bg);
		sbg.setVisible(false);
		sbg.setEnabled(false);
		MenuItem back = menu.findItem(R.id.back);
		back.setVisible(false);
		back.setEnabled(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		ListView listView = (ListView) findViewById(R.id.list);

		switch (item.getItemId()) {
		case R.id.options_menu_close:
			android.os.Process.killProcess(android.os.Process.myPid());
			return true;
		case R.id.select_all:
			for (int i = 0; i < NUMBER_OF_DECKS; i++) {
				cardDeckChecked[i] = true;
				listView.setItemChecked(i, true);
			}
			return true;
		case R.id.background:
			startActivity(new Intent(menu.this, BackgroundSwitcher.class));
			menu.this.finish();
			return true;
		case R.id.select_none:
			for (int i = 0; i < NUMBER_OF_DECKS; i++) {
				cardDeckChecked[i] = false;
				listView.setItemChecked(i, false);
			}
			return true;
		case R.id.use_dict:

			startActivity(new Intent(menu.this, Dictionary.class));

			return true;
			// Generic catch all for all the other menu resources
		case R.id.help:
			TextView msg = new TextView(this);
			msg.setText("Select one or more decks by checking it then press go.\n\n You can select just one or as many as you would like to practice at one time. \n\nSelect All and Select None in the options menu may help you out.");
			msg.setGravity(Gravity.CENTER_HORIZONTAL);
		
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setView(msg).setCancelable(true)
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
						}
					});
			final AlertDialog alrt = builder.create();
			alrt.show();
			return true;
		default:
			// Don't toast text when a submenu is clicked
			if (!item.hasSubMenu()) {
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT)
						.show();
				return true;
			}
			break;
		}

		return false;
	}

//public void setBgImage(){
//		
//	LinearLayout image = (LinearLayout) findViewById(R.id.LinLayout);
//	image.setBackgroundDrawable(menuDrawable);
	@Override
	public void onStart(){
		super.onStart();

		
		LinearLayout image = (LinearLayout) findViewById(R.id.LinLayout);
		image.setBackgroundDrawable(menuDrawable);
	}
	
	// ////////////////for main version
	// protected Dialog onCreateDialog(int id) {
	// Dialog dialog = null;
	// switch(id) {
	// case TEXTBUBBLE_CLICK_ID:
	//
	//
	// LayoutInflater inflater =
	// (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// final View layout =
	// inflater.inflate(R.layout.textbubble_dialogue,
	// (ViewGroup) findViewById(R.id.root));
	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
	// builder.setIcon(R.drawable.frenchandroid);
	// builder.setMessage("I'm here to help. Click me and I will help you by pronouncing the word. TRY IT OUT!")
	// .setCancelable(true)
	// .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// removeDialog(0);
	//
	// }
	// });
	// AlertDialog alert = builder.create();
	// return alert;
	// default:
	// dialog = null;
	// }
	// return dialog;
	// }
	
}

