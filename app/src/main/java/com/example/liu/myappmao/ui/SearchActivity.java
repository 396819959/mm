package com.example.liu.myappmao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liu.myappmao.DatabaseFactory;
import com.example.liu.myappmao.DatabseCallback;
import com.example.liu.myappmao.R;
import com.example.liu.myappmao.SearchRecyclerItemDecoration;
import com.example.liu.myappmao.customView.MyRadioButton;
import com.example.liu.myappmao.mAdapter.SearchHistoryRecyclerViewAdapter;

import java.util.List;

public class SearchActivity extends Activity {
    private EditText searchEdit;
    private ImageView returnButton;
    private Button button;
    private mClick click;
    private Intent intent;
    private String searchContent;
    private RecyclerView rv;
    private List<String> searchHistory;
    private MyRadioButton searchHistoryButton;
    private SearchHistoryRecyclerViewAdapter searchAdapter;
    private DatabaseFactory database;
    private mDatabaseCallback callbacak;
    int page;
    SearchRecyclerItemDecoration itemDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //  Log.i("SearchActivity", "onCreate方法");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity_layout);
        searchEdit = findViewById(R.id.searchActivity_searchBar_id);
        returnButton = findViewById(R.id.searchActivity_return_id);
        button = findViewById(R.id.search_activity_button_id);
        searchHistoryButton = findViewById(R.id.search_history_id);
        rv = findViewById(R.id.search_history_recyclerView_id);
        initData();
    }

    private void initData() {

        click = new mClick();

        database = new DatabaseFactory(SearchActivity.this);
        database.setData(new mDatabaseCallback());
        returnButton.setOnClickListener(click);
        searchHistoryButton.setOnClickListener(click);
        button.setOnClickListener(click);
        intent = new Intent();
        searchAdapter = new SearchHistoryRecyclerViewAdapter(SearchActivity.this);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        itemDecoration = new SearchRecyclerItemDecoration();

        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                switch(actionId){

                    case EditorInfo.IME_ACTION_SEARCH:

                        searchContent = searchEdit.getText().toString().trim();
                        database.insertData(searchContent);
                        intent.setClass(SearchActivity.this, SearchContentPage.class);
                        intent.putExtra("content", searchContent);
                        startActivity(intent);
                       // Toast.makeText(SearchActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }


    private class mClick implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.searchActivity_return_id://返回

                    finish();

                    break;

                case R.id.search_activity_button_id://跳转

                    searchContent = searchEdit.getText().toString().trim();
                    database.insertData(searchContent);
                    intent.setClass(SearchActivity.this, SearchContentPage.class);
                    intent.putExtra("content", searchContent);
                    startActivity(intent);

                    break;


                case R.id.search_history_id://清空历史搜索
                    database.emptyData();
                    searchAdapter.setSearchHistory();
                    break;

            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        database.queryData();
    }

    private class mDatabaseCallback implements DatabseCallback.onDatabaseCallbacak {


        @Override
        public void getData(List list) {
            searchAdapter.setSearchHistory(list);
            rv.addItemDecoration(itemDecoration);
            rv.setAdapter(searchAdapter);
        }
    }
}
