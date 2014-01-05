package com.tvpower.kz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import org.jsoup.select.Elements;
//cada nuevo activity se debe agregar al Androidmanifest.xml
public  class Chanel extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chanel);
        
        final TextView txtMensaje = (TextView)findViewById(R.id.textTitle);
        final TextView txtPrgs = (TextView)findViewById(R.id.prgs);
        
        Bundle bundle = this.getIntent().getExtras();
        txtMensaje.setText("Canal: " + bundle.getString("name"));

        final String html = bundle.getString("html");
        Document chanel = Jsoup.parseBodyFragment(html);
        Elements prgs = chanel.select("a");
        String txt = " ";
        for(Element a : prgs){
            txt += "\n " + a.text();
        }
        txtPrgs.setText("programacion: " + txt);


    }
}

