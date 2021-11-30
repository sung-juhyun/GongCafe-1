package kr.ac.mokwon.gongcafe;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ItemAdapter.onItemListener {

    private ItemAdapter adapter;
    private List<ItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        setUpRecyclerView();
    }

    /****************************************************
     리사이클러뷰, 어댑터 셋팅
     ***************************************************/
    private void setUpRecyclerView() {
        //recyclerview
        RecyclerView search_recyclerView = findViewById(R.id.search_recyclerView);
        search_recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //adapter
        itemList = new ArrayList<>(); //샘플테이터
        fillData();
        adapter = new ItemAdapter(itemList);
        search_recyclerView.setLayoutManager(layoutManager);
        search_recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL); //밑줄
        search_recyclerView.addItemDecoration(dividerItemDecoration);

        //데이터셋변경시
        //adapter.dataSetChanged(exampleList);

        //어댑터의 리스너 호출
        adapter.setOnClickListener(this);
    }

    private void fillData() {
        itemList = new ArrayList<>(); //샘플테이터
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "One", "Ten"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Two", "Eleven"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Three", "Twelve"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Four", "Thirteen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Five", "Fourteen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Six", "Fifteen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Seven", "Sixteen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Eight", "Seventeen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "Nine", "Eighteen"));
    }

    /****************************************************
     onCreateOptionsMenu SearchView  기능구현
     ***************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /****************************************************
     리사이클러뷰 클릭이벤트 인터페이스 구현
     ***************************************************/
    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "" +position, Toast.LENGTH_SHORT).show();
    }
}