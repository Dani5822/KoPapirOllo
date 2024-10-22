package com.example.kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageviewplayer;
    private ImageView imageviewcomputer;
    private TextView textviewresult;
    private Button buttonko;
    private Button buttonpapir;
    private Button buttonollo;
    private Random random;
    private List<String> kep;
    private int player;
    private int computer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        buttonko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageviewplayer.setImageResource(R.drawable.rock);
                ellenorzes(0, Computer());
                vege();
            }
        });
        buttonollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageviewplayer.setImageResource(R.drawable.scissors);
                ellenorzes(2, Computer());
                vege();
            }
        });
        buttonpapir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageviewplayer.setImageResource(R.drawable.paper);
                ellenorzes(1, Computer());
                vege();
            }
        });
    }

    public void init() {
        imageviewplayer = findViewById(R.id.imageviewplayer);
        imageviewcomputer = findViewById(R.id.imageviewcomputer);
        textviewresult = findViewById(R.id.textviewresult);
        buttonko = findViewById(R.id.buttonko);
        buttonpapir = findViewById(R.id.buttonpapir);
        buttonollo = findViewById(R.id.buttonollo);
        random = new Random();
        player = 0;
        computer = 0;


    }

    public int Computer() {
        int r = random.nextInt(3);
        if (r == 0) {
            imageviewcomputer.setImageResource(R.drawable.rock);
        } else if (r == 1) {
            imageviewcomputer.setImageResource(R.drawable.paper);
        } else {
            imageviewcomputer.setImageResource(R.drawable.scissors);
        }
        return r;
    }

    public void ellenorzes(int Player, int Computer) {
        if (Player == Computer) {
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        } else {
            if (Player == 0 && Computer == 1) {
                Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();
                computer++;
            } else if (Player == 0 && Computer == 2) {
                Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
                player++;
            } else if (Player == 1 && Computer == 0) {
                Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
                player++;
            } else if (Player == 1 && Computer == 2) {
                Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();
                computer++;
            } else if (Player == 2 && Computer == 0) {
                Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();
                computer++;
            } else if (Player == 2 && Computer == 1) {
                Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
                player++;
            }
        }
        textviewresult.setText("Eredmény: Ember: " + player + " Computer: " + computer);
    }

    public void vege() {
        if (player >= 3 || computer >= 3) {
            if (player >= 3) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Nyertél")
                        .setMessage("Szeretnél új játékot játszani?")
                        .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                player = 0;
                                computer = 0;
                                textviewresult.setText("Eredmény: Ember: " + player + "Computer: " + computer);
                            }
                        })
                        .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .create();
                alertDialog.show();
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Vereség")
                        .setMessage("Szeretnél új játékot játszani?")
                        .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                player = 0;
                                computer = 0;
                                textviewresult.setText("Eredmény: Ember: " + player + "Computer: " + computer);
                            }
                        })
                        .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        }
    }
}