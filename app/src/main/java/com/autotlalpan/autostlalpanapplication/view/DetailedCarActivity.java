package com.autotlalpan.autostlalpanapplication.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.autotlalpan.autostlalpanapplication.R;

import me.relex.circleindicator.CircleIndicator;

public class DetailedCarActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyCustomPagerAdapter myCustomPagerAdapter;
    private TextView tvCarTitulo, tvCarModelo, tvCarKilometraje, tvCarColorExterior, tvCarColorInterior, tvCarTransmision, tvCarDriveTrain, tvInterior, tvInteriorTitle, tvExterior, tvExteriorTitle, tvPrice, tvMotor, tvMotorTitle;
    private Button btnNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_car);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextAppearance(this, R.style.CustomText);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager = findViewById(R.id.viewPager);
        String[] photos = getIntent().getStringArrayExtra("photos");

        for (int i = 0; i < photos.length; i++) {
        }
        myCustomPagerAdapter = new MyCustomPagerAdapter(DetailedCarActivity.this, photos);
        viewPager.setAdapter(myCustomPagerAdapter);
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        tvCarTitulo = findViewById(R.id.tv_car_titulo);
        tvCarTitulo.setText(getIntent().getStringExtra("titulo"));
        tvCarModelo = findViewById(R.id.tv_car_modelo);
        tvCarModelo.setText(getIntent().getStringExtra("modelo"));
        tvCarKilometraje = findViewById(R.id.tv_car_kilometraje);
        tvCarKilometraje.setText(getIntent().getStringExtra("kilometraje"));
        tvCarColorExterior = findViewById(R.id.tv_car_exterior);
        tvCarColorExterior.setText(getIntent().getStringExtra("color exterior"));
        tvCarColorInterior = findViewById(R.id.tv_car_interior);
        tvCarColorInterior.setText(getIntent().getStringExtra("color interior"));
        tvCarTransmision = findViewById(R.id.tv_car_transmision);
        tvCarTransmision.setText(getIntent().getStringExtra("transmision"));
        tvCarDriveTrain = findViewById(R.id.tv_car_drivetrain);
        tvCarDriveTrain.setText(getIntent().getStringExtra("drivetrain"));
        tvInterior = findViewById(R.id.tv_equipo_interior_body);
        tvInteriorTitle = findViewById(R.id.equipo_interior);
        String interior = getIntent().getStringExtra("interior");
        if (interior.equals("")){
            ((ViewGroup)tvInterior.getParent()).removeView(tvInterior);
            ((ViewGroup)tvInteriorTitle.getParent()).removeView(tvInteriorTitle);
        } else {
            String interiorEquipo = "";
            for (int i = 0; i < interior.split("\n").length; i++) {
                interiorEquipo += interior.split("\n")[i];
            }
            tvInterior.setText(interiorEquipo);
        }
        tvExterior = findViewById(R.id.tv_equipo_exterior_body);
        tvExteriorTitle = findViewById(R.id.equipo_exterior);
        String exterior = getIntent().getStringExtra("exterior");
        if (exterior.equals("")){
            ((ViewGroup)tvExterior.getParent()).removeView(tvExterior);
            ((ViewGroup)tvExteriorTitle.getParent()).removeView(tvExteriorTitle);
        } else {
            String exteriorEquipo = "";
            for (int i = 0; i < exterior.split("\n").length; i++) {
                exteriorEquipo += exterior.split("\n")[i];
            }
            tvExterior.setText(exteriorEquipo);
        }

        tvMotorTitle = findViewById(R.id.tv_motor_details_title);
        tvMotor = findViewById(R.id.tv_motor_details_body);
        String motor = getIntent().getStringExtra("motor");
        if (motor.equals("")){
            ((ViewGroup)tvMotor.getParent()).removeView(tvMotor);
            ((ViewGroup)tvMotorTitle.getParent()).removeView(tvMotorTitle);
        } else {
            tvMotor.setText(motor);
        }

        tvPrice = findViewById(R.id.tv_precio);
        tvPrice.setText("$" + getIntent().getStringExtra("price"));

        ImageView ivCompanyTelephone = findViewById(R.id.iv_phone);
        ivCompanyTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+5253363963"));
                startActivity(intent);
            }
        });
        ImageView ivCompanyEmail = findViewById(R.id.iv_email);
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
        ImageView ivCompanyAddress = findViewById(R.id.iv_map);
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
        ImageView ivFacebook = findViewById(R.id.iv_facebook);
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://www.facebook.com/autostlalpan");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        ImageView ivWhatsApp = findViewById(R.id.iv_whatsapp);
        ivWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:" + "+525580369072");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });
        TextView tvWebsite = findViewById(R.id.tv_website);
        tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("http://www.autostlalpan.com/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        btnNotice = findViewById(R.id.btn_disclosure);
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
}
