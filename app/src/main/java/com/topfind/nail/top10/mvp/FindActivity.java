package com.topfind.nail.top10.mvp;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.topfind.nail.top10.R;
import com.topfind.nail.top10.adapters.CustomAdapter;
import com.topfind.nail.top10.database.TopDatabase;
import com.topfind.nail.top10.entity.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class FindActivity extends AppCompatActivity implements FindContract.IView {



    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.button) Button button;
    @BindView(R.id.listView) ListView listView;

    public static CustomAdapter adapter;
    private FindContract.IPresenter findPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);
        findPresenter = new FindPresenter(this, new RetrofitModel(), new RoomModel(this));
        findPresenter.LoadDataBase();
    }

    @OnItemClick(R.id.listView)
    void onItemClick(int position){
        Item dataModel= adapter.getItem(position);
        Toast.makeText(getApplicationContext(),dataModel.getTitle() + " - " + dataModel.getSnippet() +
                " - " + dataModel.getLink(),Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button)
    void OnClick(){
        findPresenter.processRequest(editText.getText().toString());
    }

    @Override
    public void showProgress() {
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDataToListView(ArrayList<Item> dataToAdapterView) {
        adapter= new CustomAdapter(dataToAdapterView ,getApplicationContext());
        listView.setAdapter(adapter);
    }


    @Override
    public void onResponseFailter(Throwable throwable) {
        Toast.makeText(FindActivity.this,
                "Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        findPresenter.onDestroy();

    }
}

