package com.almas.uyghursdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.almas.view.UySyllabelTextView;

public class HomeAdapter extends BaseAdapter implements ListAdapter {

	private String[] title={"ئۇيغۇرچە","English","كىرگۈزگۈچ"};
	private LayoutInflater inflater;
	public HomeAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return title.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO: Odd code returning the passed argument?
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO: Odd code returning the passed argument?
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = inflater.inflate(R.layout.list_item_main, null);
		}
		UySyllabelTextView  textView = (UySyllabelTextView ) convertView.findViewById(R.id.text);
		textView.setText(title[position]);
		return convertView;
	}

}
