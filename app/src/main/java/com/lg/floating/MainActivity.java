package com.lg.floating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvStickyHeaderView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    /**
     * 初始化View
     */
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        tvStickyHeaderView = (TextView) findViewById(R.id.tv_sticky_header_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StickyExampleAdapter(this, getData()));
    }

    /**
     * 初始化Listener
     */
    private void initListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickview = recyclerView.findChildViewUnder(0, 0);
                if (stickview != null && stickview.getContentDescription() != null) {
                    if (!TextUtils.equals(tvStickyHeaderView.getText(), stickview.getContentDescription())) {
                        tvStickyHeaderView.setText(stickview.getContentDescription());
                    }
                }
                View transInfoView = recyclerView.findChildViewUnder(
                        0, tvStickyHeaderView.getHeight() + 1);
                if (transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int top = transInfoView.getTop();

                    if (transViewStatus == StickyExampleAdapter.HAS_STICKY_VIEW) {
                        if (top > 0) {
                            int dealtY = top - tvStickyHeaderView.getMeasuredHeight();
                            tvStickyHeaderView.setTranslationY(dealtY);
                        } else {
                            tvStickyHeaderView.setTranslationY(0);
                        }
                    } else if (transViewStatus == StickyExampleAdapter.NONE_STICKY_VIEW) {
                        tvStickyHeaderView.setTranslationY(0);
                    }
                }
            }
        });
    }

    public List<StickyBean> getData() {
        List<StickyBean> stickyExampleModels = new ArrayList<>();

        for (int index = 0; index < 100; index++) {
            if (index < 15) {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本1", "name" + index, "gender" + index));
            } else if (index < 25) {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本2", "name" + index, "gender" + index));
            } else if (index < 35) {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本3", "name" + index, "gender" + index));
            } else {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本4", "name" + index, "gender" + index));
            }
        }
        return stickyExampleModels;
    }
}
