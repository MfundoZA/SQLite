 package com.mfundoza.sqlite;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.ListView;
 import android.widget.Switch;
 import android.widget.TextView;

 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

 public class MainActivity extends AppCompatActivity {
     TextView txtName;
     TextView txtAge;
     Switch swtActiveCustomer;
     Button btnViewAll;
     Button btnAdd;
     ListView lstCustomers;

     DatabaseHelper dataBaseHelper;
     ArrayAdapter customerArrayAdapter;

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

        dataBaseHelper = new DatabaseHelper(this);

        updateListView(dataBaseHelper);

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllCustomers();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCustomer();
            }
        });

        lstCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteCustomer(position);
            }
        });
    }

     private void updateListView(DatabaseHelper dataBaseHelper) {
         customerArrayAdapter = new ArrayAdapter<CustomerModel>(this, R.layout.simple_list_item, R.id.textView, dataBaseHelper.getAllCustomers());
         lstCustomers.setAdapter(customerArrayAdapter);
     }

     private void viewAllCustomers() {
         updateListView(dataBaseHelper);
     }

     private void addNewCustomer() {
         CustomerModel customerModel;

        try {
            customerModel = new CustomerModel(-1, txtName.getText().toString(),
                    Integer.parseInt(txtAge.getText().toString()), swtActiveCustomer.isChecked());
            Toast.makeText(this, customerModel.toString(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error creating customer", Toast.LENGTH_SHORT).show();
            customerModel = new CustomerModel(-1, "error", 0, false);
        }

         boolean success = dataBaseHelper.addOne(customerModel);

         updateListView(dataBaseHelper);
     }

     private void deleteCustomer(int position) {
        CustomerModel clickedCustomer = (CustomerModel) lstCustomers.getItemAtPosition(position);
        dataBaseHelper.deleteCustomer(clickedCustomer);
        updateListView(dataBaseHelper);
     }
 }