package com.example.segundoauqui.navegationapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import static android.R.attr.key;
import static android.R.attr.keycode;


public class Home extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }
    public WebView webViewHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Home");
        // Inflate the layout for this fragment

        String url = "https://github.com/SAUQU";
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        webViewHome = (WebView) view.findViewById(R.id.webViewHome);
        if(savedInstanceState == null) {
            webViewHome.loadUrl(url);
        }
        WebSettings webSettings = webViewHome.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewHome.setWebViewClient(new WebViewClient());



        webViewHome.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) view;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });


        return view;

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webViewHome.saveState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webViewHome.restoreState(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public WebView getWebView(){
        return webViewHome;
    }




}