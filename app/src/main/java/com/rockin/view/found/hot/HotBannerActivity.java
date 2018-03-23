package com.rockin.view.found.hot;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.R;
import com.rockin.databinding.ActivityHotBannerBinding;
import com.rockin.view.base.BaseActivity;

/**
 * @author Penoder
 * @date 18-2-5.
 */

public class HotBannerActivity extends BaseActivity {

    private ActivityHotBannerBinding bannerBinding;

    /**
     * 标题
     */
    public ObservableField<String> actionTitle = new ObservableField<>();

    /**
     * WebUrl
     */
    public ObservableField<String> actionUrl = new ObservableField<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerBinding = DataBindingUtil.setContentView(this, R.layout.activity_hot_banner);
        bannerBinding.setViewModel(this);

        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        actionTitle.set(getIntent().getStringExtra("ACTION_TITLE"));
        actionUrl.set(getIntent().getStringExtra("ACTION_URL"));
        // 不打开 JS 竟然加载不完全
        bannerBinding.webViewHotBanner.getSettings().setJavaScriptEnabled(true);
        bannerBinding.webViewHotBanner.loadUrl(actionUrl.get());

        bannerBinding.webViewHotBanner.setOnLongClickListener(v -> true);


        //如果不设置WebViewClient，请求会跳转系统浏览器
        bannerBinding.webViewHotBanner.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

                view.loadUrl(url);
//                    return true;
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    public ReplyCommand onBackCommand = new ReplyCommand(this::goBack);

    private void goBack() {
        if (bannerBinding.webViewHotBanner.canGoBack()) {
            bannerBinding.webViewHotBanner.goBack();
        } else {
            finish();
        }
    }
}
