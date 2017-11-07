package com.example.sookmyung.r1312197_04_choiha; /**
 * Created by sookmyung on 2017-11-07.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
    Button btn_C, btn_plus, btn_minus, btn_multiply, btn_divide, btn_result;
    TextView tv_preview;
    //TextView tv_result;

//    double result = 0;
//    double temp = 0;
//    int sign = 0; // default0, plus1, minus2, multiply3, divide4
//    boolean ch_sign = false;

    ArrayList<String> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);

        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_C = (Button)findViewById(R.id.btn_C);
        btn_plus = (Button)findViewById(R.id.btn_plus);
        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_multiply = (Button)findViewById(R.id.btn_multiply);
        btn_divide = (Button)findViewById(R.id.btn_divide);
        btn_result = (Button)findViewById(R.id.btn_result);
        //tv_result = (TextView)findViewById(R.id.textView);
        tv_preview = (TextView)findViewById(R.id.tv_preview);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_result.setOnClickListener(this);
    }

    public void onClick(View v) {
        // 안드로이드 메이저 컴포넌트(액티비티, 서비스, 컨텐트 프로바이더, 브로드캐스트 리시버)를 로드하기 위해서는
        // intent를 통해서 로드할 컴포넌트를 지정해야한다.

        switch (v.getId()) {
            case R.id.btn_0:
                addEquation("0");
                break;
            case R.id.btn_1:
                addEquation("1");
                break;
            case R.id.btn_2:
                addEquation("2");
                break;
            case R.id.btn_3:
                addEquation("3");
                break;
            case R.id.btn_4:
                addEquation("4");
                break;
            case R.id.btn_5:
                addEquation("5");
                break;
            case R.id.btn_6:
                addEquation("6");
                break;
            case R.id.btn_7:
                addEquation("7");
                break;
            case R.id.btn_8:
                addEquation("8");
                break;
            case R.id.btn_9:
                addEquation("9");
                break;
            case R.id.btn_plus:
                addEquation("+");
                break;
            case R.id.btn_minus:
                addEquation("-");
                break;
            case R.id.btn_multiply:
                addEquation("*");
                break;
            case R.id.btn_divide:
                addEquation("/");
                break;
            case R.id.btn_C:
                tv_preview.setText(" ");
              //  tv_result.setText(" ");
                list = null;
                break;
            case R.id.btn_result:
                showResult(calculate(tv_preview.getText().toString()));
                break;
        }
    }

    public String calculate(String str) {
        // 1 + 2 - 3 * 4 / 5
        //String str_split1[] = str.split("\\W"); // 숫자 찾기
        //String str_split2[] = str.split("\\d"); // 기호 찾기
        String split[] = str.split("(?<=[*/+-])|(?=[*/+-])"); // 기호( +, -, *, / ) 를 구분하는 정규표현식

        list = new ArrayList<String>(); // 숫자, 기호로 나누어 담을 ArrayList 생성.

        int i = 0;
        for(i = 0; i < split.length; i++) {
            list.add(i, split[i]);  // 수식을 숫자부분과 기호부분으로 나누어 ArrayList에 담는다.
        }

        double pre = 0, suf = 0; // 수식의 앞숫자(pre)와 뒤숫자(suf)
        double result = 0;

        // 곱셈(곱하기)
        for(i = 0; i < list.size(); i++) {
            if (list.get(i).equals("*")) { // * 기호일 경우
                pre = Double.parseDouble(list.get(i-1)); // 기호의 앞숫자(pre)와
                suf = Double.parseDouble(list.get(i+1)); // 기호의 뒷숫자(suf)를
                result = pre * suf; // 곱해준다.

                list.set(i, result+""); // * 기호를 곱하기의 결과로 바꿔준다.
                list.remove(i-1); // pre를 제거한다.
                list.remove(i); // suf를 제거한다.
            }
        }

        // 나눗셈(나누기)
        for(i = 0; i < list.size(); i++) {
            if (list.get(i).equals("/")) {
                pre = Double.parseDouble(list.get(i - 1));
                suf = Double.parseDouble(list.get(i + 1));
                result = pre / suf;

                list.set(i, result + "");
                list.remove(i - 1);
                list.remove(i);
            }
        }

        // 덧셈(더하기)
        for(i = 0; i < list.size(); i++) {
            if (list.get(i).equals("+")) {
                pre = Double.parseDouble(list.get(i - 1));
                suf = Double.parseDouble(list.get(i + 1));
                result = pre + suf;

                list.set(i, result + "");
                list.remove(i - 1);
                list.remove(i);
            }
        }

        // 뺄셈(빼기)
        for(i = 0; i < list.size(); i++) {
            if (list.get(i).equals("-")) {
                pre = Double.parseDouble(list.get(i - 1));
                suf = Double.parseDouble(list.get(i + 1));
                result = pre - suf;

                list.set(i, result + "");
                list.remove(i - 1);
                list.remove(i);
            }
        }

        //textView.setText(list.get(0));
        return list.get(0); // 결과 리턴
    }

    /**결과 출력 함수
     *
     * @param str
     */
    public void showResult(String str) {
        tv_preview.setText(str);
    }

    /**preview텍스트뷰에 수식(숫자와 기호)을 적을 수 있게 해준다.
     *
     * @param str
     */
    public void addEquation(String str) {
        prev_setText(tv_preview.getText() + str);
    }

    /**preview텍스트뷰에 한 글자씩 입력할 수 있는 함수.
     *
     * @param str
     */
    public void prev_setText(String str) {
        tv_preview.setText(str);
    }
}
