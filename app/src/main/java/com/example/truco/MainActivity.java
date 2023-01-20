package com.example.truco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    ImageView cuia;
    RelativeLayout telaVitoria;
    KonfettiView confete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();
    }

    private void IniciarComponentes() {

        texto = findViewById(R.id.texto);
        cuia = findViewById(R.id.cuia);
        telaVitoria = findViewById(R.id.telaVitoria);

        Intent intent = getIntent();
        String qmVenceu = intent.getStringExtra("msg");

        texto.setText(qmVenceu);

        confete = findViewById(R.id.confete);

        Shape.DrawableShape drawableShape = new Shape.DrawableShape(AppCompatResources.getDrawable(this, R.drawable.ic_dice), true);
        EmitterConfig emitterConfig = new Emitter(3000, TimeUnit.MILLISECONDS).max(300);
        confete.start(
                new PartyFactory(emitterConfig)
                        .shapes(Shape.Circle.INSTANCE, Shape.Square.INSTANCE, drawableShape)
                        .spread(360)
                        .position(0.2, 0.2, 1, 1)
                        .sizes(new Size(8, 50, 10))
                        .timeToLive(10000).fadeOutEnabled(true).build()
        );

    }

}