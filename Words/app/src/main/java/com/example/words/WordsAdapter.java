package com.example.words;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.words.Words;

import java.util.List;

public class WordsAdapter extends ArrayAdapter<Words> {
    @NonNull
    private int resoureId;
    public WordsAdapter(Context context, int testViewResourceId, List<Words> objects){
      super(context,testViewResourceId,objects);
      resoureId=testViewResourceId;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Words word=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView english=(TextView)view.findViewById(R.id.english);
        TextView chinese=(TextView)view.findViewById(R.id.chinese);
        english.setText(word.getEnglish());
        chinese.setText(word.getChinese());
        return view;
    }
}
