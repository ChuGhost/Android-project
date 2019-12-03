package com.example.cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;
import java.math.BigDecimal;
import java.math.MathContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] num=new int[]{R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven,R.id.eight,R.id.nine,R.id.zero
                ,R.id.jia,R.id.jian,R.id.cheng,R.id.chu,R.id.equal,R.id.sin,R.id.cos,R.id.tan,R.id.cot,R.id.back
                ,R.id.clear,R.id.point,R.id.left,R.id.right,R.id.help,R.id.transtwo,R.id.transeight,R.id.m,R.id.cm
                ,R.id.m3,R.id.cm3,
                //R.id.ans,
                R.id.eightten,R.id.twoten,R.id.tensix,R.id.sixten};
        Button[] buttons=new Button[35];
        for(int i=0;i<buttons.length;i++) {
            buttons[i]=(Button)findViewById(num[i]);
            buttons[i].setOnClickListener(this);
        }
    }
    double ans=0;
    public void onClick(View view){
        TextView textView=(TextView)findViewById(R.id.view);
        TextView editText=(TextView)findViewById(R.id.input);
        int id=view.getId();
       switch (id){
           case R.id.one:
               editText.setText(editText.getText()+"1");
               textView.setText(textView.getText()+"1");
               break;
           case R.id.two:
               editText.setText(editText.getText()+"2");
               textView.setText(textView.getText()+"2");
               break;
           case R.id.three:
                editText.setText(editText.getText()+"3");
               textView.setText(textView.getText()+"3");
               break;
           case R.id.four:
               editText.setText(editText.getText()+"4");
               textView.setText(textView.getText()+"4");
               break;
           case R.id.five:
              editText.setText(editText.getText()+"5");
               textView.setText(textView.getText()+"5");
               break;
           case R.id.six:
              editText.setText(editText.getText()+"6");
               textView.setText(textView.getText()+"6");
               break;
           case R.id.seven:
               editText.setText(editText.getText()+"7");
               textView.setText(textView.getText()+"7");
               break;
           case R.id.eight:
               editText.setText(editText.getText()+"8");
               textView.setText(textView.getText()+"8");
               break;
           case R.id.nine:
               editText.setText(editText.getText()+"9");
               textView.setText(textView.getText()+"9");
               break;
           case R.id.zero:
               editText.setText(editText.getText()+"0");
               textView.setText(textView.getText()+"0");
               break;
           case R.id.jia:
               //char en=editText.getText().toString().charAt(editText.getText().toString().length()-1);
             //  System.out.println(en);
             //  if(en!='+'&&en!='-'&&en!='*'&&en!='/'){
               editText.setText(editText.getText() + "+");
                textView.setText(textView.getText() + "+");
         // }
               if(editText.getText().toString().length()==0){
                   editText.setText(editText.getText() + "ans+");
                   textView.setText(textView.getText() + "ans+");
               }
               break;
           case R.id.jian:
              editText.setText(editText.getText()+"-");
               textView.setText(textView.getText()+"-");
               break;
           case R.id.cheng:
               editText.setText(editText.getText()+"*");
               textView.setText(textView.getText()+"*");
               break;
           case R.id.chu:
               editText.setText(editText.getText()+"/");
               textView.setText(textView.getText()+"/");
               break;
           case R.id.left:
              editText.setText(editText.getText()+"(");
               textView.setText(textView.getText()+"(");
               break;
           case R.id.right:
               editText.setText(editText.getText().toString()+")");
               textView.setText(textView.getText().toString()+")");
               break;
           case R.id.equal:
               if(editText.getText().toString().charAt(0)=='a'){
                   CalculatorMathUtil util=new CalculatorMathUtil(2);
                   editText.setText(editText.getText()+"=");
                   double result=Double.parseDouble(util.getResult(ans+editText.getText().toString()));
                   ans=result;
                   System.out.println(ans);
                   textView.setText(textView.getText()+"="+result+"\n");
               }
               else{
               CalculatorMathUtil util=new CalculatorMathUtil(2);
               editText.setText(editText.getText()+"=");
               double result=Double.parseDouble(util.getResult(editText.getText().toString()));
               ans=result;
               System.out.println(ans);
               textView.setText(textView.getText()+"="+result+"\n");
               }
               break;
           case R.id.point:
               editText.setText(editText.getText()+".");
               textView.setText(textView.getText()+".");
               break;
           case R.id.clear:
               editText.setText("");
               break;
           case R.id.transtwo:
               String s=Integer.toBinaryString(Integer.parseInt(editText.getText().toString()));
               textView.setText(textView.getText()+"二进制："+s+"\n");
               break;
           case R.id.transeight:
               String ss=Integer.toOctalString(Integer.parseInt(editText.getText().toString()));
               textView.setText(textView.getText()+"八进制："+ss+"\n");
               break;
           case R.id.back:
               if(editText.getText().toString().length()>0) {
                   String text = editText.getText().toString();
                   text=text.substring(0,text.length()-1);
                   editText.setText(text);
                   String text1 = textView.getText().toString();
                   text1=text1.substring(0, text1.length() - 1);
                   textView.setText(text1);
               }
               break;
           case R.id.sin:
               double h=Math.toRadians(Double.parseDouble(editText.getText().toString()));
               textView.setText(textView.getText().toString()+"sin："+Math.sin(h)+"\n");
               break;
           case R.id.cos:
               double k=Math.toRadians(Double.parseDouble(editText.getText().toString()));
               textView.setText(textView.getText().toString()+"cos:"+Math.cos(k)+"\n");
               break;
           case R.id.tan:
               double i=Math.toRadians(Double.parseDouble(editText.getText().toString()));
               textView.setText(textView.getText().toString()+"tan："+Math.tan(i)+"\n");
               break;
           case R.id.cot:
               double j=Math.toRadians(Double.parseDouble(editText.getText().toString()));
               textView.setText(textView.getText().toString()+"cot："+1/Math.tan(j)+"\n");
               break;
           case R.id.cm:
                textView.setText(textView.getText().toString()+"m="+Double.parseDouble(editText.getText().toString())*100+"cm\n");
               break;
           case R.id.m:
               textView.setText(textView.getText().toString()+"cm="+Double.parseDouble(editText.getText().toString())/100+"m\n");
               break;
           case R.id.m3:
               textView.setText(textView.getText().toString()+"cm3="+Double.parseDouble(editText.getText().toString())/1000000+"m3\n");
               break;
           case R.id.cm3:
               textView.setText(textView.getText().toString()+"m3="+Double.parseDouble(editText.getText().toString())*1000000+"cm3\n");
               break;
           //case R.id.ans:
              // textView.setText(textView.getText().toString()+"ans="+ans+"\n");
              // break;
           case R.id.help:
               Toast.makeText(this,"欢迎使用帮助文档",Toast.LENGTH_SHORT).show();
               break;
           case R.id.eightten:
               int t1=Integer.parseInt(editText.getText().toString(),8);
               textView.setText(textView.getText()+"八进制转十进制："+t1+"\n");
               break;
           case R.id.twoten:
               int t2=Integer.parseInt(editText.getText().toString(),2);
               textView.setText(textView.getText()+"二进制转十进制："+t2+"\n");
               break;
           case R.id.tensix:
               String s16=Integer.toHexString(Integer.parseInt(editText.getText().toString()));
               textView.setText(textView.getText()+"十进制转十六进制："+s16+"\n");
               break;
           case R.id.sixten:
               int t61=Integer.parseInt(editText.getText().toString(),16);
               textView.setText(textView.getText()+"十六进制转十进制："+t61+"\n");
               break;
           default:
               break;
      }
    }

    /*public double handle(String all){
        Stack<Double> numstack=new Stack<Double>();
        Stack<Character> charstack=new Stack<Character>();
        if(all.charAt(0)=='-'){
            all="0"+all;
        }
        String[] nums=all.split("[0-9|.]");
        for(int i=0;i<nums.length;i++){
            numstack.add(Double.parseDouble(nums[i]));
        }
        String sym=all.replace("[.0-9]","");
        for(char sy:sym.toCharArray()) {
            charstack.add(sy);
        }
        return result(numstack,charstack);
    }
    public double result(Stack<Double> numstack,Stack<Character> charstack){
      /*  while(!numstack.empty()){
            System.out.println(numstack.pop());
        }
        while(!charstack.empty()){
            System.out.println(charstack.pop());
        }*/
        /*double a=numstack.pop();
        double b=numstack.pop();
        char m=charstack.peek();
        System.out.println(charstack.peek());
        while(!charstack.empty()) {
            switch (m) {
                case '+':
                    numstack.push(a + b);
                    break;
                case '-':
                    numstack.push(a - b);
                    break;
                case '*':
                    numstack.push(a * b);
                    break;
                case '/':
                    numstack.push(a / b);
                    break;
                default:
                    break;
            }
        }
        return numstack.pop();
    }*/




    /**

     * 计算一个正确的字符串形式运算式

     * @author HLP

     *

     */

    public class CalculatorMathUtil {

        /**

         * 数据栈

         */

        Stack<BigDecimal> number;

        /**

         * 符号栈

         */

        Stack<Character> operator;

        /**

         * 计算除法时，设置的结果标度

         */

        int length;



        public CalculatorMathUtil(int length) {

            this.length = length;

        }



        /**

         * 获得计算结果的字符串表示形式

         * @param exp

         * @return

         */

        public String getResult(String exp) {

            this.number = new Stack<BigDecimal>();

            this.operator = new Stack<Character>();

            calculate(exp);

            return this.number.peek().toString();

        }



        /**

         * 对运算式exp进行解析，并计算

         * @param exp

         */

        public void calculate(String exp) {

            if(exp.charAt(0)=='+'||exp.charAt(0)=='-'||exp.charAt(0)=='*'||exp.charAt(0)=='/'){
                exp=ans+exp;
            }
            int index = 0;

            int sign = 0;

            while (index < exp.length()) {

                char op = exp.charAt(index);

                if ((op <= '9' && op >= '0') || op == '.') {

                    index++;

                } else {

                    if (index > 0) {

                        char opp = exp.charAt(index-1);

                        if (op == '-' && !((opp <= '9' && opp >= '0') || opp == '.')) {

                            index++;

                            continue;

                        }

                    }

                    if (sign != index) {

                        String numStr = exp.substring(sign, index);

                        BigDecimal num = new BigDecimal(numStr);

                        number.push(num);

                    }

                    analysis(op);

                    sign = ++index;

                }

            }

        }



        /**

         * 对得到的数据和符号进行分析处理

         * @param op

         * @param num

         */

        public void analysis(char op) {

            if (operator.empty() || comparePriority(op, operator.peek())) {

                operator.push(op);

            } else {

                makeNum(op);

            }

        }



        /**

         * 计算

         * @param op

         * @param num

         */

        private void makeNum(char op) {

            BigDecimal result = null;

            while (!operator.empty() && !comparePriority(op, operator.peek())) {

                char op2 = operator.pop();

                if (op == ')' && op2 == '(') {

                    return;

                }

                BigDecimal num1 = null;

                BigDecimal num2 = number.pop();

                if (number.empty()) {

                    num1 = new BigDecimal(0);

                } else {

                    num1 = number.pop();

                }

                switch (op2) {

                    case '+':

                        result = num1.add(num2);

                        break;

                    case '-':

                        result = num1.subtract(num2);

                        break;

                    case '*':

                        result = num1.multiply(num2);

                        break;

                    case '/':

                        result = num1.divide(num2, new MathContext(length));

                        break;

                    default:

                        operator.pop();

                        break;

                }

                number.push(result);

            }

            if (op != ')') {

                operator.push(op);

            }

        }



        /**

         * 判断优先级：true(c1优先级高于c2)、false(c1优先级低于c2)

         * @param c1

         * @param c2

         * @return

         */

        public boolean comparePriority(char c1, char c2) {

            if (c1 == '=') {

                return false;

            } else if (c1 == ')') {

                return false;

            } else if (c1 == '(') {

                return true;

            } else if (c2 == '(') {

                return true;

            } else if ((c1 == '*' || c1 == '/') && (c2 == '-' || c2 == '+')) {

                return true;

            } else {

                return false;

            }



        }



    }

}
