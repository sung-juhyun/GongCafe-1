package kr.ac.mokwon.gongcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MypageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

    }

    public void myPageLoginListener(View target){
        Intent intent = new Intent(getApplicationContext(), GoogleSignInActivity.class);
        startActivity(intent);
    }
}
