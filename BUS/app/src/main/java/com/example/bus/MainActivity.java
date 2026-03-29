package com.example.bus;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn1, btn2;

        btn1 = findViewById(R.id.btn1);//findViewById는 Layout에 있는 View 객체들을 리소스 아이디 통해 가져오는 메소드
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               txt1.setVisibility(View.VISIBLE);
               txt1.setText("버튼 1번을 눌렀습니다.");
               Toast.makeText(getApplicationContext(),"버튼 1번을 눌렀습니다.",Toast.LENGTH_SHORT).show();
           }

        });

        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                txt1.setVisibility(View.VISIBLE);
                txt1.setText("버튼 2번을 눌렀습니다.");
                Toast.makeText(getApplicationContext(),"버튼 12번을 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }

        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}