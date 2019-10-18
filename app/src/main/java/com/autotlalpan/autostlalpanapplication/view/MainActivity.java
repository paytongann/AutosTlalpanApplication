package com.autotlalpan.autostlalpanapplication.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autotlalpan.autostlalpanapplication.R;
import com.autotlalpan.autostlalpanapplication.model.CarsPojo;
import com.autotlalpan.autostlalpanapplication.presenter.Presenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewContract, CustomListener {

    private Presenter presenter;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private ImageView ivCompanyTelephone, ivCompanyEmail, ivCompanyAddress, ivFacebook, ivWhatsApp;
    private TextView tvWebsite;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        progressBar = findViewById(R.id.progressBar_cyclic);
        presenter = new Presenter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(this);
        adapter.setListener(MainActivity.this);
        recyclerView.setAdapter(adapter);
        presenter.initRetrofit();
        presenter.onBindView(this);
        ivCompanyTelephone = findViewById(R.id.iv_phone);
        ivCompanyTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+5253363963"));
                startActivity(intent);
            }
        });
        ivCompanyEmail = findViewById(R.id.iv_email);
        ivCompanyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailintent = new Intent(Intent.ACTION_SEND);
                emailintent.setType("plain/text");
                emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{"VENTAS@AUTOTLALPAN.COM"});
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailintent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailintent, "Selecciona Gmail para enviar correo electrónico a Autos Tlalpan."));
            }
        });
        ivCompanyAddress = findViewById(R.id.iv_map);
        ivCompanyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("Autos Certificados Tlalpan"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        ivFacebook = findViewById(R.id.iv_facebook);
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://www.facebook.com/autostlalpan");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        ivWhatsApp = findViewById(R.id.iv_whatsapp);
        ivWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Uri uri = Uri.parse("smsto:" + "+525580369072");
                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                    i.setPackage("com.whatsapp");
                    startActivity(Intent.createChooser(i, ""));
            }
        });
        tvWebsite = findViewById(R.id.tv_website);
        tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("http://www.autostlalpan.com/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        Button btnNotice = findViewById(R.id.btn_disclosure);
        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NoticeFragment statsFragment = NoticeFragment.getInstance();
                statsFragment.show(
                        getSupportFragmentManager(), NoticeFragment.TAG
                );

            }
        });
    }

    public void onDataPopulated(ArrayList<CarsPojo> data) {
        adapter.setDataSet(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Buscar Autos...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void onClick(CarsPojo item) {
        Intent intent = new Intent(this, DetailedCarActivity.class);
        setResult(RESULT_OK, intent);
        String[] photos = new String[item.imagenes.size()];
        for (int i = 0; i < item.imagenes.size(); i++) {
            photos[i] = item.imagenes.get(i);
        }
        intent.putExtra("photos", photos);
        intent.putExtra("titulo", item.titulo);
        intent.putExtra("modelo", item.modelo.toString());
        intent.putExtra("kilometraje", item.kilomeraje.toString());
        intent.putExtra("color exterior", item.colorExterior);
        intent.putExtra("color interior", item.colorInterior);
        intent.putExtra("transmision", item.motor.transmision);
        intent.putExtra("inyeccion", item.motor.inyeccion);
        intent.putExtra("drivetrain", item.motor.drivetrain);
        intent.putExtra("interior", item.equipoInterior);
        intent.putExtra("exterior", item.equipoExterior);
        String price = item.precio;
        intent.putExtra("price", price);
        intent.putExtra("motor", item.motor.description);
        startActivity(intent);
    }

    public void endProgressBar(){
        ((ViewGroup)progressBar.getParent()).removeView(progressBar);
    }
}
