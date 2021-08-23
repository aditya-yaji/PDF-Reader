
package com.miniproj.andro.pdfreader;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
public class PDFAdapter extends ArrayAdapter<File> implements Filterable {
    Context context;
    ViewHolder viewHolder;
    ArrayList<File> al_pdf;
    ArrayList<File> al_pdf1;
    public PDFAdapter(Context context, ArrayList<File> al_pdf) {
        super(context, R.layout.adapter_pdf, al_pdf);
        this.context = context;
        this.al_pdf = al_pdf;
        al_pdf1 = new ArrayList<>(al_pdf);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        if (al_pdf.size() > 0) {
            return al_pdf.size();
        } else {
            return 1;
        }
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_pdf, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_filename = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_filename.setText(al_pdf.get(position).getName());
        return view;
    }
    public static class ViewHolder {
        TextView tv_filename;
    }

public Filter getFilter(){
        return FilterUser;
}
private Filter FilterUser = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String searchText = charSequence.toString().toLowerCase();
        ArrayList<File> tempList = new ArrayList<>();
        if (searchText.length() == 0 || searchText.isEmpty()) {
            tempList.addAll(al_pdf1);
        } else {
            for (File item : al_pdf1) {
              if(item.getName().toLowerCase().contains(searchText) ){
              tempList.add(item);
              }
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = tempList;
            return filterResults;
        }
    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        al_pdf.clear();
        al_pdf.addAll((Collection<? extends File >)filterResults.values);
        notifyDataSetChanged();
    }
};
}