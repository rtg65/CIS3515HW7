package edu.temple.cis3515hw7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.webControlInterface, PageViewerFragment.webViewInterface {
    PageControlFragment pageControl;
    PageViewerFragment pageViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageControl = PageControlFragment.newInstance("test","rwar");
        pageViewer = PageViewerFragment.newInstance("dkfslj", "skdlfj");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.page_control, pageControl)
                .add(R.id.page_viewer, pageViewer)
                .commit();

        }




    public void searchClick(String html) {
        pageViewer.search(html);
    }

    public void backClick() {
        pageViewer.back();
    }
    public void forwardClick() {
        pageViewer.forward();
    }

    public void linkClick(String url){
        pageControl.linkClicked(url);
    }

}