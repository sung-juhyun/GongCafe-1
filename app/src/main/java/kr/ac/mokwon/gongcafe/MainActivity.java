package kr.ac.mokwon.gongcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getData();

    }

    public void searchListener(View target){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void registrationListener(View target){
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    public void myPageListener(View target){
        Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
        startActivity(intent);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,recyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        List<String> listCafeName = Arrays.asList("공스카페","카페2","카페3","카페4","카페5");
        List<String> listCafeInfo = Arrays.asList("설명1","설명2","설명3","설명4","설명5");
        List<Integer> listResId = Arrays.asList(
                R.drawable.cafe1,
                R.drawable.cafe2,
                R.drawable.cafe3,
                R.drawable.cafe4,
                R.drawable.cafe5
        );

        for(int i = 0; i<listCafeName.size();i++){
            Data data = new Data();
            data.setCafeName(listCafeName.get(i));
            data.setCafeInfo(listCafeInfo.get(i));
            data.setResId(listResId.get(i));

            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();
    }

}