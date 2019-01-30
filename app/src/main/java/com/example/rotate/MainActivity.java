package com.example.rotate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.WindowManager;
import android.view.Surface;

public class MainActivity extends AppCompatActivity {

    private int height, width;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtView = (TextView) this.findViewById(R.id.orient);
        final ConstraintLayout layout = (ConstraintLayout) txtView.getParent();
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width  = layout.getMeasuredWidth();
                int height = layout.getMeasuredHeight();
                if (height > width)
                {
                    txtView.setText("Portrait");
                    Intent intent = new Intent();
                    intent.setAction("com.example.rotate.LANDSCAPE");
                    MainActivity.this.startActivity(intent);
                }
                else
                {
                    txtView.setText("Landscape");
                    Intent intent = new Intent();
                    intent.setAction("com.example.rotate.PORTRAIT");
                    MainActivity.this.startActivity(intent);
                }

                // Quit main activity
                try {  Thread.sleep(150); } catch (InterruptedException ignore) { }
                MainActivity.this.finish();

            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        TextView txtView = (TextView) this.findViewById(R.id.orient);
        //int height = this.getWindow().getDecorView().getHeight();
        //int width = this.getWindow().getDecorView().getWidth();
        //if (height > width) txtView.setText("Portrait");
        //else txtView.setText("Landscape");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
