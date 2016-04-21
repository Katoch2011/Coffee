package com.example.priya.coffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button order,minus,plus;
    int quantity=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order=(Button)findViewById(R.id.order);
        minus=(Button)findViewById(R.id.minus);
        plus=(Button)findViewById(R.id.plus);
        final CheckBox wippedCream=(CheckBox)findViewById(R.id.whipped_cream);
        final CheckBox choclateChips=(CheckBox)findViewById(R.id.choclate_chips);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity=quantity-1;
                if(quantity<=0) {
                    Toast.makeText(getApplicationContext(), "You are entering the wrong value.", Toast.LENGTH_LONG).show();
                    quantity=1;
                }
                else
                    display(quantity);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity=quantity+1;
                display(quantity);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasCream= wippedCream.isChecked();
                boolean hasChoclate=choclateChips.isChecked();

                int price=calPrice(hasCream,hasChoclate);
                String priceMessage=orderSummary(price,hasCream,hasChoclate);
                displayMsg(priceMessage);
            }
        });
    }

    public void displayMsg(String msg)
    {
        TextView showMsg=(TextView)findViewById(R.id.showMsg);
        showMsg.setText(" " + msg);
    }

    public void display(int value)
    {
        TextView showInt=(TextView)findViewById(R.id.showInt);
        showInt.setText(String.valueOf(" " + value));
    }
    public int calPrice(boolean addCream,boolean addChoclate){
        int basePrice=10;
        if(addChoclate==true) basePrice+=5;
        if(addCream==true) basePrice+=2;
        return basePrice*quantity;
    }
    public String orderSummary(int price,boolean addCream,boolean addChoclate){
        EditText name=(EditText)findViewById(R.id.name);
        String priceMessage="NAME: "+ name.getText();
        priceMessage+="\n QUANTITY: "+quantity;
        priceMessage+="\n Add Whipped cream? "+addCream;
        priceMessage+="\n Add Choclate chips? "+addChoclate;
        priceMessage+= "\n Total: $"+price+"\n Thank You!!";
        return priceMessage;
    }
}
