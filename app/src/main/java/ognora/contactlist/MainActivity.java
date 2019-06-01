package ognora.contactlist;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<ContactModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ListAdapters adapter ;
    ProgressBar progressBar  ;
    int READ_CONTACTS =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        check();

        recyclerView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListAdapters(arrayList, MainActivity.this);
        recyclerView.setAdapter(adapter);

        final ContactViewModel contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        contactViewModel.getContacts(progressBar).observe(this, new Observer<ArrayList<ContactModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ContactModel> contactModels) {
               arrayList.addAll(contactModels);
               adapter.notifyDataSetChanged();

            }
        });


    }

    private void check() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==READ_CONTACTS && grantResults.length>0 ) {

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                return ;

            }
            else
            {
                finish();
            }

        }
    }
}
