package com.almas.fragment;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.almas.tools.ML;
import com.almas.uyghursdk.AppConfig;
import com.almas.uyghursdk.ColorPickerDialog;
import com.almas.uyghursdk.R;

@SuppressLint("ValidFragment")
public class TextViewerControllerFragment extends Fragment {
	private ConfirmClickedListener confirmClickedListener;
	private SeekBar seekBarTextSize;
	private TextView textViewTextSize;
	private View viewMain;
	private Spinner spinnerFont;
	private SeekBar seekBarLineSpace;
	private TextView textViewLineSpace;
	private SeekBar seekBarFistLine;
	private TextView textViewFirstLine;
	private CheckBox checkBoxFirstLine;
	protected ColorPickerDialog dialog;
	private View imageViewTextViewerColor;
	public TextViewerControllerFragment(ConfirmClickedListener listener) {
		this.confirmClickedListener = listener;
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		 viewMain = inflater.inflate(R.layout.fragment_text_viewer_controller, null);
		 viewMain.findViewById(R.id.buttonConfirm).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(confirmClickedListener!=null) confirmClickedListener.onConfirmClicked();
			}
		});
		 
		 //字体的大小
		seekBarTextSize = viewMain.findViewById(R.id.seekBar1);
		seekBarTextSize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				AppConfig.textSize = seekBar.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewTextSize.setText(""+progress);
			}
		});
		textViewTextSize = viewMain.findViewById(R.id.textView1);
		
		//字体的选择
		spinnerFont = viewMain.findViewById(R.id.spinner);
		spinnerFont.setAdapter(new FontAdapter(getActivity(),AppConfig.fontName));
		spinnerFont.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				AppConfig.fontIndex = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		//行间距
		seekBarLineSpace = viewMain.findViewById(R.id.seekBarLineSpace);
		seekBarLineSpace.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				AppConfig.lineSpace = (float)(seekBar.getProgress()+10)/10.0f;
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewLineSpace.setText(""+(float)(seekBar.getProgress()+10)/10.0f);
			}
		});
		textViewLineSpace = viewMain.findViewById(R.id.textViewLineSpace);
		
		
		//首行缩进
		seekBarFistLine = viewMain.findViewById(R.id.seekBarFistLine);
		seekBarFistLine.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				AppConfig.firstLineIndentWidth = seekBar.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewFirstLine.setText(""+seekBar.getProgress());
			}
		});
		textViewFirstLine = viewMain.findViewById(R.id.textViewFistLine);
		checkBoxFirstLine = viewMain.findViewById(R.id.checkBoxFistLine);
		checkBoxFirstLine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AppConfig.enableFistLine = checkBoxFirstLine.isChecked();
				updateUI();
			}
		});
		Button btnColor = viewMain.findViewById(R.id.buttonTextColor);
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    dialog = new ColorPickerDialog(getActivity(), R.style.ColorDialogTheme,AppConfig.textColor,
			            "",
			            new ColorPickerDialog.OnColorChangedListener() {
			        @Override
			        public void colorChanged(int color) {
			        	AppConfig.textColor = color;
			        	updateUI();
			        }
			    });			   
			    dialog.show(); 
			}
		});
		imageViewTextViewerColor = viewMain.findViewById(R.id.imageViewTextViewerColor);
		
		updateUI();
		return viewMain;
	}
	@Override
	public void onStart() {
		super.onStart();
		updateUI();
	}
	private void updateUI() {
		textViewTextSize.setText(""+AppConfig.textSize);
		seekBarTextSize.setProgress(AppConfig.textSize);
		spinnerFont.setSelection(AppConfig.fontIndex,true);
		
		textViewLineSpace.setText(""+AppConfig.lineSpace);
		seekBarLineSpace.setProgress((int) ((AppConfig.lineSpace*10)-10));
		
		textViewFirstLine.setText(""+AppConfig.firstLineIndentWidth);
		seekBarFistLine.setProgress(AppConfig.firstLineIndentWidth);
		
		checkBoxFirstLine.setChecked(AppConfig.enableFistLine);
		if(AppConfig.enableFistLine) {
			seekBarFistLine.setEnabled(true);
		} else {
			seekBarFistLine.setEnabled(false);
		}
		imageViewTextViewerColor.setBackgroundColor(AppConfig.textColor);
	}
	public ConfirmClickedListener getConfirmClickedListener() {
		return confirmClickedListener;
	}
	public void setConfirmClickedListener(ConfirmClickedListener confirmClickedListener) {
		this.confirmClickedListener = confirmClickedListener;
	}
	public interface ConfirmClickedListener{
		void onConfirmClicked();
	}
}
