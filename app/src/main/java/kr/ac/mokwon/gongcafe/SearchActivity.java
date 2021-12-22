package kr.ac.mokwon.gongcafe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
//
//    private ItemAdapter adapter;
//    private List<ItemModel> itemList;

    private List<ImageDTO> imageDTOs = new ArrayList<>();
    private List<String> uidLists = new ArrayList<>();
    private FirebaseDatabase database;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        database =FirebaseDatabase.getInstance();
        setUpRecyclerView();



    }

    class BoardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((CustomViewHolder)holder).textView.setText(imageDTOs.get(position).cafeName);
            ((CustomViewHolder)holder).textView2.setText(imageDTOs.get(position).info);

            Glide.with(holder.itemView.getContext()).load(imageDTOs.get(position).imageUrl).into(((CustomViewHolder)holder).imageView);


        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            TextView textView2;

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.search_image_view);
                textView = (TextView) view.findViewById(R.id.search_text_view1);
                textView2 = (TextView) view.findViewById(R.id.search_text_view2);
            }
        }
    }

//    }

    /****************************************************
     리사이클러뷰, 어댑터 셋팅
     ***************************************************/
    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.search_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final BoardRecyclerViewAdapter boardRecyclerViewAdapter = new BoardRecyclerViewAdapter();
        recyclerView.setAdapter(boardRecyclerViewAdapter);

        database.getReference().child("images").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                imageDTOs.clear();;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ImageDTO imageDTO = snapshot.getValue(ImageDTO.class);
                    imageDTOs.add(imageDTO);
                }
                boardRecyclerViewAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
        //recyclerview
//        RecyclerView search_recyclerView = findViewById(R.id.search_recyclerView);
//        search_recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//
//        //adapter
//        itemList = new ArrayList<>(); //샘플테이터
//        fillData();
//        adapter = new ItemAdapter(itemList);
//        search_recyclerView.setLayoutManager(layoutManager);
//        search_recyclerView.setAdapter(adapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL); //밑줄
//        search_recyclerView.addItemDecoration(dividerItemDecoration);
//
//        //데이터셋변경시
//        //adapter.dataSetChanged(exampleList);
//
//        //어댑터의 리스너 호출
//        adapter.setOnClickListener(this);
//    }

//    private void fillData() {
//        itemList = new ArrayList<>(); //샘플테이터
//        itemList.add(new ItemModel(R.drawable.cafe1, "Starbucks", "스타벅스"));
//        itemList.add(new ItemModel(R.drawable.cafe2, "Tom N Toms", "탐엔탐스"));
//        itemList.add(new ItemModel(R.drawable.cafe3, "EDIYA", "이다야"));
//        itemList.add(new ItemModel(R.drawable.cafe4, "Twosome Place", "투썸플레이스"));
//        itemList.add(new ItemModel(R.drawable.cafe5, "I`m 1L", "일리터"));
//        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "caffe pascucci", "파스쿠찌"));
//        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "SevenCafe", "Sixteen"));
//        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "EightCafe", "Seventeen"));
//        itemList.add(new ItemModel(R.drawable.ic_launcher_foreground, "NineCafe", "Eighteen"));
//    }

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
//                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /****************************************************
     리사이클러뷰 클릭이벤트 인터페이스 구현
     ***************************************************/
//    @Override
//    public void onItemClicked(int position) {
//        Toast.makeText(this, "" +position, Toast.LENGTH_SHORT).show();
//    }
}