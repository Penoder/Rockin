package com.rockin.view.homepage;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.BR;
import com.rockin.R;
import com.rockin.databinding.ActivitySearchBinding;
import com.rockin.view.base.BaseActivity;
import com.rockin.view.homepage.itemView.ItemSearchViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author Penoder
 * @date 2018/01/16
 */
public class SearchActivity extends BaseActivity {

    private ActivitySearchBinding searchBinding;

    /**
     * 输入的搜索词
     */
    public ObservableField<String> searchContent = new ObservableField<>();

    /**
     * 提示搜索词搜索出的结果数量
     */
    public ObservableField<String> searchResultCount = new ObservableField<>();

    /**
     * 热门搜索词
     */
    private List<String> keyWords = new ArrayList<>();


    public ObservableList<ItemSearchViewModel> items = new ObservableArrayList<>();
    public ItemBinding videoItemView = ItemBinding.of(BR.viewModel, R.layout.item_search_result);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        searchBinding.setViewModel(this);
        initView();
    }

    /**
     * 初始化热门搜索词
     */
    private void initView() {
        searchBinding.editTxtSearch.requestFocus();

        keyWords.add("阅后即瞎");
        keyWords.add("日食记");
        keyWords.add("复仇者联盟");
        keyWords.add("励志");
        keyWords.add("谷阿莫");
        keyWords.add("复仇者联盟3");
        keyWords.add("美食");
        keyWords.add("广告");
        keyWords.add("爱情");
        keyWords.add("舞蹈");
        keyWords.add("搞笑");
        keyWords.add("漫威");
        keyWords.add("动画");
        keyWords.add("日本");
        keyWords.add("电影相关");
        keyWords.add("健身");
        keyWords.add("VR");
        keyWords.add("滑板");
        keyWords.add("脱口秀");
        keyWords.add("寻梦环游记");

        LayoutInflater mInflater = LayoutInflater.from(this);
        for (String keyWord : keyWords) {
            TextView textView = (TextView) mInflater.inflate(R.layout.text_search_keyword, searchBinding.flowSearchWord, false);
            textView.setText(keyWord);
            final String str = textView.getText().toString();
            //点击事件
            textView.setOnClickListener(v -> {
                cancelSelected();   // 先取消其他标签的选中
                v.setSelected(true);
                for (int j = 0; j < keyWords.size(); j++) {
                    if (str.equals(keyWords.get(j))) {
                        searchVideoWithWord(str);
                    }
                }
            });
            searchBinding.flowSearchWord.addView(textView);//添加到父View
        }
    }

    private void cancelSelected() {
        int labelCount = searchBinding.flowSearchWord.getChildCount();
        for (int i = 0; i < labelCount; i++) {
            searchBinding.flowSearchWord.getChildAt(i).setSelected(false);
        }
    }

    private void searchVideoWithWord(String str) {
        searchContent.set(str);
    }

    public ReplyCommand onCancelSearchCommand = new ReplyCommand(() -> {
        finish();
        overridePendingTransition(0, R.anim.translate_from_up_to_out);
    });
}
