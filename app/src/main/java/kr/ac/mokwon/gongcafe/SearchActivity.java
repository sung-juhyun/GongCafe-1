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
        itemList.add(new ItemModel(R.drawable.cafe1, "Starbucks", "스타벅스"));
        itemList.add(new ItemModel(R.drawable.cafe2, "Tom N Toms", "탐엔탐스"));
        itemList.add(new ItemModel(R.drawable.cafe3, "EDIYA", "이다야"));
        itemList.add(new ItemModel(R.drawable.cafe4, "Twosome Place", "투썸플레이스"));
        itemList.add(new ItemModel(R.drawable.cafe5, "I`m 1L", "일리터"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "caffe pascucci", "파스쿠찌"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "SevenCafe", "Sixteen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "EightCafe", "Seventeen"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "NineCafe", "Eighteen"));
    }

    /****************************************************
     onCreateOptionsMenu SearchView  기능구현
     ***************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.menu.menu, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = findViewById(R.id.action_search2);


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