package com.tvpower.kz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.content.Intent;
//jsoup
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class tvpower extends Activity{
    /** Called when the activity is first created. */
    private Element chanel;
    private Elements canales;
    private TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	text = (TextView)findViewById(R.id.textOne);
        final GridView grid = (GridView)findViewById(R.id.GridOpciones);
        //String url = "http://en.wikipedia.org/";
        String url = "http://www.entutele.com/programacion/todos/";
        Document doc = null;
        
        try{//sin esto no funciona esta mauser
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
        }catch(Exception e){
            //not create
            text.setText("no se obtuvo nada");
            return;
        }

	    //Elements canales = doc.select("[href^=/canal/");//3 que no van:
	    canales = doc.select(".canalimg");
	    /*va sin las comillas va sin las comillas de dentro en jQuery("[href^='/canal/ae']")*/
	    text.setText(doc.title() + " selector:-> " + canales.size());
	    grid.setAdapter(new ItemChanels(this, canales));
        
        grid.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
                    Intent intent = new Intent(tvpower.this,Chanel.class);
                    
                    Bundle bundle = new Bundle();
                    chanel = canales.get(position);
                    //todos los programas
                    //Elements programs = chanel.parent().parent().select("td a");
                    //
                    bundle.putString("name",chanel.parent().text());
                    bundle.putString("html",chanel.parent().parent().parent().select("td").toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    
                }

            }
        );

    }

}
