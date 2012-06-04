package com.SIT.AbleApps.ItalianDroidFlashCardsfree;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BackgroundSwitcher extends mainActivity implements OnTouchListener {

	final Drawable[] bg = new Drawable[3];
	// final ImageView[] background = new ImageView[16];

	private Menu mMenu;
	private static final int sMenuResource = R.menu.options_menu;
	private float downXValue;
	int pic = 1;
	Drawable temp; //in case user selects back button

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.background_switcher);

		Resources res = getResources();

		bg[0] = res.getDrawable(R.drawable.a1);
		bg[1] = res.getDrawable(R.drawable.a2);
		bg[2] = res.getDrawable(R.drawable.a3);

	 

		temp = menuDrawable;
		LinearLayout background = (LinearLayout) findViewById(R.id.BackgroundLayout);
		background.setBackgroundDrawable(bg[1]);

		LinearLayout image = (LinearLayout) findViewById(R.id.BackgroundLayout);
		image.setOnTouchListener((OnTouchListener) this);

		//
		ImageButton changePic = (ImageButton) findViewById(R.id.changeButton);
		changePic.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				goBackToMainScreen();

			}
		});

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
		MenuItem bg = menu.findItem(R.id.background);
		bg.setVisible(false);
		bg.setEnabled(false);
		MenuItem sa = menu.findItem(R.id.select_all);
		sa.setVisible(false);
		sa.setEnabled(false);
		MenuItem sn = menu.findItem(R.id.select_none);
		sn.setVisible(false);
		sn.setEnabled(false);
		MenuItem ud = menu.findItem(R.id.use_dict);
		ud.setVisible(false);
		ud.setEnabled(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.options_menu_close:
			android.os.Process.killProcess(android.os.Process.myPid());
			return true;
		case R.id.select_bg:

			goBackToMainScreen();

			return true;
		case R.id.back:

			LinearLayout background = (LinearLayout) findViewById(R.id.BackgroundLayout);
			background.setBackgroundDrawable(temp);
			startActivity(new Intent(BackgroundSwitcher.this, menu.class));
			BackgroundSwitcher.this.finish();

			return true;
			
		default:

			break;
		}

		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// Get the action that was done on this touch event
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			// store the X value when the user's finger was pressed down
			downXValue = arg1.getX();
			break;
		}

		case MotionEvent.ACTION_UP: {
			// Get the X value when the user released his/her finger
			float currentX = arg1.getX();

			// going backwards: pushing stuff to the right
			if (downXValue < currentX) {
				prevPic();
			}

			// going forwards: pushing stuff to the left
			if (downXValue > currentX) {
				nextPic();
			}// ends first if
			break;
		}
		}

		// if you return false, these actions will not be recorded
		return true;
	}

	private void prevPic() {

		LinearLayout image = (LinearLayout) findViewById(R.id.BackgroundLayout);

		Animation cardOutLeft = AnimationUtils.loadAnimation(this,
				R.anim.cardout_right);

		final Animation cardInRight = AnimationUtils.loadAnimation(this,
				R.anim.cardin_left);

		image.startAnimation(cardOutLeft);

		cardOutLeft.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				// The animation has ended. Do methods then set card_in
				LinearLayout image = (LinearLayout) findViewById(R.id.BackgroundLayout);

				pic--;
				if (pic == -1) {
					pic = 2;
				}
				image.setBackgroundDrawable(bg[pic]);
				image.startAnimation(cardInRight);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void nextPic() {

		LinearLayout image = (LinearLayout) findViewById(R.id.BackgroundLayout);

		Animation cardOutLeft = AnimationUtils.loadAnimation(this,
				R.anim.cardout_left);

		final Animation cardInRight = AnimationUtils.loadAnimation(this,
				R.anim.cardin_right);

		image.startAnimation(cardOutLeft);

		cardOutLeft.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				// The animation has ended. Do methods then set card_in
				LinearLayout image = (LinearLayout) findViewById(R.id.BackgroundLayout);

				pic++;
				if (pic == 3) {
					pic = 0;
				}
				image.setBackgroundDrawable(bg[pic]);
				image.startAnimation(cardInRight);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void goBackToMainScreen() {
		menuDrawable = bg[pic];
		

		startActivity(new Intent(BackgroundSwitcher.this, menu.class));
		
		BackgroundSwitcher.this.finish();
		
	}

}
