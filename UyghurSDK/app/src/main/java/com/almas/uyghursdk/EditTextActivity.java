package com.almas.uyghursdk;

import com.almas.fragment.EditTextFragment;
import com.almas.fragment.EditTextFragmentController;
import com.almas.fragment.EditTextFragmentController.ConfirmClickedListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class EditTextActivity extends FragmentActivity implements ConfirmClickedListener   {

	private EditTextFragmentController editTextFragmentController;
	private EditTextFragment editTextFragment;
	private boolean isSettingMode = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_text);
		
		editTextFragmentController = new EditTextFragmentController(this);
		editTextFragment = new EditTextFragment();
		openEditTextFragment();
	}

	private void openEditTextFragment() {
		if(isSettingMode){
			FragmentManager fm =  getSupportFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        transaction.replace(R.id.frameLayout, editTextFragment);  
	        transaction.commit();
	        isSettingMode = false;
		} else {
			Log.w("", "trying to open edit text fragment outside settingMode, ignoring this action ");
		}
	}
	
	public void onSettingClicked(View v){
		if(isSettingMode)openEditTextFragment();
		else openSettingFragment();
	}

	private void openSettingFragment() {
		if(!isSettingMode){
			FragmentManager fm =  getSupportFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        transaction.replace(R.id.frameLayout, editTextFragmentController);  
	        transaction.commit();
	        isSettingMode = true;
		} else {
			Log.w("", "trying to open edit text fragment while already in settingMode, ignoring this action");
		}
	}
	
	public void onReturnClicked(View v){
		if(isSettingMode) {
			openEditTextFragment();
		} else {
			finish();
		}
	}

	@Override
	public void onConfirmClicked() {
		openEditTextFragment();
	}
}
