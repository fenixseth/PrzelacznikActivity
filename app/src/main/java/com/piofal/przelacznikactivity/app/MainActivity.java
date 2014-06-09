package com.piofal.przelacznikactivity.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

class MyBrowserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row);
        Uri url = getIntent().getData();
        WebView webView = (WebView) findViewById(R.id.WebView01);
        webView.setWebViewClient(new Callback());
        webView.loadUrl(url.toString());
    }
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }
}
public class Test extends Activity {

    Button b1, b2, b3, b4, b5;
    int request_Code = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.btn_webbrowser);
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent("android.intent.action.VIEW");
                i.setData(Uri.parse("http://www.amazon.com"));
                startActivity(i);
            }
        });
        b2 = (Button) findViewById(R.id.btn_makecalls);
        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:+651234567"));
                startActivity(i);
            }
        });
        b3 = (Button) findViewById(R.id.btn_showMap);
        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri
                        .parse("geo:37.827500,-122.481670"));
                startActivity(i);
            }
        });
        b4 = (Button) findViewById(R.id.btn_chooseContact);
        b4.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(android.content.Intent.ACTION_PICK);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i, request_Code);
            }
        });

        b5 = (Button) findViewById(R.id.btn_launchMyBrowser);
        b5.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent("app.test.MyBrowser", Uri
                        .parse("http://www.amazon.com"));
                i.addCategory("app.test.OtherApps");
                i.addCategory("app.test.SomeOtherApps");
                startActivity(i);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, data.getData().toString(),
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(data.getData().toString()));
                startActivity(i);
            }
        }
    }
}


