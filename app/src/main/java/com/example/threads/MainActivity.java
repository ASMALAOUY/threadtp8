package com.example.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtStatus;
    private ProgressBar progressBar;
    private ImageView img;
    private Handler mainHandler;
    private Button btnLoadThread, btnCalcAsync, btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus = findViewById(R.id.txtStatus);
        progressBar = findViewById(R.id.progressBar);
        img = findViewById(R.id.img);
        btnLoadThread = findViewById(R.id.btnLoadThread);
        btnCalcAsync = findViewById(R.id.btnCalcAsync);
        btnToast = findViewById(R.id.btnToast);

        mainHandler = new Handler(Looper.getMainLooper());

        btnToast.setOnClickListener(v ->
                Toast.makeText(this, "UI réactive", Toast.LENGTH_SHORT).show()
        );

        btnLoadThread.setOnClickListener(v -> loadImageWithThread());
        btnCalcAsync.setOnClickListener(v -> new HeavyCalcTask().execute());
    }

    private void loadImageWithThread() {
        // Désactiver le bouton pendant le chargement (optionnel)
        btnLoadThread.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        txtStatus.setText("Statut : chargement image (Thread)...");

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            mainHandler.post(() -> {
                img.setImageBitmap(bitmap);
                progressBar.setVisibility(View.INVISIBLE);
                txtStatus.setText("Statut : image chargée (Thread)");
                btnLoadThread.setEnabled(true);
            });
        }).start();
    }

    private class HeavyCalcTask extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            btnCalcAsync.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            txtStatus.setText("Statut : calcul lourd (AsyncTask)...");
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long result = 0;
            for (int i = 1; i <= 100; i++) {
                for (int k = 0; k < 200000; k++) {
                    result += (i * k) % 7;
                }
                publishProgress(i);
                // Simulation d'annulation si l'activité est fermée
                if (isCancelled()) break;
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            txtStatus.setText("Calcul : " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Long result) {
            progressBar.setVisibility(View.INVISIBLE);
            txtStatus.setText("Statut : calcul terminé, résultat = " + result);
            btnCalcAsync.setEnabled(true);
        }

        @Override
        protected void onCancelled() {
            progressBar.setVisibility(View.INVISIBLE);
            txtStatus.setText("Calcul annulé");
            btnCalcAsync.setEnabled(true);
        }
    }
}