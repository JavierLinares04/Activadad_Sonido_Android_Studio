package com.javierlinares.act_audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

SoundPool soundPool_1;
MediaPlayer media_player_1;
MediaRecorder mi_audio, mi_melodia;
EditText edt_seleccion;
TextView tv_vel, tv_tit_dur;
Boolean cancion;
String almacenamiento, almacenamiento_melodia;
int do_t, re_t, mi_t, fa_t, sol_t, la_t, si_t, duracion;
float velocidad, volumen;

View fondo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if	(ContextCompat.checkSelfPermission(getApplicationContext(),	Manifest.permission.WRITE_EXTERNAL_STORAGE)	!=
                PackageManager.PERMISSION_GRANTED
                &&	ActivityCompat.checkSelfPermission(getApplicationContext(),	Manifest.permission.RECORD_AUDIO)	!=
                PackageManager.PERMISSION_GRANTED)	{
            ActivityCompat.requestPermissions(MainActivity.this,	new	String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,	Manifest.permission.RECORD_AUDIO},	1000);
        }

        fondo = (View) findViewById(R.id.id_fondo);

        tv_vel = (TextView)findViewById(R.id.tv_velocidad);
        tv_tit_dur = (TextView)findViewById(R.id.tv_titulo);
        edt_seleccion = (EditText)findViewById(R.id.editTextNumber);

        soundPool_1 = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);


        do_t = soundPool_1.load(this, R.raw.dododo, 1);
        re_t = soundPool_1.load(this, R.raw.re, 1);
        mi_t = soundPool_1.load(this, R.raw.mi, 1);
        fa_t = soundPool_1.load(this, R.raw.fa, 1);
        sol_t = soundPool_1.load(this, R.raw.sol, 1);
        la_t = soundPool_1.load(this, R.raw.la, 1);
        si_t = soundPool_1.load(this, R.raw.si, 1);

        velocidad =Integer.parseInt(tv_vel.getText().toString());

        cancion = false;

    }

    //PRIMER APARTADO

    public void funcion_do(View view) {

        soundPool_1.play(do_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_si(View view) {

        soundPool_1.play(si_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_la(View view) {

        soundPool_1.play(la_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_re(View view) {

        soundPool_1.play(re_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_mi(View view) {

        soundPool_1.play(mi_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_sol(View view) {

        soundPool_1.play(sol_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_fa(View view) {

        soundPool_1.play(fa_t, 1.0f, 1.0f, 1, 0, velocidad);
    }

    public void funcion_suma_vel(View view) {

        if (velocidad<2){
           velocidad+=0.1f;

           tv_vel.setText(""+velocidad);
        }
        else {
            Toast.makeText(this, "Alcanzado limite", Toast.LENGTH_SHORT).show();
        }
    }

    public void funcion_resta_vel(View view) {

        if (velocidad>0.5f){
            velocidad-=0.1f;

            tv_vel.setText(""+velocidad);
        }
        else {
            Toast.makeText(this, "Alcanzado limite", Toast.LENGTH_SHORT).show();
        }
    }




    //SEGUNDO APARTADO

    public void funcion_cancion_1(View view) {

            media_player_1 = MediaPlayer.create(this, R.raw.cancion1);
            duracion = media_player_1.getDuration();
            tv_tit_dur.setText("Cancion 1 - "+duracion);

    }


    public void funcion_cancion_2(View view) {

            media_player_1 = MediaPlayer.create(this, R.raw.cancion2);
            duracion = media_player_1.getDuration();
            tv_tit_dur.setText("Cancion 2 - "+duracion);


    }

    public void funcion_detener(View view) {
        if(media_player_1!=null){
            media_player_1.stop();
            media_player_1.release();
            media_player_1=null;
            tv_tit_dur.setText("Ninguna cancion cargada");

        }
    }

    public void funcion_play(View view) {
        if(media_player_1==null){
            Toast.makeText(this, "Cargar cancion", Toast.LENGTH_SHORT).show();
        }
        else {
            if (media_player_1.isPlaying()==false){
                media_player_1.start();
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Cancion ya inicida", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void funcion_pausar(View view) {
        if(media_player_1==null){
            Toast.makeText(this, "Cargar cancion", Toast.LENGTH_SHORT).show();
        }
        else {
            if (media_player_1.isPlaying()==true){
                media_player_1.pause();
                Toast.makeText(this, "Pausar", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Cancion ya pausada", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void funcion_subir_volumen(View view) {
        if(media_player_1!=null){
            volumen +=0.1f;
            media_player_1.setVolume(volumen, volumen);
        }
        else {
            Toast.makeText(this, "Carga cancion", Toast.LENGTH_SHORT).show();
        }

    }

    public void funcion_bajar_volumen(View view) {
        if(media_player_1!=null){
            volumen -=0.1f;
            media_player_1.setVolume(volumen, volumen);
        }
        else {
            Toast.makeText(this, "Carga cancion", Toast.LENGTH_SHORT).show();
        }
    }

    public void funcion_selecion_inicio(View view) {

        if (media_player_1!=null){
            duracion = media_player_1.getDuration();
            if (Integer.parseInt(edt_seleccion.getText().toString())>0 && Integer.parseInt(edt_seleccion.getText().toString())<duracion) {
                media_player_1.seekTo(Integer.parseInt(edt_seleccion.getText().toString()));
                media_player_1.start();
                Log.i("Error", "No Falla");
            }
            else {
                Toast.makeText(this, "Rango no adecuado", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Carga cancion", Toast.LENGTH_SHORT).show();
        }

    }

    public void funcion_grabar_detener(View view) {
        if (mi_audio==null) {


            almacenamiento = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            mi_audio = new MediaRecorder();
            mi_audio.setAudioSource(MediaRecorder.AudioSource.MIC);
            mi_audio.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mi_audio.setAudioEncoder(MediaRecorder.OutputFormat.AMR_WB);
            mi_audio.setOutputFile(almacenamiento);

            try {
                mi_audio.prepare();
                mi_audio.start();
            } catch (IOException e) {
                Toast.makeText(this, "Error en la grabacion", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            mi_audio.stop();
            mi_audio.release();
            mi_audio=null;
        }
    }

    public void funcion_reproduccion_audio(View view) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(almacenamiento);
            mediaPlayer.prepare();
        }
        catch (IOException e){
            Toast.makeText(this, "No se posible reproducir", Toast.LENGTH_SHORT).show();
        }

        mediaPlayer.start();
        Toast.makeText(this, "Reproduciendo grabacion", Toast.LENGTH_SHORT).show();
    }

    public void funcion_grabar_melodia(View view) {
        if (mi_melodia==null) {


            almacenamiento_melodia = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            mi_melodia = new MediaRecorder();
            mi_melodia.setAudioSource(MediaRecorder.AudioSource.UNPROCESSED);
            mi_melodia.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mi_melodia.setAudioEncoder(MediaRecorder.OutputFormat.AMR_WB);
            mi_melodia.setOutputFile(almacenamiento_melodia);

            try {
                mi_melodia.prepare();
                mi_melodia.start();
            } catch (IOException e) {
                Toast.makeText(this, "Error en la grabacion", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            mi_melodia.stop();
            mi_melodia.release();
            mi_melodia=null;
        }
    }

    public void funcion_reproduccion_melodia(View view) {
        MediaPlayer mediaPlayer_melodia = new MediaPlayer();
        try {
            mediaPlayer_melodia.setDataSource(almacenamiento_melodia);
            mediaPlayer_melodia.prepare();
        }
        catch (IOException e){
            Toast.makeText(this, "No se posible reproducir", Toast.LENGTH_SHORT).show();
        }

        mediaPlayer_melodia.start();
        Toast.makeText(this, "Reproduciendo grabacion", Toast.LENGTH_SHORT).show();
    }

    public void funcion_cambio_color(View view) {
        fondo.setBackgroundColor(Color.CYAN);
    }
}