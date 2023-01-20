package com.example.truco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import nl.dionsegijn.konfetti.xml.KonfettiView;

public class show extends AppCompatActivity {

    ImageButton addBtn1, addBtn2, minusBtn1, minusBtn2;

    CharSequence[] pontos = {"12", "9"};

    TextView pontosP1, pontosP2, viraP1, viraP2, p1Name, p2Name, viewVira;

    Animation anim;

    int pontoP1 = 0;
    int pontoP2 = 0;

    int winP1 = 0;
    int winP2 = 0;

    int pontosVira = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getSupportActionBar().hide();

        IniciarComponentes();

        DialogoNomeP2();
        DialogoNomeP1();
        DialogoPontos();

        //Clique do botão de soma de Nós
        addBtn1.setOnClickListener(view -> {

            pontoP1++;
            String txtPontoP1 = String.valueOf(pontoP1);
            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink1x);
            pontosP1.startAnimation(anim);
            pontosP1.setText(txtPontoP1);
            Vira();

        });//Fim do clique do botão de soma de Nós


        //Clique do botão de soma de Eles
        addBtn2.setOnClickListener(view -> {

            pontoP2++;
            String txtPontoP2 = String.valueOf(pontoP2);
            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink1x);
            pontosP2.startAnimation(anim);
            pontosP2.setText(txtPontoP2);
            Vira();

        });//Fim do clique do botão de soma de Eles


        //Clique do botão de diminuir de Nós
        minusBtn1.setOnClickListener(view -> {
            if(pontoP1 <= 0){

            }
            else{
                pontoP1--;
            }

            String txtPontoP1 = String.valueOf(pontoP1);
            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink1x);
            pontosP1.startAnimation(anim);
            pontosP1.setText(txtPontoP1);

        });//Fim do clique do botão de diminuir de Nós


        //Clique do botão de diminuir de Eles
        minusBtn2.setOnClickListener(view -> {
            if(pontoP2 <= 0){

            }
            else {
                pontoP2--;
            }

            String txtPontoP2 = String.valueOf(pontoP2);
            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink1x);
            pontosP2.startAnimation(anim);
            pontosP2.setText(txtPontoP2);

        });//Fim do clique do botão de diminuir de Eles


        //Clique da vira para remover a vira de Nós
        viraP1.setOnClickListener(view -> {
            DesviraP1();
        });//Fim do clique da vira para remover a vira de Nós


        //Clique da vira para remover a vira de Eles
        viraP2.setOnClickListener(view -> {
            DesviraP2();
        });//Fim do clique da vira para remover a vira de Eles

    }

    private void TxtViraA() {
        //String txtVira = "Vira a: " + pontosVira+"";
        String txtVira = "Vira a "+pontosVira;
        viewVira.setText(txtVira);
    }

    private void DialogoNomeP2() {

        EditText p2NmDialog = new EditText(this);
        p2NmDialog.setText("Eles");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Digite o nome do jogador").setView(p2NmDialog);

        builder.setPositiveButton("Ok", (dialogInterface, i) ->
                p2Name.setText(p2NmDialog.getText().toString()));
        builder.create().show();

    }

    private void DialogoNomeP1() {


        EditText p1NmDialog = new EditText(this);
        p1NmDialog.setText("Nós");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Digite o nome do jogador").setView(p1NmDialog)
                .setPositiveButton("Ok", (dialogInterface, i) ->
                p1Name.setText(p1NmDialog.getText().toString()))
                .create()
                .show();

    }

    private void DialogoPontos() {

        //Criar caixa de dialogo pra definir até quantos pontos o jogo vai
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pontos para a vira:");
        builder.setSingleChoiceItems(pontos,0, (dialogInterface, item) -> {
            if(item == 1){
                pontosVira = 9;
            }
            else{
                pontosVira = 12;
            }

        });
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            Toast.makeText(this, "Agora o jogo vira a "+pontosVira+" pontos \n" +
                    " e termina em "+(pontosVira*2)+" pontos", Toast.LENGTH_LONG).show();
            TxtViraA();
        });

        builder.create().show();

    }


    private void IniciarComponentes() {

        addBtn1 = findViewById(R.id.addBtn1);
        minusBtn1 = findViewById(R.id.minusBtn1);
        addBtn2 = findViewById(R.id.addBtn2);
        minusBtn2 = findViewById(R.id.minusBtn2);
        pontosP1 = findViewById(R.id.pontosP1);
        pontosP2 = findViewById(R.id.pontosP2);

        p1Name = findViewById(R.id.p1Name);
        p2Name = findViewById(R.id.p2Name);

        viraP2 = findViewById(R.id.viraP2);
        viraP1 = findViewById(R.id.viraP1);
        viewVira = findViewById(R.id.viewVira);

        pontosP1.setText(String.valueOf(pontoP1));
        pontosP2.setText(String.valueOf(pontoP2));

    }


    public void Vira(){

        if(pontoP1 == pontosVira){

            //Ativa o sinalizador de Vira
            viraP1.setVisibility(View.VISIBLE);
            Toast.makeText(this, p1Name.getText().toString()+" virou", Toast.LENGTH_SHORT).show();

            pontoP1 = 0;
            String txtPontoP1 = String.valueOf(pontoP1);
            pontosP1.setText(txtPontoP1);

            winP1++;
            if(winP1 == 2) {
                Toast.makeText(this, "Vencedor: "+p1Name.getText().toString(), Toast.LENGTH_SHORT).show();
                DesviraP1();
                DesviraP2();

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("msg","Vencedor: \n"+p1Name.getText().toString());
                startActivity(intent);
            }

        }
        if(pontoP2 == pontosVira){

            //Ativa o sinalizador de Vira
            viraP2.setVisibility(View.VISIBLE);
            Toast.makeText(this, p2Name.getText().toString()+" virou", Toast.LENGTH_SHORT).show();

            pontoP2 = 0;
            String txtPontoP2 = String.valueOf(pontoP2);

            pontosP2.setText(txtPontoP2);

            winP2++;
            if(winP2 == 2) {
                Toast.makeText(this, "Vencedor: \n"+p1Name.getText().toString(), Toast.LENGTH_SHORT).show();
                DesviraP1();
                DesviraP2();

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("msg","Vencedor: \n"+p1Name.getText().toString());
                startActivity(intent);
            }

        }
    }

    private void DesviraP2() {

            //Desliga o sinalizador de Vira
            viraP2.setVisibility(View.INVISIBLE);
            Toast.makeText(this, p2Name.getText().toString()+" tirou a vira", Toast.LENGTH_SHORT).show();
            winP2 = 0;

    }

    private void DesviraP1(){

        //Desliga o sinalizador de Vira
        viraP1.setVisibility(View.INVISIBLE);
        Toast.makeText(this, p1Name.getText().toString()+" tirou a vira", Toast.LENGTH_SHORT).show();
        winP1 = 0;

    }

}