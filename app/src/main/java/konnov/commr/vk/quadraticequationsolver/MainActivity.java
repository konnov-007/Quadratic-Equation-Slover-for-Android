package konnov.commr.vk.quadraticequationsolver;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final int MENU_REPORTS_ID = 1;
    final int MENU_QUIT_ID = 2;

    EditText firstDecimal;
    EditText secondDecimal;
    EditText thirdDecimal;
    Button btnResult;
    Button btnClear;
    TextView discriminantOutput;
    TextView rootsOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        firstDecimal = (EditText) findViewById(R.id.first_number);
        secondDecimal = (EditText) findViewById(R.id.second_number);
        thirdDecimal = (EditText) findViewById(R.id.third_number);
        btnResult = (Button) findViewById(R.id.resultBtn);
        btnClear = (Button) findViewById(R.id.clearBtn);
        discriminantOutput = (TextView) findViewById(R.id.discriminant_output);
        rootsOutput = (TextView) findViewById(R.id.roots_output);

        btnResult.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        double a = 0;
        double b = 0;
        double c = 0;
        double disc = 0;
        double root1 = 0;
        double root2 = 0;
        double squareRoot=0;
        int flag = 10;
        // Проверяем поля на пустоту
        if (TextUtils.isEmpty(firstDecimal.getText().toString())
                || TextUtils.isEmpty(secondDecimal.getText().toString())
                || TextUtils.isEmpty(thirdDecimal.getText().toString())) {
            return;
        }

        // читаем EditText и заполняем переменные числами
        a = Float.parseFloat(firstDecimal.getText().toString());
        b = Float.parseFloat(secondDecimal.getText().toString());
        c = Float.parseFloat(thirdDecimal.getText().toString());

        if(v.getId() == R.id.resultBtn) {
            if(a == 0 || b == 0 || c == 0)
                flag = 0;
            if(flag != 0) {
                disc = (b * b) - 4 * (a * c);
                if (disc < 0)
                    flag = 1;
            }
            if(flag != 1 && flag != 0){
                squareRoot = Math.sqrt(disc);
                root1 = (-b - squareRoot) / (2 * a);
                root2 = (-b + squareRoot) / (2 * a);
                flag = 2;
            }
        }
        if(flag == 2) {
            discriminantOutput.setText("Discriminant = " + disc);
            if (root1 == root2)
                rootsOutput.setText("First root = second root = " + root1);
            else
                rootsOutput.setText("First root is " + root1 + ", second is " + root2);
        }
        if(flag == 1)
            discriminantOutput.setText("Discriminant equals " + disc + ". If discriminant less than zero, no roots exist");
        if(flag == 0)
            discriminantOutput.setText("It doesn't look like it's a quadratic quation");

        if(v.getId() == R.id.clearBtn) {
            discriminantOutput.setText("");
            rootsOutput.setText("");
            firstDecimal.setText("");
            secondDecimal.setText("");
            thirdDecimal.setText("");
        }

    }

    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// TODO Auto-generated method stub
        menu.add(0, MENU_REPORTS_ID, 0, "Feedback");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");
        return super.onCreateOptionsMenu(menu);
    }

    // обработка нажатий на пункты меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// TODO Auto-generated method stub
        switch (item.getItemId()) {
            case MENU_REPORTS_ID:
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com/im?media=&sel=223582583"));
                startActivity(browseIntent);
                break;
            case MENU_QUIT_ID:
// выход из приложения
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
