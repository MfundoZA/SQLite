 package com.mfundoza.sqlite;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.ListView;
 import android.widget.Switch;
 import android.widget.TextView;

 import android.database.sqlite.SQLiteDatabase;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

 public class MainActivity extends AppCompatActivity {
     TextView txtName;
     TextView txtAge;
     Switch swtActiveCustomer;
     Button btnViewAll;
     Button btnAdd;
     ListView lstCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        swtActiveCustomer = findViewById(R.id.swtActiveCustomer);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnAdd = findViewById(R.id.btnAdd);
        lstCustomers = findViewById(R.id.lstCustomers);

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewUser();
            }
        });
    }

     private void addNewUser() {
        try {

            CustomerModel customerModel = new CustomerModel(-1, txtName.getText().toString(),
                    Integer.parseInt(txtAge.getText().toString()), swtActiveCustomer.isChecked());
            Toast.makeText(this, customerModel.toString(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error creating customer", Toast.LENGTH_SHORT).show();
        }
     }
 }