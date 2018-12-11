package com.example.user.example2d;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.example2d.Drawgame;

public class MainActivity extends AppCompatActivity {
    Button up,down,left,right;
    int flagl=0,flagr=0,flagu=0,flagd=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        // Get the root Linearlayout object.
        LinearLayout rootLayout = (LinearLayout)findViewById(R.id.linear1);

        // Create the Drawgame custom view object.
        final Drawgame drawgame = new Drawgame(this);

        //set min width and height.
        drawgame.setMinimumWidth(500);
        drawgame.setMinimumHeight(800);

        // Create a ontouch listener object.
        up=(Button) findViewById(R.id.Up);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flagd!=1)
                {drawgame.moveCarUp();
                    flagl=0;
                    flagr=0;flagd=0;flagu=1;}
            }
        });
        down=(Button) findViewById(R.id.Down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flagu!=1){
                    drawgame.moveCarDown();
                    flagl=0;
                    flagr=0;flagu=0;flagd=1;}
            }
        });
        left=(Button) findViewById(R.id.Left);
        left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(flagr!=1) {
                    drawgame.moveCarLeft();
                    flagl=1;
                    flagr=0;flagu=0;flagd=0;
                }
            }
        });
        right=(Button) findViewById(R.id.Right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flagl!=1) {
                    drawgame.moveCarRight();
                    flagl=0;
                    flagr=1;flagu=0;flagd=0;
                }
            }
        });




        // Add drawgame object in root LinearLayout object.
        rootLayout.addView(drawgame);
    }

}