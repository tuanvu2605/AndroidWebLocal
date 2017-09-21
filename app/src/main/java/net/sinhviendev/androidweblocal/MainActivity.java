package net.sinhviendev.androidweblocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.io.*;



public class MainActivity extends AppCompatActivity {

    private MyServer server;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            server = new MyServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        webView = (WebView)findViewById(R.id.hiWebView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://localhost:8080/");





    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        try {
//            server = new MyServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        if(server != null) {
            server.stop();
        }
    }


    public class MyServer extends NanoHTTPD {
        private final static int PORT = 8080;

        public MyServer() throws IOException {
            super(PORT);
            start();
            System.out.println( "\nRunning! Point your browers to http://localhost:8080/ \n" );
        }

        @Override
        public Response serve(IHTTPSession session) {


            String msg = "<html><body><h1>Hello server</h1>\n";
            msg += "<p>We serve " + session.getUri() + " !</p>";
            return newFixedLengthResponse( msg + "</body></html>\n" );
        }
    }
}
