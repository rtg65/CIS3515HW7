package edu.temple.cis3515hw7;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageViewerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;
    private PageViewerFragment.webViewInterface parentActivity;
    public PageViewerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageViewerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageViewerFragment newInstance(String param1, String param2) {
        PageViewerFragment fragment = new PageViewerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PageViewerFragment.webViewInterface) {
            parentActivity = (PageViewerFragment.webViewInterface) context;
        } else {
            throw new RuntimeException("You must implement webControlInterface to attach this fragment");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        WebView web = v.findViewById(R.id.web_view);
        web.setWebViewClient(new MyWebViewClient());
        return v;
    }

    public void search(String html){
        WebView web = v.findViewById(R.id.web_view);
        web.loadUrl(html);
    }

    public void back(){
        WebView web = v.findViewById(R.id.web_view);
        web.goBack();
    }

    public void forward(){
        WebView web = v.findViewById(R.id.web_view);
        web.goForward();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // This is my website, so do not override; let my WebView load the page
                return false;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            parentActivity.linkClick(url);
            super.onPageFinished(view, url);
        }
    }

    interface webViewInterface {
        void linkClick(String html);
    }
}