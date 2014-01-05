package com.tvpower.kz;

import com.tvpower.kz.R;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.drawable.Drawable;

public class ItemChanels extends BaseAdapter {
    private Context context;
    private final Elements canales;
    private final Pattern patron = Pattern.compile("([-_\\w]+).gif$");
    private View item;
    private ViewHolder holder;
    private Element canal;
    private String src;
    private Matcher match;
    private String nameOfimg;

    public ItemChanels(Context context, Elements canales) {
        this.context = context;
        this.canales = canales;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        item = convertView;
        if(item == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.content, null);
            holder = new ViewHolder();
            holder.title = (TextView) item.findViewById(R.id.textOne);
            holder.img = (ImageView) item.findViewById(R.id.imgLogo);
            item.setTag(holder);
        }else{
            holder = (ViewHolder) item.getTag();
        }

        canal = canales.get(position);
        holder.title.setText( canal.parent().text() +  " -> " + position);
        
        //find name of logo
        src = canal.attr("src");
        match = patron.matcher(src);
        if(match.find()){
            nameOfimg = match.group(1);
            holder.img.setImageDrawable(getImg(nameOfimg.replace("-","_")));
        }
        return item;
    }

    @Override
    public int getCount() {
        return canales.size();//mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private Drawable getImg(String path){
        path = "drawable/" + path;
        int imageID = context.getResources().getIdentifier(path, null, context.getPackageName());
        Drawable img = context.getResources().getDrawable(imageID);
        return img;
    }

}
