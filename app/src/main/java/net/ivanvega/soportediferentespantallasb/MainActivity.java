package net.ivanvega.soportediferentespantallasb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.contenedor_pequeno) != null
           ){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_pequeno,
                            SelectorFragment.class, null)
            .commit();
        }




    }
}