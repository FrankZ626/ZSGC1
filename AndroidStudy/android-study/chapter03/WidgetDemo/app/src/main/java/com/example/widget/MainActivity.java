package com.example.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Exercise> exercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 初始化数据
        initExercises();

        // 2. 获取控件
        RecyclerView rvExercise = findViewById(R.id.rv_content);
        // 3. 设置布局和分割线
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvExercise.setLayoutManager(manager);
        rvExercise.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 4. 创建适配器
        ExerciseRecyclerAdapter adapter = new ExerciseRecyclerAdapter(exercises);
        // 5. 设置适配器
        rvExercise.setAdapter(adapter);

        // 6. 设置监听器
        adapter.setOnItemClickListener(new ExerciseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Exercise exercise = exercises.get(position);

                // 跳转到相应的章节的习题

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void initExercises() {
        exercises = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            Exercise exercise = new Exercise();
            exercise.setId(i + 1);
            switch (i) {
                case 0:
                    exercise.setTitle("第1章 Android基础入门");
                    exercise.setSubTitle("共计5题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 1:
                    exercise.setTitle("第2章 Android常用布局");
                    exercise.setSubTitle("共计9题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 2:
                    exercise.setTitle("第3章 Android常用控件");
                    exercise.setSubTitle("共计11题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 3:
                    exercise.setTitle("第4章 Activity和Fragment");
                    exercise.setSubTitle("共计15题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 4:
                    exercise.setTitle("第5章 数据存储");
                    exercise.setSubTitle("共计12题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 5:
                    exercise.setTitle("第6章 内容提供者");
                    exercise.setSubTitle("共计5题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 6:
                    exercise.setTitle("第7章 广播接收者");
                    exercise.setSubTitle("共计6题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 7:
                    exercise.setTitle("第8章 服务");
                    exercise.setSubTitle("共计5题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 8:
                    exercise.setTitle("第9章 网络编程");
                    exercise.setSubTitle("共计10题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 9:
                    exercise.setTitle("第10章 Material Design基础");
                    exercise.setSubTitle("共计8题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 10:
                    exercise.setTitle("第11章 基于位置的服务");
                    exercise.setSubTitle("共计3题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 11:
                    exercise.setTitle("第12章 Material Design实战");
                    exercise.setSubTitle("共计5题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 12:
                    exercise.setTitle("第13章 高级技巧");
                    exercise.setSubTitle("共计6题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                case 13:
                    exercise.setTitle("第14章 项目发布上线");
                    exercise.setSubTitle("共计4题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_2);
                    break;
                case 14:
                    exercise.setTitle("第15章 综合项目实战");
                    exercise.setSubTitle("共计5题");
                    exercise.setBackground(R.mipmap.ic_exercise_bg_1);
                    break;
                default:
                    break;
            }
            exercises.add(exercise);
        }
    }
}