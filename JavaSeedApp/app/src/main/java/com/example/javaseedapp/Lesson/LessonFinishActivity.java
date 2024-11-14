package com.example.javaseedapp.Lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javaseedapp.DAO.LessonDAO;
import com.example.javaseedapp.DAO.StudentLessonDAO;
import com.example.javaseedapp.Model.StudentLesson;
import com.example.javaseedapp.R;
import com.example.javaseedapp.Units.ListUnits;

public class LessonFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession_finish);

        Intent intent = getIntent();
        int courseStudentId = intent.getIntExtra("courseStudentId",-1);
        int lessonId = intent.getIntExtra("lessonId", -1);
        int curPoint = intent.getIntExtra("curPoint", -1);
        int totalPoint = intent.getIntExtra("totalPoint", -1);

        TextView score = (TextView) findViewById(R.id.ScoreTxt);
        score.setText(curPoint+"/"+totalPoint);
        boolean check = false;
        StudentLesson studentLesson =StudentLessonDAO.getInstance().getDetail(courseStudentId,lessonId);
        if(studentLesson==null){
            studentLesson = new StudentLesson(curPoint,lessonId,courseStudentId);
            check=StudentLessonDAO.getInstance().createStudentLesson(studentLesson);
        }else{
            if(curPoint>studentLesson.getMark()){
                studentLesson.setMark(curPoint);
                check=StudentLessonDAO.getInstance().updateStudentLesson(studentLesson);
            }
        }
        Button btn = findViewById(R.id.continueBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LessonFinishActivity.this, ListUnits.class);
                i.putExtra("id", LessonDAO.getInstance().getDetail(lessonId).getUnit().getCourseId());
                startActivity(i);
            }
        });
    }
}