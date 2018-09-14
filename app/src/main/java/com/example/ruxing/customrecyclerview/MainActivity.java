package com.example.ruxing.customrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerView mRecyclerView;
    private List<DataEntity> mList;
    private DataAdapter mDataAdapter;

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            DataEntity entity = new DataEntity();
            entity.setName("姓名" + i);
            entity.setSex("性别" + i);
            mList.add(entity);
        }
        mDataAdapter = new DataAdapter(this, mList);
        mRecyclerView.setAdapter(mDataAdapter);
        mRecyclerView.setHeaderView(mHeaderView);
        mRecyclerView.setmFooterView(mFooterView);
        mRecyclerView.setEmptyView(mEmptyView);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.item_footer, mRecyclerView, false);
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.item_empty, mRecyclerView, false);
    }
}
