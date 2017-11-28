package com.example.lexel.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "type";
    public static final String RESULT_ITEM = "item";
    public static final int RC_ADD_ITEM = 99;

    private EditText name;
    private EditText price;
    private ImageButton add;
    private String type;


    public static void startForResult(Fragment fragment, String type, int requestCode) {
               Intent starter = new Intent(fragment.getContext(), AddActivity.class);
               starter.putExtra(EXTRA_TYPE, type);
              fragment.startActivityForResult(starter, requestCode);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        add = findViewById(R.id.add);
        add.setEnabled(false);
        type = getIntent().getStringExtra(EXTRA_TYPE);

        name.addTextChangedListener(textWatcher);
        price.addTextChangedListener(textWatcher);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra(RESULT_ITEM, new Item(name.getText().toString(), Integer.parseInt(price.getText().toString()), type));
                setResult(RESULT_OK, result);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        name.removeTextChangedListener(textWatcher);
        price.removeTextChangedListener(textWatcher);
        super.onDestroy();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequences, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequences, int i, int i1, int i2) {

            add.setEnabled(!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(price.getText()));

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}


