package com.SIT.AbleApps.ItalianDroidFlashCardsfree;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Dictionary extends mainActivity {

	private static final int sMenuResource = R.menu.options_menu;
	private Menu mMenu;

	WebView mWebView;
	String url;
	String language = "it";
	String english = "en";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dictionary);

		LinearLayout image = (LinearLayout) findViewById(R.id.dictLayout);
		image.setBackgroundDrawable(menuDrawable);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		// mWebView.loadUrl("http://www.google.com");

		mWebView.setWebViewClient(new HelloWebViewClient());

		mWebView.setVisibility(View.VISIBLE);

		url = "http://translate.google.com";
		mWebView.loadUrl(url);



	}
	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

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
		MenuItem sa = menu.findItem(R.id.select_all);
		sa.setVisible(false);
		sa.setEnabled(false);
		MenuItem sn = menu.findItem(R.id.select_none);
		sn.setVisible(false);
		sn.setEnabled(false);
		MenuItem dict = menu.findItem(R.id.use_dict);
		dict.setVisible(false);
		dict.setEnabled(false);
		MenuItem bg = menu.findItem(R.id.background);
		bg.setVisible(false);
		bg.setEnabled(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.options_menu_close:
			android.os.Process.killProcess(android.os.Process.myPid());
			return true;
		case R.id.back:
			Dictionary.this.finish();
			return true;
		case R.id.help:
			TextView msg = new TextView(this);
			msg.setText("Type in an Italian word you would like to find the definition or translation for in the box then click the coinciding button and a browser will open up. \n\nIt may be helpful to mention if you need to type in a special charactor you can long click on the letter on the Android default keyboard. Then, more letters should come into view showing these charactors.");//edit here
			msg.setGravity(Gravity.CENTER_HORIZONTAL);

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setView(msg)
					.setCancelable(true)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									return;
								}
							});
			final AlertDialog alrt = builder.create();
			alrt.show();
			return true;
			// Generic catch all for all the other menu resources
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
}
