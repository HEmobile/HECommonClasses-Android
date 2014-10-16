package br.com.hemobile.hecommomclasses_android;



import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class GenericBaseAdapter<T> extends BaseAdapter {

	private List<T> list;
	
	public GenericBaseAdapter() {
		
	}

	@Override
	public int getCount() {
		try {
			return getList().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public T getItem(int position) {
		try {
			return getList().get(position);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ItemView<T> itemView;
	        if (convertView == null || !(convertView instanceof ItemView)) {
	            itemView = (ItemView<T>) buildItemView(); 
	        } else {
	            itemView = (ItemView<T>) convertView;
	        }
	        
	        itemView.bind(getItem(position), position);

	        return itemView;
	}

	public abstract ItemView<T> buildItemView();
	 
	public List<T> getList() {
		if (list == null)
			setList(new ArrayList<T>());
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
	
}
