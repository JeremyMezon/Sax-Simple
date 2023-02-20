package com.example.saxsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var texto : TextView = findViewById(R.id.txtResultado);

        var executor = Executors.newSingleThreadExecutor();
        var handler = Handler(Looper.getMainLooper());

        executor.execute {
            val saxparser = RssParserSaxSimple("https://www.europapress.es/rss/rss.aspx")
            var noticias = saxparser.parse()
            handler.post {
                for (i in noticias!!.indices) {
                    texto.text = texto.text.toString() +
                            System.getProperty("line.separator") +
                            noticias!![i].Titulo
                }
            }
        };
    }
}